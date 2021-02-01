package com.mybatis.demo;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.mybatis.demo.entity.Dish;
import com.mybatis.demo.util.EncryptUtil;
import com.ygjt.cargo.common.utils.excel.Excel;
import com.ygjt.cargo.dao.SystemDictionaryDao;
import lombok.Data;
import lombok.val;
import org.apache.commons.lang.StringUtils;
import org.json.JSONString;
import sun.security.util.AuthResources_it;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class testtt {
    public static void main(String[] args) throws Throwable {

//        int j;
//        boolean flag;
//        for (int i = 2; i < 1000; i++) {
//            flag = false;
//            for (j = 2; j < i; j++) {
//                if (i % j == 0) {
//                    flag = true;
//                    break;
//                }
//            }
//            if (flag == false) {
//                System.out.print(i + "    ");
//            }
//
//        User user = new User();
//
//        user.setName("张三");
//        user.setDesc("123");
//        String string = JSON.toJSONString(user);
//
//        JSONObject jsonObject = JSON.parseObject(string);
//        String name = jsonObject.getString("name2");
//        System.out.println(name);
//
//        String s = "";
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("pageSize", 10);
        jsonObject.put("pageNum", 1);
        String s = jsonObject.toJSONString();
        System.out.println(EncryptUtil.encryptBase64(s, "K0d3ry7f!ZW9qA4f"));



        String string = "leaveMala";

        System.out.println(string.substring(5));

//        System.out.println(json);

    }

}



