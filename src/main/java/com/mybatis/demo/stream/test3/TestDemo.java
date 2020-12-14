package com.mybatis.demo.stream.test3;

import com.sun.corba.se.spi.ior.IORTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TestDemo {

    public static void main(String[] args) {

        List<Integer> list = Stream.of(1, 2, 3).collect(Collectors.toList());
        List<Integer> list1 = Stream.of(3, 4).collect(Collectors.toList());

        // 数对
        List<int[]> collect = list.stream()
                .flatMap(i -> list1.stream()
                        .map(j -> new int[]{i, j}))
                .collect(Collectors.toList());

        for (int[] ints : collect) {
            System.out.println(Arrays.toString(ints));
        }

        System.out.println(" 被3 整除的 数对");

        // 能被3整除的数对
        List<int[]> collect1 = list.stream()
                .flatMap(i -> list1.stream()
                        .filter(j -> (i + j) % 3 == 0)
                        .map(j -> new int[]{i, j}))
                .collect(Collectors.toList());

        for (int[] ints : collect1) {
            System.out.println(Arrays.toString(ints));
        }


        List<Integer> list2 = new ArrayList<Integer>(){{
            add(1);
            add(2);
            add(3);
            add(4);
        }};
        Integer reduce = list2.stream().reduce(0, Integer::sum);
        System.out.println(reduce);

        list2.stream().reduce((a, b) -> (a + b)).ifPresent(result -> System.out.println(result));


    }
}
