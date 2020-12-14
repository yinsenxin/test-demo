package com.mybatis.demo.dao;

import com.mybatis.demo.entity.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserDao {
    List<User> SelectOne(Integer id);
}
