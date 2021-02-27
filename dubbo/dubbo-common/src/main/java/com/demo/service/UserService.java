package com.demo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.demo.entity.User;
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
public interface UserService extends IService<User> {
    String hello();
    @TwoPhaseBusinessAction(name = "updateMoney", commitMethod = "commit", rollbackMethod = "rollback")
    boolean updateMoney(BusinessActionContext actionContext
            , @BusinessActionContextParameter(paramName = "userId") Serializable userId
            , @BusinessActionContextParameter(paramName = "money") Integer money);
    boolean commit(BusinessActionContext actionContext);
    boolean rollback(BusinessActionContext actionContext);
    Integer getMoney(Serializable userId);
}