package com.demo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.demo.dao.UserDao;
import com.demo.entity.User;
import com.demo.service.UserService;
import io.seata.rm.tcc.api.BusinessActionContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.Serializable;

/**
 * @author lc
 * @date 2021-01-15
 * @description
 */
@Service("userService")
@Slf4j
public class UserServiceImpl extends ServiceImpl<UserDao, User> implements UserService {

    @Override
    public Boolean updateMoney(Serializable userId, Integer money) {
        User byId = getById(userId);
        if (byId == null) {
            throw new RuntimeException("用户未找到");
        }
        byId.setMoney(byId.getMoney()-money);
        return byId.updateById();
    }

    @Override
    public Boolean updateMoneyTcc(BusinessActionContext actionContext, Serializable userId, Integer money) {
        String xid = actionContext.getXid();
        log.info("xid: {}",xid );

        User byId = getById(userId);
        if (byId == null) {
            throw new RuntimeException("用户未找到");
        }
        byId.setMoney(byId.getMoney()-money);
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