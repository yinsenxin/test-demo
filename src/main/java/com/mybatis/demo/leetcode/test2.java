package com.mybatis.demo.leetcode;

public class test2 {
    public static void main(String[] args) {
        int [] array = {1,3,5,7};
        int target = 9;
        int search = search(array, target);
        System.out.println(search);
    }

    /**
     * 给定一个排序数组和一个目标值，在数组中找到目标值，并返回其索引。如果目标值不存在于数组中，返回它将会被按顺序插入的位置。
     * 你可以假设数组中无重复元素。
     * 示例 1:
     * 输入: [1,3,5,6], 5
     * 输出: 2
     * @param nums
     * @param target
     * @return
     */
    public static int search(int []nums, int target){

        // 循环遍历数据, 如果存在目标值, 直接返回 下标
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == target){
                return i;
            }
        }
        // 如果 target在数组中不存在的情况
        if (target > nums[nums.length-1]){
            return nums.length;
        }
        for (int i = 1; i < nums.length; i++) {
            if (target > nums[i-1] && target < nums[i]){
                return i;
            }
        }
        return 0;
    }
}
