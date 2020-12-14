package com.mybatis.demo.io;

import java.io.*;

public class TestFile {
    public static void main(String[] args) throws IOException {

        File dir = new File("D:\\ideaspacework\\55train\\dispatch-");

        deleteDir(dir);
    }

    //列出dir目录中所有的.java文件
    static void listJavaFiles (File dir){
        File[] fs = dir.listFiles(new FileFilter(){
            @Override
            public boolean accept(File f){
                if (f.isDirectory()) {
                    return true;
                }
                if (f.isFile()){
                    String name = f.getName();
                    return name.endsWith(".java");
                }
                return false;
            }
        });
        for(File f: fs){
            if (f.isFile()) {
                System.out.println(f.getAbsolutePath());
            }
            if (f.isDirectory()) {
                listJavaFiles(f);
            }
        }
    }


    //删除dir目录
    static void deleteDir(File dir){
        File[] files = dir.listFiles();
        for (File file : files) {
            if (file.isFile()){
                file.delete();
            }
            if (file.isDirectory()){
                deleteDir(file);
            }
        }
        dir.delete();
    }
}
