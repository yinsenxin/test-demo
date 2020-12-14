package com.mybatis.demo;


import lombok.Data;

public class Test111 {



    public static void main(String[] args){

//        try(Stream<String> lines = Files.lines(Paths.get("D:\\mall\\test.sql"), Charset.defaultCharset())){
//
//            List<String> collect = lines.limit(1).collect(Collectors.toList());
//
//            collect.forEach(System.out::println);
//
//
//        }catch (IOException e){
//            e.printStackTrace();
//        }


    }

}

@Data
class OrderDelivery{
    private String orderNo;
    private String gpsNo;
}


