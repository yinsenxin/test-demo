package com.mybatis.demo.service.impl;

import com.mybatis.demo.dao.UserDao;
import com.mybatis.demo.entity.User;
import com.mybatis.demo.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public List<User> selectOne(int id) {

        return userDao.SelectOne(id);
    }

}
