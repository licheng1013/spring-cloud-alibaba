package com.demo.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.demo.entity.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author lc
 * @date 2020-12-31
 * @description
 */
@Mapper
public interface UserDao extends BaseMapper<User> {

}