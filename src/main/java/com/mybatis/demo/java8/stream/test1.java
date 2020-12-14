package com.mybatis.demo.java8.stream;

import com.mybatis.demo.entity.Dish;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class test1 {
    public static void main(String[] args) {

        List<Dish> list = Stream.of(
                new Dish("pork", false, -800, Dish.Type.MEAT),
                new Dish("beef", false, -700, Dish.Type.MEAT),
                new Dish("chicken", false, -400, Dish.Type.MEAT),
                new Dish("french fries", true, -530, Dish.Type.OTHER),
                new Dish("rice", true, -3501, Dish.Type.OTHER),
                new Dish("season fruit", true, 120, Dish.Type.OTHER),
                new Dish("pizza", true, -550, Dish.Type.OTHER),
                new Dish("prawns", false, -300, Dish.Type.FISH),
                new Dish("test", false, -450, Dish.Type.FISH),
                new Dish("salmon", false, -450, Dish.Type.FISH),
                new Dish(null, false, null, Dish.Type.FISH),
                new Dish()
        ).collect(Collectors.toList());

        // 求list中元素数量
        long count = list.stream().count();
        Long count2 = list.stream().collect(Collectors.counting());

        // 求list元素中热量的最大值
        OptionalInt max = list.stream().filter(item -> item.getCalories() != null).mapToInt(Dish::getCalories).max();
        Optional<Integer> max1 = list.stream().filter(item -> item.getCalories() != null).map(Dish::getCalories).reduce(Integer::max);
        Optional<Dish> max2 = list.stream().filter(item -> item.getCalories() != null).collect(Collectors.maxBy(Comparator.comparing(Dish::getCalories)));
        Optional<Dish> max3 = list.stream().filter(item -> item.getCalories() != null).max(Comparator.comparing(Dish::getCalories));
        Optional<Dish> max4 = list.stream().filter(item -> item.getCalories() != null).collect(Collectors.reducing(((d1, d2) -> d1.getCalories() > d2.getCalories() ? d1 : d2)));
        Integer max5 = list.stream().filter(item -> item.getCalories() != null).map(Dish::getCalories).reduce(0, Integer::max);
        Integer max6 = list.stream().filter(item -> item.getCalories() != null).collect(Collectors.reducing(0, Dish::getCalories, Integer::max));

        // 求list元素中热量的总和
        Integer sum1 = list.stream().filter(item -> item.getCalories() != null).mapToInt(Dish::getCalories).sum();
        Optional<Integer> sum2 = list.stream().filter(item -> item.getCalories() != null).map(Dish::getCalories).reduce(Integer::sum);
        Integer sum3 = list.stream().filter(item -> item.getCalories() != null).collect(Collectors.summingInt(Dish::getCalories));
        Integer sum4 = list.stream().filter(item -> item.getCalories() != null).collect(Collectors.reducing(0, Dish::getCalories, Integer::sum));

        // 求list元素中热量的平均值
        OptionalDouble average = list.stream().filter(item -> item.getCalories() != null).mapToInt(Dish::getCalories).average();
        Double average2 = list.stream().filter(item -> item.getCalories() != null).collect(Collectors.averagingInt(Dish::getCalories));

        // summarizingInt工厂方法返回的收集器  可以获得 元素的个数,总和,平均值,最大最小值
        IntSummaryStatistics intSummaryStatistics = list.stream().filter(item -> item.getCalories() != null).collect(Collectors.summarizingInt(Dish::getCalories));

        // ================================================= 分组 =================================================//
        // 按照类型分组
        Map<Dish.Type, List<Dish>> collect = list.stream().filter(item -> item.getType() != null).collect(Collectors.groupingBy(Dish::getType));
        // 自定义分组
        Map<String, List<Dish>> collect1 = list.stream().filter(item -> item.getCalories() != null)
                .collect(Collectors.groupingBy(
                        dish -> {
                            if (dish.getCalories() <= 400) {
                                return "diet";
                            } else if (dish.getCalories() <= 700) return "normal";
                            else return "fat";
                        }
                ));
        // 多级分组
        Map<Dish.Type, Map<String, List<Dish>>> collect2 = list.stream().filter(item -> item.getType() != null && item.getCalories() != null)
                .collect(Collectors.groupingBy(Dish::getType, Collectors.groupingBy(
                        dish -> {
                            if (dish.getCalories() <= 400) {
                                return "diet";
                            } else if (dish.getCalories() <= 700) return "normal";
                            else return "fat";
                        }
                )));
        // 按子组收集数据
        Map<Dish.Type, Long> collect3 = list.stream().filter(item -> item.getType() != null)
                .collect(Collectors.groupingBy(Dish::getType, Collectors.counting()));

        Map<Dish.Type, Dish> collect4 = list.stream().filter(item -> item.getType() != null && item.getCalories() != null)
                .collect(Collectors.groupingBy(Dish::getType, Collectors.collectingAndThen(
                        Collectors.maxBy(Comparator.comparingInt(Dish::getCalories)),
                        Optional::get
                )));




    }


}










































































