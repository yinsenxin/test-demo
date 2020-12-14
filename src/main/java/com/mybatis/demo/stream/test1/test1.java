package com.mybatis.demo.stream.test1;


import com.mybatis.demo.entity.Dish;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;
enum CaloricLevel {DIET, NORMAL, FAT};
public class test1 {
    public static void main(String[] args) {
        List<Dish> menu = Stream.of(
                new Dish("pork", false, 800, Dish.Type.MEAT),
                new Dish("beef", false, 700, Dish.Type.MEAT),
                new Dish("chicken", false, 400, Dish.Type.MEAT),
                new Dish("french fries", true, 530, Dish.Type.OTHER),
                new Dish("rice", true, 3501, Dish.Type.OTHER),
                new Dish("season fruit", true, 120, Dish.Type.OTHER),
                new Dish("pizza", true, 550, Dish.Type.OTHER),
                new Dish("prawns", false, 300, Dish.Type.FISH),
                new Dish("test", false, 450, Dish.Type.FISH),
                new Dish("salmon", false, 450, Dish.Type.FISH),
                new Dish(null, false, null, Dish.Type.FISH)
        ).collect(Collectors.toList());

        int sum = menu.stream().filter(item -> item.getCalories() != null).mapToInt(Dish::getCalories).sum();
        Integer reduce = menu.stream().filter(item -> item.getCalories() != null).map(Dish::getCalories).reduce(0, Integer::sum);
        Integer collect8 = menu.stream().filter(item -> item.getCalories() != null).map(Dish::getCalories).reduce(0, Integer::sum);
        Map<Dish.Type, Long> collect9 = menu.stream().filter(item -> item.getType() != null).collect(Collectors.groupingBy(Dish::getType, Collectors.counting()));



        Map<String, List<Dish>> collect = menu.stream().collect(Collectors.groupingBy(
                dish -> {
                    if (dish.getCalories() <= 400) {
                        return "1";
                    } else if (dish.getCalories() <= 700) {
                        return "2";
                    } else {
                        return "3";
                    }
                }

        ));



        List<String> collect4 = menu.stream().map(Dish::getName).collect(Collectors.toList());

        List<String> collect3 = menu.stream().collect(Collectors.mapping(Dish::getName, Collectors.toList()));

        Map<Dish.Type, List<Dish>> collect6 = menu.stream().collect(Collectors.groupingBy(Dish::getType));

        System.out.println(getDishName(menu));


        Function<Dish, Dish> f1 = dish -> {

            Dish dish1 = new Dish();
            BeanUtils.copyProperties(dish, dish1);
            dish1.setCalories(dish.getCalories() + 100);
            return dish1;
        };

        List<Dish> collect10 = menu.stream().filter(dish -> dish.getCalories() != null).map(f1).collect(Collectors.toList());
        collect10.forEach(System.out::println);
//
//        long count = menu.stream().collect(Collectors.counting());
        Map<Dish.Type, List<Dish>> groupingByType = getGroupingByType(menu);

        //List<String> openIds = list.stream().collect(Collectors.collectingAndThen(Collectors.toCollection(() -> new TreeSet<>(Comparator.comparing(item -> item))), ArrayList::new));
        //openIds.forEach(System.out::println);

        // 卡路里大于300 的3个菜肴的名字
        List<String> calories = getCalories(menu);
        // 卡路里大于300 的菜肴的数量
       // long caloriesCount = getCaloriesCount(menu);

        // 筛选前两个荤菜(有序集合,会按照顺序 无序集合,无顺序)
       // List<Dish> meat = getMeat(menu);

        // 筛选跳过前两个荤菜(有序集合,会按照顺序 无序集合,无顺序)
      //  List<Dish> meat2 = getMeat2(menu);

        // 提取菜肴的名字
     //  List<String> name = getName(menu);






    }

    //
    public  static List<String> get(){
        // 移出元素
        // menu.removeIf(item-> item.getName().equals("1"));
//        List<Integer> list = new ArrayList<>();
//        list.add(1);
//        list.add(22);
//        list.add(3);
//        list.add(5);
//        list.add(null);
//
//
//
//        list.sort(Comparator.comparing(item -> item, Comparator.nullsLast(Integer::compareTo)));
//        list.forEach(System.out::println);
        return null;
    }

    /**
     * 获取菜单列表 的最大热量
     *
     * @param menu
     * @return
     */
    public static String getCaloriesMax(List<Dish> menu){

        OptionalInt max1 = menu.stream().filter(dish -> dish.getCalories() != null)
                .mapToInt(Dish::getCalories).max();

        Optional<Integer> reduce = menu.stream()
                .filter(dish -> dish.getCalories() != null)
                .map(Dish::getCalories)
                .reduce(Integer::max);

        Optional<Dish> collect = menu.stream()
                .filter(dish -> dish.getCalories() != null)
                .collect(Collectors.maxBy(Comparator.comparingInt(Dish::getCalories)));

        Optional<Dish> max = menu.stream().filter(dish -> dish.getCalories() != null)
                .max(Comparator.comparing(Dish::getCalories));

        Optional<Dish> collect1 = menu.stream().filter(dish -> dish.getCalories() != null)
                .collect(Collectors.reducing((x, y) -> x.getCalories() > y.getCalories() ? x : y));


        return max.get().getName();
    }

