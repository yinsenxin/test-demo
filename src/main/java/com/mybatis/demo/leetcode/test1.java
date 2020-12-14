package com.mybatis.demo.leetcode;


import java.util.HashMap;
import java.util.Map;

/**
 * @author 尹森鑫
 */
public class test1 {
    public static void main(String[] args) {


        /*int[] nums = {2, 7, 11, 15};
        int[] ints = twoSum(nums, 13);
        System.out.println(Arrays.toString(ints));*/

    }

    /**
     *  通过hashMap来完成
     * @param nums
     * @param target
     * @return
     */
    public static int [] twoSum(int[] nums, int target){

        Map<Integer, Integer> map = new HashMap<>(10);
        for (int i = 0; i < nums.length; i++) {
            int result = target - nums[i];
            if (map.containsKey(result)){
                return new int []{map.get(result), i};
            }
            map.put(nums[i], i);
        }
        throw new IllegalArgumentException("NO match result!!!");
    }



}
