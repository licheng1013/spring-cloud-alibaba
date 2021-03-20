package com.demo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.demo.aop.Tcc;
import com.demo.config.KeyConfig;
import com.demo.dao.UserDao;
import com.demo.entity.User;
import com.demo.service.UserService;
import com.demo.util.RedisString;
import io.seata.rm.tcc.api.BusinessActionContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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
    @Autowired
    private RedisString redisString;

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
    @Tcc(prefix = KeyConfig.USER_KEY)
    public Boolean updateMoneyTcc(BusinessActionContext actionContext, Serializable userId, Integer money) {
        String xid = actionContext.getXid();
        log.info("用户服务 xid: {}",xid );
        User user = getById(userId);
        if (user == null) {
            throw new RuntimeException("用户未找到");
        }
        if (user.getMoney() < money) {
            throw new RuntimeException("金额不足");
        }
        user.setMoney(user.getMoney()-money); //冻结金额并添加到冻结金额里面
        user.setFreeze(user.getFreeze()+money);
        boolean b = user.updateById();
//        if(b){ //注解驱动
//            redisString.set(xid, "1");// 生产由redis实现
//            //ResultHolder.set(xid, "1");//单机
//        }
        return b;
    }

    @Override
    @Tcc(prefix = KeyConfig.USER_KEY,type = false)
    public boolean commit(BusinessActionContext actionContext) {
        String xid = actionContext.getXid();
        Object userId = actionContext.getActionContext("userId");
        Object money = actionContext.getActionContext("money");
        log.info("用户服务 commit,用户id: {},用户扣除金额: {},xid: {}",userId,money,xid);
//        String v = redisString.get(xid);//ResultHolder.get(xid) //注解驱动
//
//        if (StrUtil.isBlank(v)) {
//            log.info("用户服务空提交");
//            return true;
//        }
        User user = getById(userId.toString()); //如果用户在其他地方查询了金额,会出现线程问题
        user.setFreeze(user.getFreeze()-(Integer)money); //把冻结金额扣除掉
        boolean b = user.updateById();
//        if(b){ //注解驱动
//            redisString.remove(xid);// 生产由redis实现
//            //ResultHolder.remove(xid); //删除xid,单机
//        }
        return b;
    }

    @Override
    @Tcc(prefix = KeyConfig.USER_KEY,type = false)
    public boolean rollback(BusinessActionContext actionContext) {
        String xid = actionContext.getXid();
        Object userId = actionContext.getActionContext("userId");
        Object money = actionContext.getActionContext("money");
        log.info("用户服务 rollback,用户id: {},用户扣除金额: {},xid: {}",userId,money,xid);
//        String v = redisString.get(xid);//ResultHolder.get(xid) //注解驱动
//
//        if (StrUtil.isBlank(v)) {
//            log.info("用户服务空回滚");
//            return true;
//        }
        User user = getById(userId.toString());
        user.setFreeze(user.getFreeze()-(Integer)money); //把冻结金额扣除掉,并补回金额
        user.setMoney(user.getMoney()+(Integer)money);
        boolean b = user.updateById();
//        if(b){ //注解驱动
//            redisString.remove(xid);// 生产由redis实现
//            //ResultHolder.remove(xid); //删除xid
//        }
        return b;
    }
}