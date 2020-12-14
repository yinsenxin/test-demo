package com.mybatis.demo.lambda;


import java.io.*;

/**
 * @author 尹森鑫
 */
public class FileService{

    /**
     *  通过url获取本地文件的内容 , 调用函数式接口进行处理
     * @param url
     * @param fileHander
     */
    public void fileHand(String url, FileHander fileHander) throws IOException {

        BufferedReader bf = new BufferedReader(new FileReader(url));

        String string = null;
        StringBuffer sb = new StringBuffer();
        while ((string=bf.readLine()) != null){
            sb.append(string + "\n");
        }
        fileHander.readFile(sb.toString());
    }


}
