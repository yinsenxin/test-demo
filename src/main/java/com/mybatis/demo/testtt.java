package com.mybatis.demo;


import org.apache.commons.lang.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class testtt {
    public static void main(String[] args) throws Throwable {

        TestJson testJson = new TestJson();
        testJson.setName("A");
        testJson.setDay(null);

        TestJson testJson1 = new TestJson();
        testJson1.setName("B");
        testJson1.setDay("BBB");

        List<TestJson> list = new ArrayList<>();

        list.add(testJson);
        list.add(testJson1);

        List<String> collect = list.stream()
                .map(TestJson::getDay)
                .filter(StringUtils::isNotEmpty)
                .collect(Collectors.toList());

        collect.forEach(System.out::println);

    }
}


class  TestJson{

    String name;

    String day;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }
}


