package com.demo.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.demo.config.KeyConfig;
import com.demo.dao.UserDao;
import com.demo.entity.User;
import com.demo.service.UserService;
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
@Service("userService")
@Slf4j
public class UserServiceImpl extends ServiceImpl<UserDao, User> implements UserService {
    @Autowired
    private RedisString redisString;


    /**
     * @param type 1提交,2回滚
     * @date 2021/5/25
     */
    @Transactional
    public boolean updateMoney(BusinessActionContext actionContext,Integer type){
        String xid = actionContext.getXid();
        String v = redisString.get(KeyConfig.USER_KEY+xid);
        if (StrUtil.isBlank(v)) {
            return true;
        }
        Object userId = actionContext.getActionContext("userId");
        Object money = actionContext.getActionContext("money");
        boolean b = updateUser(userId.toString(), (Integer) money, type);
        if (b) {
            redisString.remove(KeyConfig.USER_KEY+xid);
        }
        return b;
    }
    /**
     * @param type 1提交,2回滚,3锁定
     * @date 2021/5/25
     */
    @Transactional
    public boolean updateUser(String userId,Integer money,Integer type){
        User user = getById(userId);
        if(type == 1){ //提交
            user.setFreeze(user.getFreeze()-money);
        } else if (type == 2) {//回滚
            user.setFreeze(user.getFreeze()-money);
            user.setMoney(user.getMoney()+money);
        }else if(type == 3){//锁定资源
            user.setFreeze(user.getFreeze()+money);
            user.setMoney(user.getMoney()-money);
        }
        return user.updateById();
    }

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
//    @Tcc(prefix = KeyConfig.USER_KEY)
    @Transactional
    public Boolean updateMoneyTcc(BusinessActionContext actionContext, Serializable userId, Integer money) {
        String xid = actionContext.getXid();
        log.info("用户服务 xid: {}",xid );
        boolean b = updateUser(userId.toString(), money, 3);
        if(b){ //注解驱动
            redisString.set(KeyConfig.USER_KEY+xid, "1");// 生产由redis实现 //ResultHolder.set(xid, "1");//单机
        }
        return b;
    }

    @Override
//    @Tcc(prefix = KeyConfig.USER_KEY,type = false)
    public boolean commit(BusinessActionContext actionContext) {
         //如果用户在其他地方查询了金额,会出现线程问题
        return updateMoney(actionContext, 1);
    }

    @Override
//    @Tcc(prefix = KeyConfig.USER_KEY,type = false)
    public boolean rollback(BusinessActionContext actionContext) {
        return updateMoney(actionContext, 2);
    }
}