package com.mybatis.demo.test;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.util.CollectionUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TestString {
    public static void main(String[] args) throws ClassNotFoundException, ParseException {
//        int[] array = {1,2,3,7,3,2,1};
//        int centerIndex = getCenterIndex(array);
//        System.out.println(centerIndex);

        List<Str> list = new ArrayList<>();
        Str str = new Str(1000,2);
        Str str2 = new Str(22000,2);
        Str str3 = new Str(2000,2);
        list.add(str3);
        list.add(str2);
        list.add(str);

        // 获取最大元素
        String string = list.stream().max(Comparator.comparingInt(Str::getA)).toString();

        Stream<Str> sorted = list.stream().sorted();

        list.sort(Comparator.comparingInt(Str::getA));
        System.out.println(list.get(2));





        // 获取最小元素

        // 去重复元素
//        List<Str> collect = list.stream().distinct().collect(Collectors.toList());
//        System.out.println(collect);




    }

    private static int getCenterIndex(int [] nums){
        int left = 0;
        for(int i = 0; i < nums.length; i++) {
            int right = 0;
            left = i-1 < 0 ? 0 :  left+nums[i-1];
            for(int j = i+1; j < nums.length; j++) {
                right += nums[j];
            }
            if(left == right) {
                return i;
            }
        }
        return -1;
    }
}

@Data
@AllArgsConstructor
class Str{

    private Integer a;
    private Integer b;


}
