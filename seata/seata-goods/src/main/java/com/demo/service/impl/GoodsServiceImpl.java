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

        goods.setTotal(goods.getTotal() - num);//扣除并冻结起来
        goods.setFreeze(goods.getFreeze() + num);
        log.info("商品对象: {}",goods);
        boolean b = goods.updateById();
        log.info("修改结果: {}",b);
        if (b) { //扣减成功才设置xid //注解驱动
            redisString.set(KeyConfig.GOODS_KEY+xid,"1");// 生产由redis实现
            //ResultHolder.set(xid, "goods"); // 生产由redis实现
        }
        return b;
    }

    @Override
//    @Tcc(prefix = KeyConfig.GOODS_KEY,type = false)
    @Transactional
    public boolean commit(BusinessActionContext actionContext) {
        String xid = actionContext.getXid();
        Object goodsId = actionContext.getActionContext("goodsId");
        Object num = actionContext.getActionContext("num");
        log.info("商品服务 commit,商品id: {},商品扣除数量: {},xid: {}", goodsId, num, xid);
        String v = redisString.get(KeyConfig.GOODS_KEY+xid);//ResultHolder.get(xid)//注解驱动


        if (StrUtil.isBlank(v)) { //注解驱动
            log.info("commit空提交处理");
            return true; //空回滚
        }
        //业务处理
        Goods goods = getById(goodsId.toString());
        goods.setFreeze(goods.getFreeze() - (Integer) num);
        boolean b = goods.updateById();
        if (b) { //注解驱动
            redisString.remove(KeyConfig.GOODS_KEY+xid);// 生产由redis实现
            //ResultHolder.remove(xid); //删除xid
        }
        return b;
    }

    @Override
//    @Tcc(prefix = KeyConfig.GOODS_KEY,type = false)
    @Transactional
    public boolean rollback(BusinessActionContext actionContext) {
        String xid = actionContext.getXid();
        Object goodsId = actionContext.getActionContext("goodsId");
        Object num = actionContext.getActionContext("num");
        log.info("商品服务 rollback,商品id: {},商品回滚数量: {},xid: {}", goodsId, num, xid);
        String v = redisString.get(KeyConfig.GOODS_KEY+xid);//ResultHolder.get(xid) // 注解驱动

        if (StrUtil.isBlank(v)) { // 注解驱动
            log.info("rollback空回滚处理");
            return true; //空回滚
        }
        //业务处理
        Goods goods = getById(goodsId.toString());
        goods.setFreeze(goods.getFreeze() - (Integer) num);
        goods.setTotal(goods.getTotal() + (Integer) num);

        boolean b = goods.updateById();
        if (b) { //注解驱动
            redisString.remove(KeyConfig.GOODS_KEY+xid);// 生产由redis实现
            //ResultHolder.remove(xid); //删除xid
        }
        return b;
    }
}