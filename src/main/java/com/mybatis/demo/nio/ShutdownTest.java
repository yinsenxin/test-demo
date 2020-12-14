package com.mybatis.demo.nio;

import java.util.ArrayList;
import java.util.List;

public class ShutdownTest {
    public static void main(String[] args) throws Exception{

    }
    static class Sku{
        private String name;
        private String price;
        public String getPrice(){
            return this.price;
        }

        // 静态 方法
        public static int compartorSku(Sku sku1, Sku sku2){
            return sku1.getPrice().compareTo(sku2.getPrice());
        }

        // 普通方法
        public int compareObject(Sku s1, Sku s2){
            return s1.getPrice().compareTo(s2.getPrice());
        }

        // 普通方法
        public int compareClassInstance(Sku s1){
            return this.getPrice().compareTo(s1.getPrice());
        }

    }

    public void test(){
        List<Sku> skus = new ArrayList<>();
        // 类名::静态方法名
        skus.sort(Sku::compartorSku);
        // 展开
        skus.sort((Sku s1, Sku s2) ->{
            return Sku.compartorSku(s1, s2);
        });

        // 对象::实例方法
        Sku sku = new Sku();
        skus.sort(sku::compareObject);
        // 展开
        skus.sort((Sku s1, Sku s2) -> sku.compareObject(s1, s2));

        // 类名::实例方法
        skus.sort(Sku::compareClassInstance);
        // 展开
        skus.sort((Sku object, Sku s1) -> object.compareClassInstance(s1));

    }

}
