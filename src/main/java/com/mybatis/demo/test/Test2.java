package com.mybatis.demo.test;


import org.apache.commons.lang.ArrayUtils;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

public class Test2 {

    public static void main(String[] args) {

        int [] a = {1, 2, 3};
        int [] b = {4,5,6,7};
        Integer[] integers2 = Arrays.stream(a).boxed().toArray(Integer[]::new);

        // remove 移除指定下标上的元素
        int[] remove = ArrayUtils.remove(a, 0);
        System.out.println(Arrays.toString(remove));
        System.out.println( );

        // removeElement 移除指定元素
        int[] ints = ArrayUtils.removeElement(a, 12);
        System.out.println(Arrays.toString(ints));

        // 合并两个数组
        /**
         *  底层利用System.arraycopy 进行copy 数组
         */

        int[] ints1 = ArrayUtils.addAll(a, b);
        System.out.println(Arrays.toString(ints1));

        int[] add = ArrayUtils.add(a, 3, 111);
        System.out.println(Arrays.toString(add));
        int[] ints2 = addAddArray(a, b);

        System.out.println(Arrays.toString(ints2));


    }

    public static int[] addAddArray(int[] array1, int[] array2){
        int[] newArray = new int[array1.length + array2.length];
        for (int i = 0; i < array1.length; i++) {
            newArray[i] = array1[i];
        }
        int index = 0;
        for (int i = 0; i < array2.length; i++) {
            newArray[array1.length+(index++)] = array2[i];
        }
        return newArray;
    }


    public static Object[] m1(Object [] a){

        return a.clone();
    }

    public static String toStrings(Object [] array){
        if (array == null){
            return "null";
        }
        if (array.length-1 == -1){
            return "[]";
        }
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        for (int i = 0;; i++) {
            sb.append(array[i]);
            if (i == array.length-1){
                return sb.append('}').toString();
            }
            sb.append(", ");
        }
    }


    public static Object remove(Object array, int index) {
        int length = Array.getLength(array);
        if (index < 0 || index >= length) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Length: " + length);
        }

        Object result = Array.newInstance(array.getClass().getComponentType(), length - 1);
        System.arraycopy(array, 0, result, 0, index);
        if (index < length - 1) {
            System.arraycopy(array, index + 1, result, index, length - index - 1);
        }

        return result;
    }
}
