package com.mybatis.demo;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class t1 {
    public static void main(String[] args) {

//        List<String> list = Arrays.asList("一", "七", "三", "六", "四");
//        Comparator<String> comparator = Comparator.naturalOrder();
//
//        list.sort(comparator.reversed());
//        Collections.sort(list);
//        A a = new A("1", "2");
//        A b = new A("1", "2");
//        Set<A> set = new HashSet<>();
//        set.add(a);
//        set.add(b);
//
//        System.out.println(set.size());


        List<String> list = new ArrayList<String>(){{
            add("noOut");
            add("transport");
            add("signFor");
        }};
        boolean a = list.stream().noneMatch(item -> StringUtils.equals(item, "arrive"));
        System.out.println(a);


    }

}

class A{
    private String name;
    private String desc;

    public A(String name, String desc) {
        this.name = name;
        this.desc = desc;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        A a = (A) o;

        return new EqualsBuilder()
                .append(name, a.name)
                .append(desc, a.desc)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(name)
                .append(desc)
                .toHashCode();
    }
}
