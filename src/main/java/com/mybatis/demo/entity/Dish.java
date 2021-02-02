package com.mybatis.demo.entity;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

import java.util.stream.IntStream;

/**
 * @author 尹森鑫
 */

public class Dish {
    /**
     * 名称
     */
    private  String name;
    /**
     * 是否为素食
     */
    private  boolean vegetarian;
    /**
     * 卡路里
     */
    private  Integer calories;
    /**
     * 类型
     */
    private  Dish.Type type;

    // 测试方法引用
    public String getGroupCalories(){
        if (this.calories <= 400){
            return "低热量";
        } else if (this.calories <= 700){
            return "中热量";
        } else return "高热量";
    }

    // 测试方法引用 (质数与非质数)
    public static boolean isPrime (int candidate){
        int candidateRoot = (int) Math.sqrt((double) candidate);
        return IntStream.rangeClosed(2, candidateRoot)
                .noneMatch(i -> candidate % i == 0);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Dish dish = (Dish) o;

        return new EqualsBuilder()
                .append(vegetarian, dish.vegetarian)
                .append(name, dish.name)
                .append(calories, dish.calories)
                .append(type, dish.type)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(name)
                .append(vegetarian)
                .append(calories)
                .append(type)
                .toHashCode();
    }

    public enum Type{ MEAT, FISH, OTHER}

    @Override
    public String toString() {
        return "Dish{" +
                "name='" + name + '\'' +
                ", vegetarian=" + vegetarian +
                ", calories=" + calories +
                ", type=" + type +
                '}';
    }


    public Dish() {
    }

    public Dish(String name, boolean vegetarian, Integer calories, Type type) {
        this.name = name;
        this.vegetarian = vegetarian;
        this.calories = calories;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isVegetarian() {
        return vegetarian;
    }

    public void setVegetarian(boolean vegetarian) {
        this.vegetarian = vegetarian;
    }

    public Integer getCalories() {
        return calories;
    }

    public void setCalories(Integer calories) {
        this.calories = calories;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }
}
