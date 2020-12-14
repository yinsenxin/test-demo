package com.mybatis.demo.exercise;


import java.util.Arrays;
import java.util.List;
import java.util.OptionalInt;
import java.util.stream.Collectors;

/**
 * @author yinsenxin
 */
public class ArrayMaxAndMin {
    public static void main(String[] args) {

        //定义一个 int 类型的 一维数组
        int [] array = {11,2,3,4,5,6};
        String [] str = {"JAVA", "操作系统", "数据库系统原理", "计算机系统结构"};
        // 方法一:  使用排序, 默认升序
        Arrays.sort(array);
        System.out.println("最大值: "+array[array.length-1] +" 最小值: " + array[0]);

        // 方法二: 将数组转换成list, 通过流获取 最大/小 值
        List<Integer> list = Arrays.stream(array).boxed().collect(Collectors.toList());
        OptionalInt min = list.stream().mapToInt(item -> item).min();
        OptionalInt max = list.stream().mapToInt(item -> item).max();
        if (min.isPresent()){
            System.out.println(min.getAsInt());
        }
        if (max.isPresent()){
            System.out.println(max.getAsInt());
        }

    }

}
