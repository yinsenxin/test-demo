package com.mybatis.demo.bk;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * @author yinsenxin
 */
public class BkArray {
    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException {
        Foo foo = new Foo();
        // 任何一个类都是Class的实例对象, 这个实例对象有三种表达方式
        // 第一种表达方式 --> 实际表达了任何一个类 都有一个隐含的静态成员变量class
        Class c1 = Foo.class;
        // 第二种表达方式 --> 对已知对象使用getClass()方法
        Class c2 = foo.getClass();
        Class c3 = Class.forName("com.mybatis.demo.bk.Foo");

        List<Foo> list = new ArrayList<>();

        Foo foo1 = new Foo();
        foo1.setName("zs");
        foo1.setCode("123456");
        Foo foo2 = new Foo();
        foo2.setName("root");
        foo2.setCode("000000");
        list.add(foo1);
        list.add(foo2);


        for (Foo obt : list) {
            Class aClass = obt.getClass();
            // 获取方法
            Method method = aClass.getMethod("getName");
            try {
                Object invoke = method.invoke(obt);
                if (invoke instanceof String) {
                    System.out.println("String------------" + invoke);
                }
                if (invoke instanceof Integer){
                    System.out.println("-----------" + invoke);
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }


        }


    }
}


class Foo{

    private String name;
    private String code;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void print (){
        System.out.println("foo -------");
    }

}
















