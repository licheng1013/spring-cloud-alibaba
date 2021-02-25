package com.demo.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.demo.dao.GoodsDao;
import com.demo.entity.Goods;
import com.demo.util.ResultHolder;
import io.seata.rm.tcc.api.BusinessActionContext;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboService;

import java.io.Serializable;

/**
 * @author lc
 * @date 2021-01-15
 * @description
 */
@Slf4j
@DubboService
public class GoodsServiceImpl extends ServiceImpl<GoodsDao, Goods> implements GoodsService {

    /**
     * @author lc
     * @date 2021/2/23
     * @description 锁定资源,检查资源
     * @param goodsId 商品id
     * @param num 扣除数量
     */
    @Override
    public boolean updateTotal(BusinessActionContext actionContext,Serializable goodsId, Integer num) {
        String xid = actionContext.getXid();
        System.out.println("try Goods xid:" + xid);
        Goods goods = getById(goodsId);
        if (goods == null) {
            throw new RuntimeException("商品找不到");
        }
        if (goods.getTotal() < num){
            throw new RuntimeException("商品数量不足");
        }

        goods.setTotal(goods.getTotal() - num);//扣除并冻结起来
        goods.setFreeze(goods.getFreeze() + num);
        ResultHolder.set(xid, "goods");
        return goods.updateById();
    }

    /**
     * @author lc
     * @date 2021/2/23
     * @description 释放锁定的资源,并成功提交,业务上失败则短信通知
     */
    @Override
    public boolean commit(BusinessActionContext actionContext) {
        String xid = actionContext.getXid();
        Object goodsId = actionContext.getActionContext("goodsId");
        Object num = actionContext.getActionContext("num");
        log.info("商品服务 commit,商品id: {},商品扣除数量: {},xid: {}",goodsId,num,xid);
        String v = ResultHolder.get(xid);
        if (v == null) {
            log.info("commit空提交处理");
            return true; //空回滚
        }
        //业务处理
        Goods goods = getById(goodsId.toString());
        goods.setFreeze(goods.getFreeze()-(Integer)num);

        ResultHolder.remove(xid); //处理后删除
        return goods.updateById();
    }

    /**
     * @author lc
     * @date 2021/2/23
     * @description 回滚操作,业务上失败则短信通知
     */
    @Override
    public boolean rollback(BusinessActionContext actionContext) {
        String xid = actionContext.getXid();
        Object goodsId = actionContext.getActionContext("goodsId");
        Object num = actionContext.getActionContext("num");
        String v = ResultHolder.get(xid);
        log.info("商品服务 rollback,商品id: {},商品回滚数量: {},xid: {}",goodsId,num,xid);
        if (v == null) {
            log.info("rollback空回滚处理");
            return true; //空回滚
        }
        //业务处理
        Goods goods = getById(goodsId.toString());
        goods.setFreeze(goods.getFreeze()-(Integer)num);
        goods.setTotal(goods.getTotal() + (Integer) num);

        ResultHolder.remove(xid); //处理后删除
        return goods.updateById();
    }

    @Override
    public Integer getMoney(Integer goodsId) {
        System.out.println(goodsId);
        Goods byId = getById(goodsId);
        if (byId == null) {
            throw new RuntimeException("商品未找到");
        }
        return byId.getMoney();
    }
}