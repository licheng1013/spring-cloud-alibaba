package com.demo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.demo.entity.User;
import io.seata.rm.tcc.api.BusinessActionContext;
import io.seata.rm.tcc.api.LocalTCC;
import io.seata.rm.tcc.api.TwoPhaseBusinessAction;

import java.io.Serializable;

/**
 * @author lc
 * @date 2021-01-15
 * @description
 */
@LocalTCC
public interface UserService extends IService<User> {

    Boolean updateMoney(Serializable userId, Integer money);

    @TwoPhaseBusinessAction(name = "updateMoneyTcc", commitMethod = "commit", rollbackMethod = "rollback")
    Boolean updateMoneyTcc(BusinessActionContext actionContext,Serializable userId, Integer money);

    boolean commit(BusinessActionContext actionContext);

    boolean rollback(BusinessActionContext actionContext);
}