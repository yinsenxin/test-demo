package com.mybatis.demo.leetcode;

/**
 * @author 尹森鑫
 */
public class Test4 {
    public static void main(String[] args) {
        // 在一个String数组中 找出最长 public prefix,没有时 返回""
        String[] str = {"leetcode", "leetc", "leets", "leetd"};
        String s = longestCommonPrefix(str);
        System.out.println(s);
    }

    public static String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        }
        String prefix = strs[0];
        for (int i = 1; i < strs.length; i++) {
            prefix = longestCommonPrefix(prefix, strs[i]);
            if (prefix.length() == 0) {
                break;
            }
        }
        return prefix;
    }
    public static String longestCommonPrefix(String str1, String str2) {
        int length = Math.min(str1.length(), str2.length());
        int index = 0;
        while (index < length && str1.charAt(index) == str2.charAt(index)) {
            index++;
        }
        return str1.substring(0, index);
    }

}


