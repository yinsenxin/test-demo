package com.mybatis.demo.test;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.TreeMap;

public class AutoTestEngine {

    public static void main(String[] args) throws Exception {
        autoTest("com.mybatis.demo.test.ClassA");
    }
    //根据className,自动创建对象, 并按照Tests标注, 按顺序, 用相应的参数, 执行每个需要测试的方法,
    public static void autoTest(String className) throws Exception{
        //创建对象
        Class c = Class.forName(className);
        Object o = c.newInstance();

        TreeMap<Integer, Method> map = new TreeMap<>();
        //拿到c类中所有公开方法
        Method[] ms = c.getMethods();

        for(Method m : ms){
            //拿到添加了Test标注的方法中 order属性
            if (m.isAnnotationPresent(Tests.class)){
                Tests t = m.getAnnotation(Tests.class);
                int order = t.order();
                //利用TreeMap key:order value:Method
                map.put(order, m);
            }
        }

        //遍历TreeMap的值. 依次调用每个方法
        Collection<Method> values = map.values();
        for(Method m : values){
            Tests t =  m.getAnnotation(Tests.class);
            String p = t.parameter();
            Object result = m.invoke(o, p);
            System.out.println(result);
        }

    }

}

class ClassA{
    @Tests(order=2,parameter="Liucy")
    public String ma(String s){
        return "ma  "+s;
    }

    @Tests(order=1,parameter="Huxz")
    public String mb(String s){
        return "mb  "+s;
    }

    @Tests(order=4,parameter="Suns")
    public String mc(String s){
        return "mc  "+s;
    }
    @Tests(order=3,parameter="Liangb")
    public String md(String s){
        return "md  "+s;
    }

    public String me(String s){
        return "me  "+s;
    }
}

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@interface Tests{
    int order();
    String parameter();
}
