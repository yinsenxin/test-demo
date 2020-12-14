package com.mybatis.demo.controller;


import com.mybatis.demo.entity.User;
import com.mybatis.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/test")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/select")
    public String select(@RequestParam Integer id){
        Optional<List<User>> users = Optional.ofNullable(userService.selectOne(id));
        List<User> users1 = users.get();
        users1.forEach(System.out::println);
        return "123";
    }

}
