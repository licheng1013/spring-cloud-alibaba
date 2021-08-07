package com.demo;

import cn.org.atool.fluent.mybatis.model.StdPagedList;
import com.demo.entity.User;
import com.demo.mapper.UserMapper;
import com.demo.wrapper.UserQuery;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.Serializable;
import java.util.Date;

/**
 * @author lc
 * @since 2021/8/5
 */
@SpringBootTest
@Slf4j
public class FluentAppTest {



    @Autowired
    private UserMapper userMapper;
    @Test
    public void  TestAdd() throws InterruptedException {
        for (int i = 0; i < 100; i++) {
            User userEntity = new User();
            userEntity.setMoney(20);
            userEntity.setCreateTime(new Date());
            Serializable save = userMapper.save(userEntity);
            Thread.sleep(300);
            log.info("插入: {}",save);
        }
    }


    @Test
    public void TestFluentMybatis(){
        User userEntity = new User();
        userEntity.setMoney(20);
        userEntity.setCreateTime(new Date());
        Serializable save = userMapper.save(userEntity);
        log.info("插入: {}",save);

        User entity = userMapper.findById(userEntity.getUserId());
        log.info("查询: {}",entity);

        entity.setMoney(50);
        int updateStatus = userMapper.updateById(entity);
        log.info("更新: {}",updateStatus);

        int deleteStatus = userMapper.deleteById(userEntity.getUserId());
        log.info("删除: {}",deleteStatus);
    }

    @Test void  TestLimit(){
        UserQuery u = new  UserQuery();
        u.where.money().eq("50").end();
        u.limit(1);

        User one = userMapper.findOne(u);
        log.info("查询: {}",one);
    }

    @Test void  TestPage(){
        UserQuery u = new  UserQuery();
        u.limit(0,10);
        u.orderBy.createTime().desc();

        StdPagedList<User> list = userMapper.stdPagedEntity(u);
        log.info("查询: {}",list.getData());
    }


    @Test
    public void test2(){
        System.out.println("Hello Test");
    }

}
