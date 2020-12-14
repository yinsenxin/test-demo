package com.mybatis.demo.io;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Arrays;

public class TestIO {
    public static void main(String[] args) throws Exception {

        String url = "D:\\ideaspacework\\MyDemo\\mybatis\\src\\main\\java\\com\\mybatis\\demo\\lambda\\FileService.java";

        FileInputStream fis = new FileInputStream(url);

        byte[] arr=new byte[1024];
        int len;
        while((len=fis.read(arr))!=-1) {
            System.out.println(new String(arr, 0, len));
        }

    }
}
