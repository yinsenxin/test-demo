package com.mybatis.demo.lambda;

import javafx.util.Builder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.factory.ObjectFactory;

import java.util.*;
import java.util.concurrent.Callable;
import java.util.function.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author 尹森鑫
 */
public class FilterCondition {
    public static void main(String[] args) throws Exception {

        List<Apple> inventory = Arrays.asList(
                new Apple("red", 100, 101),
                new Apple("white",100, 102),
                new Apple("Green", 100, 101)
        );
        List<Apple> inventory2 = Arrays.asList(
                new Apple("red1", 100, 101),
                new Apple("red", 100, 101),
                new Apple("white",100, 102),
                new Apple("Green", 100, 101)
        );
        


    }

    // 根据过滤器 过滤想要筛选的Condition
    static <T> List<T> filterApple(List<T> ts, Predicate<T> predicate){
        List<T> list = new ArrayList<>();
        ts.forEach(t -> {
            if(predicate.test(t)) {
                list.add(t);
            }
        });
        return list;
    }

}


@Data
@NoArgsConstructor
@AllArgsConstructor
class Apple{
    private String color;
    private Integer desc;
    private Integer weight;

    public Apple(Integer desc) {
        this.desc = desc;
    }
}
