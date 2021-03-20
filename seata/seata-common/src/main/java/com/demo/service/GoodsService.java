package com.demo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.demo.entity.Goods;
import io.seata.rm.tcc.api.BusinessActionContext;
import io.seata.rm.tcc.api.BusinessActionContextParameter;
import io.seata.rm.tcc.api.LocalTCC;
import io.seata.rm.tcc.api.TwoPhaseBusinessAction;

import java.io.Serializable;

/**
 * @author lc
 * @date 2021-01-15
 * @description
 */
@LocalTCC
public interface GoodsService extends IService<Goods> {

    Boolean updateTotal(Serializable goodsId, Integer num);

    @TwoPhaseBusinessAction(name = "updateTotalTcc", commitMethod = "commit", rollbackMethod = "rollback")
    boolean updateTotalTcc(BusinessActionContext actionContext, @BusinessActionContextParameter(paramName = "goodsId")  Serializable goodsId,
                           @BusinessActionContextParameter(paramName = "num") Integer num);

    boolean commit(BusinessActionContext actionContext);

    boolean rollback(BusinessActionContext actionContext);
}