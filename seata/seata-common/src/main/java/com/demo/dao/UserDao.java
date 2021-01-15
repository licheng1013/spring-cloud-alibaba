package com.demo.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.demo.entity.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author lc
 * @date 2021-01-15
 * @description
 */
@Mapper
public interface UserDao extends BaseMapper<User> {

}