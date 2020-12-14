package com.mybatis.demo.stream.cases;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *  案例一 : 查询缺考的 人员名称
 */
public class CaseOne {

    @Data
    @AllArgsConstructor
    static
    class ExamStudentScore{
        // 学生姓名
        private String studentName;
        // 成绩
        private Integer scoreValue;
        // 科目
        private String subject;

    }

    /**
     *  学生考试成绩
     */
    Map<String, List<ExamStudentScore>> studentMap;

    @Before
    public void init(){
        studentMap = new HashMap<>();
        List<ExamStudentScore> zsScoreList = new ArrayList<>();
        zsScoreList.add(new ExamStudentScore("张三", 30, "CHINESE"));
        zsScoreList.add(new ExamStudentScore("张三", 40, "ENGLISH"));
        zsScoreList.add(new ExamStudentScore("张三", 50, "MATHS"));
        studentMap.put("张三", zsScoreList);
        
        List<ExamStudentScore> lsScoreList = new ArrayList<>();
        lsScoreList.add(new ExamStudentScore("李四", 80, "CHINESE"));
        lsScoreList.add(new ExamStudentScore("李四", null, "ENGLISH"));
        lsScoreList.add(new ExamStudentScore("李四", 100, "MATHS"));
        studentMap.put("李四", lsScoreList);

        List<ExamStudentScore> wwScoreList = new ArrayList<>();
        wwScoreList.add(new ExamStudentScore("王五", null, "CHINESE"));
        wwScoreList.add(new ExamStudentScore("王五", null, "ENGLISH"));
        wwScoreList.add(new ExamStudentScore("王五", 70, "MATHS"));
        studentMap.put("王五", wwScoreList);
    }

    /**
     *  查询 缺考的人
     */
    @Test
    public void test(){
//        studentMap.forEach((key, value) ->{
//            long count = value.stream().filter(student -> student.getScoreValue() != null).count();
//            if (count != value.size()){
//                System.out.println("缺考的人是 : " + key);
//            }
//        });
        studentMap.forEach((key, value)->{
            if (value.stream().anyMatch(student -> student.getScoreValue() == null)){
                System.out.println("缺考的人是 : " + key);
            }
        });
    }

    /**
     * 查询 正常考试的人
     */
    @Test
    public void testAll(){
        studentMap.forEach((key, value) -> {
            if (value.stream().allMatch(student -> student.getScoreValue() != null)){
                System.out.println("正常考试的人是 : " + key);
            }
        });
    }

    @Test
    public void test2(){

    }


}
