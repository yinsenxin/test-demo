package com.mybatis.demo.controller;


import com.mybatis.demo.entity.User;
import com.mybatis.demo.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/test")
public class UserController {
    private static final Logger log = LoggerFactory
            .getLogger(UserController.class);

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
