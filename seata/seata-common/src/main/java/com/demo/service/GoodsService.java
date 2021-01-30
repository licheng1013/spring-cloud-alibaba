package com.demo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.demo.entity.Goods;
import io.seata.rm.tcc.api.BusinessActionContext;
import io.seata.rm.tcc.api.TwoPhaseBusinessAction;

import java.io.Serializable;

/**
 * @author lc
 * @date 2021-01-15
 * @description
 */
public interface GoodsService extends IService<Goods> {

    Boolean updateTotal(Serializable goodsId, Integer num);

    @TwoPhaseBusinessAction(name = "GoodsServiceImpl", commitMethod = "commit", rollbackMethod = "rollback")
    Boolean updateTotalTcc(BusinessActionContext actionContext,Serializable goodsId, Integer num);

    boolean commit(BusinessActionContext actionContext);

    boolean rollback(BusinessActionContext actionContext);
}