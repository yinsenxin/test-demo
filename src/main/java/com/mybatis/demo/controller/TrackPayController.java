package com.mybatis.demo.controller;


import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/track")
public class TrackPayController {

    @PostMapping("/notify")
    void notify(@RequestBody String body) {
        // body 是 接收到的推送内容
        System.out.println(body);
    }
}