    /**
     * 获取菜单列表中的总热量
     *
     * @param menu
     * @return
     */
    public static Integer getCaloriesSum(List<Dish> menu){
        Integer collect = menu.stream().filter(dish -> dish.getCalories() != null)
                .collect(Collectors.summingInt(Dish::getCalories));

        Integer collect1 = menu.stream().filter(dish -> dish.getCalories() != null)
                .collect(Collectors.reducing(0, Dish::getCalories, (i, j) -> i + j));

        Integer collect2 = menu.stream().filter(dish -> dish.getCalories() != null)
                .collect(Collectors.reducing(0, Dish::getCalories, Integer::sum));

        return collect;
    }

    /**
     * 获取菜单列表中的热量的平均值
     *
     * @param menu
     * @return
     */
    public static double getCaloriesAveraging(List<Dish> menu){
        Double result = menu.stream().filter(dish -> dish.getCalories() != null)
                .collect(Collectors.averagingInt(Dish::getCalories));

        return result;
    }

    /**
     * 获取菜单列表中热量的个数, 总和,平均值, 最大值, 最小值
     *
     * @param menu
     * @return
     */
    public static DoubleSummaryStatistics getCaloriesAll(List<Dish> menu){

        DoubleSummaryStatistics collect = menu.stream().filter(dish -> dish.getCalories() != null)
                .collect(Collectors.summarizingDouble(Dish::getCalories));

        return collect;
    }

    /**
     * 将菜肴名称连接起来
     *
     * @param menu
     * @return
     */
    public static String getDishName(List<Dish> menu){
        String collect = menu.stream()
                .map(Dish::getName)
                .collect(Collectors.joining(", "));
        return collect;
    }


    // 获取卡路里大于300的 3个菜肴的名称
    public static List<String> getCalories(List<Dish> menu){
        return menu.stream().filter(dish -> dish.getCalories() > 300)
                .limit(3).map(Dish::getName).collect(Collectors.toList());
    }
    // 卡路里大于300 的菜肴的数量
    public static long getCaloriesCount(List<Dish> menu){
        return menu.stream().filter(dish -> dish.getCalories() != null && dish.getCalories() > 300).map(Dish::getName).count();
    }
    // 筛选前两个荤菜(有序集合,会按照顺序 无序集合,无顺序)
    public static List<Dish> getMeat(List<Dish> menu){
        return menu.stream().filter(dish -> dish.getType() == Dish.Type.MEAT).limit(2).collect(Collectors.toList());
    }
    // 筛选跳过前两个荤菜(有序集合,会按照顺序 无序集合,无顺序)
    public static List<Dish> getMeat2(List<Dish> menu){
        return menu.stream().filter(dish -> dish.getType() == Dish.Type.MEAT).skip(2).collect(Collectors.toList());
    }
    // 提取菜肴的名称
    public static List<String> getName(List<Dish> menu){
        return menu.stream().filter(dish -> StringUtils.isNotBlank(dish.getName())).map(Dish::getName).collect(Collectors.toList());
    }

    public static Map<Dish.Type, List<Dish>> getGroupingByType(List<Dish> menu){
        Map<Dish.Type, List<Dish>> collect = menu.stream().collect(Collectors.groupingBy(Dish::getType));
        return collect;
    }



    public void test(List<Dish> menu){
        // 根据卡路里进行分组
        Map<Integer, List<Dish>> collect2 = menu.stream().collect(Collectors.groupingBy(Dish::getCalories));

        // 根据卡路里和名字进行分组
        Map<String, List<Dish>> collect3 = menu.stream().collect(Collectors.groupingBy(dish -> dish.getName() + "-" + dish.getCalories()));
        collect3.forEach((key, value)->{
            System.out.println(key);
            System.out.println(value.toString());
        });
        // 根据卡路里分组 映射Name
        Map<Integer, List<String>> collect4 = menu.stream().
                collect(Collectors.groupingBy(Dish::getCalories, Collectors.mapping(Dish::getName, Collectors.toList())));

        // 提取Dish对象中 卡路里为 key, 名称为value 组成Map集合
        // 其中(key1,key2)->key2, 当有重复的key出现时, 取最后一个key:value的结果
        // 其中(key1,key2)->key1, 当有重复的key出现时, 取第一个出现的key:value的结果
        Map<Integer, String> collect5 = menu.stream().collect(Collectors.toMap(Dish::getCalories, Dish::getName, (key1, key2) -> key1));
//        collect5.forEach(
//                (key,value)->{
//                    System.out.println(key + " " + value);
//                }
//        );
    }
}

