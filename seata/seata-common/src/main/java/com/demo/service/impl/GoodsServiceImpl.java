package com.demo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.demo.dao.GoodsDao;
import com.demo.entity.Goods;
import com.demo.service.GoodsService;
import io.seata.rm.tcc.api.BusinessActionContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.Serializable;

/**
 * @author lc
 * @date 2021-01-15
 * @description
 */
@Service("goodsService")
@Slf4j
public class GoodsServiceImpl extends ServiceImpl<GoodsDao, Goods> implements GoodsService {

    @Override
    public Boolean updateTotal(Serializable goodsId, Integer num) {
        Goods byId = getById(goodsId);
        if (byId == null)
            throw new RuntimeException("商品找不到");
        byId.setTotal(byId.getTotal() - num);
        return byId.updateById();
    }

    @Override
    public Boolean updateTotalTcc(BusinessActionContext actionContext, Serializable goodsId, Integer num) {
//        String xid = actionContext.getXid();
//        System.out.println("TccActionTwo prepare, xid:" + xid);

        Goods byId = getById(goodsId);
        if (byId == null)
            throw new RuntimeException("商品找不到");
        byId.setTotal(byId.getTotal() - num);
        return byId.updateById();
    }

    @Override
    public boolean commit(BusinessActionContext actionContext) {
        String xid = actionContext.getXid();
        log.info("xid: {}",xid );
        return true;
    }

    @Override
    public boolean rollback(BusinessActionContext actionContext) {
        String xid = actionContext.getXid();
        log.info("xid: {}",xid);
        return true;
    }
}