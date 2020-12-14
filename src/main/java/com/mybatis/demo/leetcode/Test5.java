package com.mybatis.demo.leetcode;

public class Test5 {
    public static void main(String[] args) {

        String substring  = "";
        String[] strs = {"ABC", "ABCD", "ABCE", "ABCF", "ABCC"};
        int length = strs[0].length();
        int count = strs.length;
        for (int i = 0; i < length; i++) {
            char c = strs[0].charAt(i);
            for (int j = 1; j < count; j++) {
                if (i == strs[j].length() || strs[j].charAt(i) != c) {
                     substring = strs[0].substring(0, i);
                     System.out.println(substring);
                }
            }
        }




    }
}
