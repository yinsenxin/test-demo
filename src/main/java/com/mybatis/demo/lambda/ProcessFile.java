package com.mybatis.demo.lambda;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.function.Predicate;

public class ProcessFile {
    public static void main(String[] args) throws IOException {

        System.out.println(processFile((BufferedReader bufferedReader) -> bufferedReader.readLine()+ "\n" + bufferedReader.readLine()));


    }

    public static String processFile(BufferReaderProcessor p) throws IOException {

        try(BufferedReader bufferedReader = new BufferedReader(new FileReader("D:\\1.txt"))){
            String process = p.process(bufferedReader);
            return process;
        }
    }

}


