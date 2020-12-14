package com.mybatis.demo.stream.test2;

import com.alibaba.fastjson.JSON;
import org.apache.commons.lang.StringUtils;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Sort {
    public static void main(String[] args) {

        List<Student> data = getData();
        // 分组映射

//        Map<String, List<Student>> collect = data.stream().collect(Collectors.groupingBy(student -> student.getId() + "_" + student.getType()));
//        List<StudentPlus> collect1 = collect.keySet().stream().map(key -> {
//            String[] s = key.split("_");
//            String id = s[0];
//            String type = s[1];
//            StudentPlus studentPlus = new StudentPlus();
//            studentPlus.setId(id);
//            studentPlus.setType(type);
//            studentPlus.setStudents(collect.get(key));
//            return studentPlus;
//        }).collect(Collectors.toList());
//
//        // 分组映射
//        Map<Integer, List<String>> collect2 = data.stream().
//                collect(Collectors.groupingBy(Student::getId, Collectors.mapping(Student::getName, Collectors.toList())));


        // 排序
        List<Student> collect = data.stream()
                .sorted(Comparator.comparing(Student::getId, Comparator.nullsFirst(Integer::compareTo).reversed())
                        .thenComparing(Student::getName).thenComparing(Student::getType,
                                (type1, type2) -> {
                                    if (StringUtils.isNotEmpty(type1) && StringUtils.isNotEmpty(type2)){
                                        if ("小学".equals(type1) && "高中".equals(type2)) {
                                            return -1;
                                        } else if ("高中".equals(type1) && "小学".equals(type2)) {
                                            return 0;
                                        } else {
                                            return 0;
                                        }
                                    }
                                    return type1 == null ? 1 : -1;
                                }
                        )
                )
                .collect(Collectors.toList());

        System.out.println(JSON.toJSONString(collect, true));
    }


    public static List<Student> getData(){
        List<Student> list = new ArrayList<Student>(){{
            add(new Student(1,"张三", "高中"));
            add(new Student(1,"张三", "高中"));
            add(new Student(3,"李四", "小学"));
            add(new Student(4,"王五", "小学"));
            add(new Student(5,"aaa", "小学"));
            add(new Student(6,"bbb", "小学"));
            add(new Student(7,"赵六", "小学"));
        }};
        return list;
    }


}



class Student{
    private Integer id;
    private String name;
    private String type;

    public Student(Integer id, String name, String type) {
        this.id = id;
        this.name = name;
        this.type = type;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}


class StudentPlus{

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public StudentPlus(String id, String type, List<Student> students) {
        this.id = id;
        this.type = type;
        this.students = students;
    }

    private String id;
    private String type;
    private List<Student> students;
}



