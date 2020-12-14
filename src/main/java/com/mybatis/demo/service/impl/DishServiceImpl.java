package com.mybatis.demo.service.impl;

import com.mybatis.demo.entity.Dish;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DishServiceImpl {

    public static List<Dish> getDishList(){

        return Stream.of(
                new Dish("pork", false, 800, Dish.Type.MEAT),
                new Dish("beef", false, 700, Dish.Type.MEAT),
                new Dish("chicken", false, 400, Dish.Type.MEAT),
                new Dish("french fries", true, 530, Dish.Type.OTHER),
                new Dish("rice", true, 350, Dish.Type.OTHER),
                new Dish("season fruit", true, 120, Dish.Type.OTHER),
                new Dish("pizza", true, 550, Dish.Type.OTHER),
                new Dish("prawns", false, 300, Dish.Type.FISH),
                new Dish(null, false, 450, Dish.Type.FISH),
                new Dish("salmon", false, 450, Dish.Type.FISH),
                new Dish("test", false, null, Dish.Type.FISH)
        ).collect(Collectors.toList());
    }


}
