package com.mybatis.demo.exercise;

import java.util.*;

/**
 *
 * @author yinsenxin
 */
public class ArrayRemoveSame {
    public static void main(String[] args) {

        // 一, 定义一个基本类型的数组
        int [] array = {11,22,33,44,11,22,33,44,1,2};

        // 1.使用HashSet  ps:无法保证元素的有序性
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < array.length; i++) {
            set.add(array[i]);
        }
        System.out.println("使用HashSet去重:    "+ set);

        // 2.使用LinkedHashSet ps:可以保证添加到集合的顺序
        Set<Integer> sets = new LinkedHashSet<>();
        for (int i = 0; i < array.length; i++) {
            sets.add(array[i]);
        }
        System.out.println("使用LinkedHashSet去重: "+ sets);

        // 3.将数组转换成List 通过contains判断进行去重
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < array.length; i++) {
            if (!list.contains(array[i])){
                list.add(array[i]);
            }
        }
        System.out.println("使用List去重:    "+list);

        // 二, 当对象类型的数组 例如: String Integer ...可以使用Arrays.asList(T... t)直接将数组转换成集合
        String [] str = {"abc", "abc", "ABC", "ABC" ,"xyz"};
        List<String> list1 = Arrays.asList(str);
        Set<String> set1 = new LinkedHashSet<>(list1);
        System.out.println(set1);
    }
}
