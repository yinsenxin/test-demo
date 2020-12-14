package com.mybatis.demo.exercise;

import org.apache.commons.lang.ArrayUtils;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;

public class TestArray2List {
    public static void main(String[] args) {
        int [] array = {1,2,3,4,5};
        int [] array2 = {};
        // 使用apache封装的数组工具类 ArrayUtils将int [] --> Integer []
        Integer[] integers2 = Arrays.stream(array).boxed().toArray(Integer[]::new);

        /*Integer[] integers = ArrayUtils.toObject(array2);
        System.out.println(Arrays.toString(integers));*/

    }



}
