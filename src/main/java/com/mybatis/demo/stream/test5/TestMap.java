package com.mybatis.demo.stream.test5;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentMap;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class TestMap {

    public static void main(String[] args) {

        List<Port> list = new ArrayList<>();

        Port port = new Port("zs", "111", "port...");
        Port port1 = new Port("ls", "222", "port1---");
        Port port2 = new Port("ww", "333", "port2+++");
        Port port3 = new Port("zl", "444", "port3!!!");
        Port port4 = new Port("ww", "333", "port4!!!");

        list.add(port);
        list.add(port1);
        list.add(port2);
        list.add(port3);
        list.add(port4);

        // Map<name, code + desc>

        // Map<name + code, desc>

        Map<String, String> collect = list.stream().collect(Collectors.toMap(item -> item.getName() + item.getCode(), Port::getDesc, (key1, key2) ->key2));

        Map<String, List<Port>> collect1 = list.stream().collect(Collectors.groupingBy(Port::getName));

        list.stream().collect(Collectors.groupingBy(Port::getName));



    }


}


class Port{

    private String name;

    private String code;

    private String desc;

    public Port(String name, String code, String desc) {
        this.name = name;
        this.code = code;
        this.desc = desc;
    }

    public Port() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    @Override
    public String toString() {
        return "Port{" +
                "name='" + name + '\'' +
                ", code='" + code + '\'' +
                ", desc='" + desc + '\'' +
                '}';
    }
}
