package com.mybatis.demo.stream;

import com.alibaba.fastjson.JSON;
import com.mybatis.demo.entity.Dish;
import com.mybatis.demo.service.impl.DishServiceImpl;
import org.junit.Before;
import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 常见的预定义收集器使用
 */
public class StreamCollector {



    List<Dish> list;

    @Before
    public void init(){
        list = DishServiceImpl.getDishList();
    }

    /**
     *  收集
     */
    @Test
    public void toList(){
        List<Dish> collect = list.stream().filter(dish -> dish.getCalories() > 400)
                .collect(Collectors.toList());
        System.out.println(JSON.toJSONString(collect, true));
    }

    /**
     * 分组
     */
    @Test
    public void toGroup(){
        Map<Integer, List<Dish>> collect = list.stream()
                .collect(Collectors.groupingBy(Dish::getCalories));

        System.out.println(JSON.toJSONString(collect, true));
    }

    /**
     * 分区
     */
    @Test
    public void partition(){
        Map<Boolean, List<Dish>> collect = list.stream()
                .collect(Collectors.partitioningBy(Dish::isVegetarian));
        System.out.println(JSON.toJSONString(collect, true));
    }

    @Test
    public void testReduce(){

        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);

        int sum = list.stream().mapToInt(Integer::intValue).sum();
        long count = list.stream().mapToDouble(Integer::doubleValue).count();

        System.out.println(sum + "  " + count);

    }
}
