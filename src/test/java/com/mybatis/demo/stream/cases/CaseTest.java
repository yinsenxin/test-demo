package com.mybatis.demo.stream.cases;

import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Sets;
import com.google.common.primitives.Chars;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CaseTest {

    @Test
    public void checkAge(){
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);

        //
        ImmutableSet<Integer> integers = ImmutableSet.copyOf(list);
        //
        ImmutableSet<Integer> of = ImmutableSet.of(1, 2, 3);
        //
        ImmutableSet<Object> build = ImmutableSet.builder()
                .add(1)
                .addAll(Sets.newHashSet(1, 2, 3))
                .add(4)
                .build();

        Chars.asList();



    }
}
