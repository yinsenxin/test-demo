package com.mybatis.demo.test;


import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class TestClass {
    public static void main(String[] args) throws Exception{

        // 1. 通过 类对象 获取类的对象
        Class<?> aClass = Class.forName("com.mybatis.demo.test.Student");

        // 创建一个无参的 Student对象
        Object o = aClass.newInstance();
        // 创建一个有参的 Student对象
        Constructor<?> constructor = aClass.getConstructor(String.class, int.class);
        Object o1 = constructor.newInstance("Lisi", 12);

        // 获取 Student中的study方法
        Method study = aClass.getDeclaredMethod("study");
        // 设置私有的study方法 为可访问方法
        study.setAccessible(true);
        // 使用该方法
        study.invoke(o1);

        // 获取 Student 中的study1方法
        Method study1 = aClass.getDeclaredMethod("study1", String.class);
        // 设置私有的study1方法 为可访问方法
        study1.setAccessible(true);
        // 使用方法
        Object result = study1.invoke(o1, "java");
        System.out.println(result);
        // 获取 Student中name属性
        Field field = aClass.getDeclaredField("name");
        // 设置私有的name属性 为可访问属性
        field.setAccessible(true);
        System.out.println(field.get(o1));
        // 给name属性 赋值
        field.set(o1, "ssss");
        System.out.println(field.get(o1));
    }

}
class Student {
    private String name;
    private int age;
    public Student() {
        System.out.println("Student()");
    }
    public Student(String name, int age) {
        this.name = name;
        this.age = age;
        System.out.println("Student(\"name, age\") ");
    }
    private void study(){
        System.out.println("学习 方法");
    }
    private int study1(String couce){
        System.out.println("couce");
        return 100;
    }
}
