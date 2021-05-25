package com.demo.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.demo.config.KeyConfig;
import com.demo.dao.GoodsDao;
import com.demo.entity.Goods;
import com.demo.service.GoodsService;
import com.demo.util.RedisString;
import io.seata.rm.tcc.api.BusinessActionContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;

/**
 * @author lc
 * @date 2021-01-15
 * @description
 */
@Service("goodsService")
@Slf4j
public class GoodsServiceImpl extends ServiceImpl<GoodsDao, Goods> implements GoodsService {
    @Autowired
    private RedisString redisString;

    @Override
    public Boolean updateTotal(Serializable goodsId, Integer num) {
        Goods byId = getById(goodsId);
        if (byId == null)
            throw new RuntimeException("商品找不到");
        byId.setTotal(byId.getTotal() - num);
        return byId.updateById();
    }
    /**
     * @param type 1提交,2回滚
     * @date 2021/5/25
     */
    @Transactional
    public boolean updateTotal(BusinessActionContext actionContext,Integer type){
        String xid = actionContext.getXid();
        String v = redisString.get(KeyConfig.GOODS_KEY+xid);
        if (StrUtil.isBlank(v)) {
            return true;
        }
        Object goodsId = actionContext.getActionContext("goodsId");
        Object num = actionContext.getActionContext("num");
        boolean b = updateGoods(goodsId.toString(), (Integer) num, type);
        if (b) {
            redisString.remove(KeyConfig.GOODS_KEY+xid);
        }
        return b;
    }
    /**
     * @param type 1提交,2回滚,3锁定
     * @date 2021/5/25
     */
    @Transactional
    public boolean updateGoods(String goodsId,Integer number,Integer type){
        Goods goods = getById(goodsId);
        if(type == 1){//提交
            goods.setFreeze(goods.getFreeze() -number);
        } else if (type == 2) {//回滚
            goods.setFreeze(goods.getFreeze() - number);
            goods.setTotal(goods.getTotal() + number);
        }else if (type == 3) {//锁定资源
            goods.setFreeze(goods.getFreeze() + number);
            goods.setTotal(goods.getTotal() - number);
        }
        return goods.updateById();
    }



    @Override
//    @Tcc(prefix = KeyConfig.GOODS_KEY)
    @Transactional
    public boolean updateTotalTcc(BusinessActionContext actionContext, Serializable goodsId, Integer num) {
        String xid = actionContext.getXid();
        log.info("商品服务try: xid: {}", xid);
        Goods goods = getById(goodsId);
        if (goods == null) {
            throw new RuntimeException("商品找不到");
        }
        if (goods.getTotal() < num) {
            throw new RuntimeException("商品数量不足");
        }
        boolean b = updateGoods(goodsId.toString(), num, 3);//乐观锁
        if (b) { //扣减成功才设置xid 否则会出现多线程问题,
            redisString.set(KeyConfig.GOODS_KEY+xid,"1");// 生产由redis实现
        }
        return b;
    }

    @Override
//    @Tcc(prefix = KeyConfig.GOODS_KEY,type = false)
    @Transactional
    public boolean commit(BusinessActionContext actionContext) {
        return updateTotal(actionContext, 1);
    }

    @Override
//    @Tcc(prefix = KeyConfig.GOODS_KEY,type = false)
    @Transactional
    public boolean rollback(BusinessActionContext actionContext) {
        return updateTotal(actionContext, 2);
    }
}