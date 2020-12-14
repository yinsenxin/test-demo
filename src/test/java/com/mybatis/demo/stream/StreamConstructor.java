package com.mybatis.demo.stream;

import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 *  流的创建方式
 */
public class StreamConstructor {

    /**
     *  1. 由数值创建流
     */
    @Test
    public void streamFromValue(){
        Stream stream = Stream.of(1, 2, 3, 4, 5);
        stream.forEach(System.out::println);
    }

    /**
     * 2. 由数组创建流
     */
    @Test
    public void streamFromArray(){
        int [] array = {1, 2, 3, 4, 5};
        IntStream stream = Arrays.stream(array);
        stream.forEach(System.out::println);
    }

    /**
     * 3. 由文件创建流
     * @throws IOException
     */
    @Test
    public void streamFromFile() throws IOException{
        Stream<String> stream = Files.lines(Paths.get("D:\\ideaspacework\\MyDemo\\" +
                "mybatis\\src\\test\\java\\com\\mybatis\\demo\\" +
                "stream\\StreamConstructor.java"));

        stream.forEach(System.out::println);
    }

    /**
     * 4. 由函数生成流(无限流)
     */
    @Test
    public void streamFromFunction(){
//        Stream stream = Stream.iterate(0, n -> n + 2);
        Stream stream = Stream.generate(Math::random);

        stream.limit(100)
                .forEach(System.out::println);


    }

}
