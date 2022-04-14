package com.mybatis.demo.stream;

import org.apache.commons.lang.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class testPredicate {

    public static void main(String[] args) {

        List<Apple> apples = appleList();



        List<Apple> list = filterApples(apples, item -> item.getColor().equals("green"));

        List<Apple> green = apples.stream().filter((Apple a) -> StringUtils.equals("green", a.getColor())).collect(Collectors.toList());

        System.out.println(list);

    }

    public static List<Apple> appleList(){

        Apple apple = new Apple(50.0, "green", 100);
        Apple apple1 = new Apple(150.23, "green", 200);
        Apple apple2 = new Apple(80.22, "red", 350);
        Apple apple3 = new Apple(30.11, "red", 435);
        Apple apple4 = new Apple(1000.0, "blue", 123);

        return new ArrayList<Apple>(){{
            add(apple);
            add(apple1);
            add(apple2);
            add(apple3);
            add(apple4);
        }};
    }

    public static List<Apple> filterApples(List<Apple> dataList, Predicate<Apple> p){
        List<Apple> list = new ArrayList<>();
        for (Apple apple : dataList) {
            if (p.test(apple)){
                list.add(apple);
            }
        }
        return list;
    }


}

class Apple{

    private Double weight;

    private String color;

    private Integer value;

    @Override
    public String toString() {
        return "Apple{" +
                "weight=" + weight +
                ", color='" + color + '\'' +
                ", value=" + value +
                '}';
    }

    public Apple() {

    }

    public Apple(Double weight, String color, Integer value) {
        this.weight = weight;
        this.color = color;
        this.value = value;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }
}



 interface Predicate<T>{

    boolean test (T t);

}

