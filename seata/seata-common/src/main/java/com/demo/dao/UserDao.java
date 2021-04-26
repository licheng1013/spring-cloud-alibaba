package com.demo.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.demo.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author lc
 * @date 2021-01-15
 * @description
 */
@Mapper
public interface UserDao extends BaseMapper<User> {

    /**
     * @param userId 用户id
     * @param money 增加金额,如果是负数则减少,否则增加
     * @return 是否修改成功
     */
    @Transactional
    @Update("update user set money=money+#{money} where user_id = #{userId}")
    boolean updateByIdSetMoney(@Param("userId") String userId, @Param("money") Integer money);
}