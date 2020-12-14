package com.mybatis.demo.test;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Method;

public class TestAnnotation {
    public static void main(String[] args) {
        // 通过反射 解析@Test中的内容

        // 获取到 类对象
        Class c = MyClass.class;
        // 获取到所有 方法
        Method[] ms = c.getMethods();

        for(Method m : ms){
            // 判断方法上是否 有Test注解
            if (m.isAnnotationPresent(Test.class)){
                // 获取到Test注解
                Test t = m.getAnnotation(Test.class);
                // 获取到 Test注解的 属性值
                String v1 = t.value();
                int v2 = t.value2();
                System.out.println(m.getName()+" value="+v1+"  value2="+v2);
            }
        }

    }
}

class MyClass{
    @Test
    public void method1(){

    }
    @Test("Huxz")
    public void method2(){

    }
    @Test(value="lisi" , value2 =20)
    public void method3(){

    }
    public void method4(){}
}
@Target(ElementType.METHOD) // 声明该标注 可以在方法上 使用
@Retention(RetentionPolicy.RUNTIME)// 保留到 .class 文件中
@interface Test{
    String value() default "Liucy" ;
    int value2() default 10;
}