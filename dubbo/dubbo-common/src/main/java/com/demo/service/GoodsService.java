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
 * @description @LocalTCC 和 @TwoPhaseBusinessAction,@BusinessActionContextParameter 注解不要则是AT模式 否则 TCC模式
 */
@LocalTCC
public interface GoodsService extends IService<Goods> {

    @TwoPhaseBusinessAction(name = "updateTotal", commitMethod = "commit", rollbackMethod = "rollback")
    boolean updateTotal(BusinessActionContext actionContext
            , @BusinessActionContextParameter(paramName = "goodsId") Serializable goodsId
            , @BusinessActionContextParameter(paramName = "num") Integer num);

    boolean commit(BusinessActionContext actionContext);

    boolean rollback(BusinessActionContext actionContext);

    Integer getMoney(Integer goodsId);
}