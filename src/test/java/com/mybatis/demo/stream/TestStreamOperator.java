package com.mybatis.demo.stream;

import com.alibaba.fastjson.JSON;
import com.mybatis.demo.entity.Dish;
import com.mybatis.demo.service.impl.DishServiceImpl;
import org.apache.commons.lang.StringUtils;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class TestStreamOperator {

    List<Dish> list;

    @Before
    public void init() {
        list = DishServiceImpl.getDishList();
    }

    @Test
    public void testGroup() {
        Map<String, ArrayList<Integer>> collect5 = list.stream()
                .filter(dish -> StringUtils.isNotEmpty(dish.getName()))
                .collect(Collectors.groupingBy(Dish::getName,
                        Collectors.mapping(Dish::getCalories, Collectors.toCollection(ArrayList::new))));
        for (Map.Entry<String, ArrayList<Integer>> map : collect5.entrySet()) {
            System.out.println(map.getKey() + "---" + map.getValue());
        }
    }


    // filter  过滤符合条件的元素(无状态的中间操作)
    @Test
    public void testFilter() {
        list.stream()
                .filter(dish -> dish.getCalories() > 300)
                .forEach(dish -> System.out.println(JSON.toJSONString(dish, true)));

    }

    // peek  对传入的参数做消费操作(无状态的中间操作)
    @Test
    public void testPeek() {
        list.stream()
                // peek 和 forEach类似, peek是无状态的中间操作  forEach是终端操作,forEach之后不会出现其他操作
                .peek(dish -> System.out.println(JSON.toJSONString(dish.getName(), true)))
                .forEach(dish -> System.out.println(JSON.toJSONString(dish, true)));
    }

    // map 应用到每个元素上，并将其映射成一个新的元素
    @Test
    public void testMap() {
        list.stream()
                .filter(dish -> StringUtils.isNotBlank(dish.getName()))
                .map(Dish::getName)
                .forEach(dish -> System.out.println(JSON.toJSONString(dish, true)));
    }


    public void testFlatMap() {

    }


    // limit 截取某段元素(有状态的中间操作)
    @Test
    public void testLimit() {
        list.stream()
                // limit 取出前2条数据
                // 如果数据源是有序的就会按照顺序取出前2条, 无序的就随便取2条
                .limit(2)
                .forEach(dish -> System.out.println(JSON.toJSONString(dish, true)));
    }

    // skip 跳过某段元素(有状态的中间操作)
    @Test
    public void testSkip() {
        list.stream()
                // skip 跳过前3条数据 和 limit相反
                .skip(3)
                .forEach(dish -> System.out.println(JSON.toJSONString(dish, true)));
    }

    // distinct 去重复元素的操作(有状态的中间操作)
    @Test
    public void test() {


    }

}
