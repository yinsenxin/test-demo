package com.mybatis.demo.leetcode;

import java.util.Arrays;

public class test3 {
    public static void main(String[] args) {

        int []nums = {2,7,11,15};
        int[] array = array(nums, 18);
        System.out.println(Arrays.toString(array));

    }

    public static int[] array(int [] nums, int target){
        for (int i = 0; i < nums.length; i++) {
            for (int i1 = i+1; i1 < nums.length; i1++) {
                if(nums[i] + nums[i1] == target){
                    return new int[]{i, i1};
                }
            }
        }
        throw new IllegalArgumentException("");
    }
}
