package com.mybatis.demo.exercise;

import java.util.Arrays;

public class ArraysMethod {
    public static void main(String[] args) {

        int [] array = {1,3,2,7,6,5,4,9};
        int [] a = null;
        int [] b = a;
        boolean equals = Arrays.equals(a, b);
        System.out.println(equals);


    }

}
