package com.mybatis.demo;

public class testException {
    public static void main(String[] args) {




    }

    public static void method(){
        try{
            method1();
        }catch(Exception e){
            e.printStackTrace();
            System.out.println("123123");
        }
    }

    public static void method1(){
        try{
            System.out.println(1/0);
        }catch(Exception e){
            e.printStackTrace();
            System.out.println("-------------------");
            throw new RuntimeException("e");
        }
    }


}
