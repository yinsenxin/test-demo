package com.mybatis.demo;


import java.text.SimpleDateFormat;
import java.util.*;

public class testtt {
    public static void main(String[] args) throws Throwable {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date1 = sdf.parse("2023-01-15");

        Date date2 = new Date();

        int days = (int) ((date2.getTime() - date1.getTime()) / (1000*3600*24));

        System.out.println(days);
    }
}


class TestJson {

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


