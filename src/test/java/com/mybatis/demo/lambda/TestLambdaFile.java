package com.mybatis.demo.lambda;

import org.junit.Test;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TestLambdaFile {

    @Test
    public void fileHand() throws IOException {
        FileService fileService = new FileService();
        fileService.fileHand("D:\\ideaspacework\\" +
                "MyDemo\\mybatis\\src\\main\\java\\com\\mybatis\\" +
                "demo\\lambda\\FileService.java",

                string -> System.out.println(string));


    }


}
