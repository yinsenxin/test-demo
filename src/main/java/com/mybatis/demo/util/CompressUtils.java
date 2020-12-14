package com.mybatis.demo.util;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class CompressUtils {
    private static ZipOutputStream zipOutputStream=null;
    private static  ByteBuffer byteBuffer = ByteBuffer.allocate(1024);

    public static void main(String[] args) {
        try {
            zipOutputStream=new ZipOutputStream(new FileOutputStream(new File("D:\\test.zip")));
            toZip(new File("D:\\mall\\test.sql"));
            zipOutputStream.flush();
            zipOutputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void toZip(File file){
        if (file.isFile()){
            try {
                zipOutputStream.putNextEntry(new ZipEntry(file.getAbsolutePath().substring(3,file.getAbsolutePath().length())));

                FileChannel channel = new FileInputStream(file).getChannel();
                while (true){
                    byteBuffer.clear();
                    int read = channel.read(byteBuffer);
                    if (read==-1)break;;
                    zipOutputStream.write(byteBuffer.array());
                }
                channel.close();
                zipOutputStream.closeEntry();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else {
            File[] files = file.listFiles();
            if (files==null||files.length==0){
                try {
                    zipOutputStream.putNextEntry(new ZipEntry(file.getAbsolutePath().substring(3,file.getAbsolutePath().length())+"/"));
                    zipOutputStream.closeEntry();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }else {
                for (File file2:files) {
                    toZip(file2);
                }
            }
        }
    }
}
