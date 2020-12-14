package com.mybatis.demo.exercise;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import org.apache.commons.lang.ArrayUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class CollectionsMethod2 {
    public static void main(String[] args) {
        ArrayList<Integer> arrayList = new ArrayList<Integer>();
        arrayList.add(-1);
        arrayList.add(3);
        arrayList.add(3);
        arrayList.add(-5);
        arrayList.add(7);
        arrayList.add(4);
        arrayList.add(-9);
        arrayList.add(-7);


//        Collections.fill(arrayList, 22);
//        System.out.println("替换之后  " + arrayList);
//        int frequency = Collections.frequency(arrayList, 3);
//        System.out.println(frequency);i
//        int i = Collections.indexOfSubList(arrayList, arrayList2);
//        System.out.println(i);

//        Collections.replaceAll(arrayList, 13, 222);
//        System.out.println(arrayList);

        Collections.sort(arrayList);
        System.out.println(Collections.binarySearch(arrayList, 3));
    }
}
