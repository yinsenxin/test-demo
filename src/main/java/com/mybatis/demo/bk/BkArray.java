package com.mybatis.demo.bk;

import java.util.Arrays;

/**
 * @author yinsenxin
 */
public class BkArray {
    public static void main(String[] args) {

        int[] array = {1, 2, 3, 4, 5};
        int[] newArray = Arrays.copyOfRange(array, 0, array.length);
        System.out.println(Arrays.toString(newArray));
    }

}
