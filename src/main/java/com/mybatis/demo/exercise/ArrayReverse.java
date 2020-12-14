package com.mybatis.demo.exercise;

import com.mybatis.demo.entity.User;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author yinsenxin
 */
public class ArrayReverse {
    public static void main(String[] args) {

        int [] nums = {1,2,32,4};
        // 使用java8 Stream流 将数组转换成 集合
        List<Integer> collect = Arrays.stream(nums).boxed().collect(Collectors.toList());
        // 默认升序
        Collections.sort(collect);
        System.out.println("升序 :  " + collect);
        // 通过 reverse 翻转集合中的元素, 实现降序
        Collections.reverse(collect);
        System.out.println("降序 :  " + collect);


    }
}
