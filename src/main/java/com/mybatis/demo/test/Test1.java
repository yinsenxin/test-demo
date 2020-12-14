package com.mybatis.demo.test;


import org.apache.commons.lang.ArrayUtils;

import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * @author yinsenxin
 */
public class Test1 {

    public static void main(String[] args) {
//     Map<String, String> customsMap = dictionaryDos.stream().collect(Collectors.toMap(SystemDictionaryDo::getColumnName, SystemDictionaryDo::getValue, (key1, key2) -> key2));
        int [] array = {1,2,3,4,5};
        int [] array2 = new int[array.length];


        systemArrayCopy(array, 0, array2, 0, array.length);
        System.out.println("System.arrayCopy()方法 ");
        System.out.println(Arrays.toString(array2));
        System.out.println( );

        System.out.println("for循环方法  ");
        int[] ints = forCopy(array);
        System.out.println(Arrays.toString(ints));

        System.out.println( );
        System.out.println("clone()方法  ");
        int[] ints1 = cloneArray(array);
        System.out.println(Arrays.toString(ints));

        System.out.println( );
        System.out.println("Arrays.copyOf()方法  ");
        int[] ints2 = arraysCopy(array, array.length);
        System.out.println(Arrays.toString(ints2));

        System.out.println( );
        System.out.println("Arrays.copyOfRange()方法  ");
        int[] ints3 = arraysCopyRange(array, 0 , array.length);
        System.out.println(Arrays.toString(ints3));

    }

    /**
     *  通过 System.arrayCopy 方法来进行数组Copy
     *
     * @param array 原数组
     * @param srcPos 原数组位置
     * @param array2 目标数组
     * @param destPos 目标数组位置
     * @param length 拷贝的个数
     */
    public static void systemArrayCopy(int []array,int srcPos,int [] array2,int destPos,int length){
        System.arraycopy(array,srcPos,array2,destPos,length);
    }

    /**
     *  利用for循环进行数组拷贝
     *
     * @param array
     * @return
     */
    public static int[] forCopy(int[] array){
        int [] array2 = new int [array.length];
        for (int i = 0; i < array.length; i++) {
            array2[i] = array[i];
        }
        return array2;
    }

    /**
     *
     *  利用Object类中的 clone()方法 进行数组拷贝
     * @param array
     * @return
     */
    public static int[] cloneArray(int [] array){
        if (array == null){
            return null;
        }
        int[] clone = array.clone();
        return clone;
    }

    /**
     *
     * 利用Arrays.copyOf()方法进行 数组copy
     * @param array 源数组
     * @param length 拷贝长度
     * @return
     */
    public static int[] arraysCopy(int[] array, int length){
        int [] ints = Arrays.copyOf(array, length);
        return ints;
    }

    /**
     *
     * 利用Arrays.copyOfRange()方法进行 数组copy
     * @param array        源数组
     * @param startIndex   开始位置
     * @param length       拷贝长度
     * @return
     */
    public static int[] arraysCopyRange(int []array, int startIndex, int length){
        int[] ints = Arrays.copyOfRange(array, startIndex, length);
        return ints;
    }

}




