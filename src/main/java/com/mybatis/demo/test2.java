package com.mybatis.demo;

import com.alibaba.fastjson.JSON;
import com.mybatis.demo.util.EncryptUtil;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

public class test2 {
    public static void main(String[] args) {


        User user = new User();
        user.setAbc(new Date());
        user.setName(null);
        user.setDesc("123");
        String s1 = JSON.toJSONString(user);

        String s = EncryptUtil.encryptBase64(s1, "aaaaaabbbbbbcccc");

        System.out.println(s);
        String endS = EncryptUtil.decryptBase64(s, "aaaaaabbbbbbcccc");

        System.out.println(endS);
    }
}

@Data
@NoArgsConstructor
class User {
    private String name;
    private String desc;
    private Date abc;
}
