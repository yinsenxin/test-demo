package com.mybatis.demo.java8.interfaces;


import java.io.PrintStream;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

public class TestInterface {
    public static void main(String[] args) {
        I1 i0 = new I1() {
            @Override
            public void test(As a) {
                a.method();
            }
        };
        I1 i1 = (As as) -> {as.m1();};
        i1.test(new As());

        I1 i2 = As::method;

        I2 i3 = (As a, String b) -> a.method(b);
        I2 i4 = As::method;

        I3  i5 = (As a, String b, String c) -> a.method(b,c);
        I3  i6 = As::method;

        List<String> list = Arrays.asList("123", "456");
        list.forEach(new Consumer<String>() {
            @Override
            public void accept(String s) {
                System.out.println(s);
            }
        });

        list.forEach(s -> Integer.parseInt(s));
        list.forEach(Integer::parseInt);


    }
}

interface I1{
    void test (As a);
}

interface I2{
    void test (As a, String s);
}

interface I3{
    void test (As a, String s, String b);
}

class As {
    public  void m1(){
        System.out.println("m1m1m1m1m1m1m");
    }
    public void method(){
        System.out.println(" method() ");
    }
    public void method(String s){
        System.out.println(" method(String s)");
    }
    public void method(String s1, String s2){
        System.out.println(" method(String s1, String s2) ");
    }
}










































































