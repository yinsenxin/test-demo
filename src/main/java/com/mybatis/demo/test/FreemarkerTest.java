package com.mybatis.demo.test;



import com.mybatis.demo.entity.OrderBoxVo;
import com.mybatis.demo.util.FreeMarkUtils;

import java.io.*;
import java.util.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;

public class FreemarkerTest {
    // https://my.oschina.net/u/3737136/blog/2958421?tdsourcetag=s_pcqq_aiomsg
    private static String document="document.xml";
    private static String documentXmlRels="document.xml.rels";
    //outputStream 输出流可以自己定义 浏览器或者文件输出流
    public static void createDocx(Map dataMap, OutputStream outputStream) {
        ZipOutputStream zipout = null;
        try {
            //图片配置文件模板
            ByteArrayInputStream documentXmlRelsInput = FreeMarkUtils.getFreemarkerContentInputStream(dataMap, documentXmlRels);

            //内容模板
            ByteArrayInputStream documentInput = FreeMarkUtils.getFreemarkerContentInputStream(dataMap, document);
            //最初设计的模板
            //File docxFile = new File(WordUtils.class.getClassLoader().getResource(template).getPath());
            File docxFile = new File("D:\\tmp\\test.zip");//换成自己的zip路径
            if (!docxFile.exists()) {
                docxFile.createNewFile();
            }
            ZipFile zipFile = new ZipFile(docxFile);
            Enumeration<? extends ZipEntry> zipEntrys = zipFile.entries();
            zipout = new ZipOutputStream(outputStream);
            //开始覆盖文档------------------
            int len = -1;
            byte[] buffer = new byte[1024];
            while (zipEntrys.hasMoreElements()) {
                ZipEntry next = zipEntrys.nextElement();
                InputStream is = zipFile.getInputStream(next);
                if (!next.toString().contains("media")) {
                    zipout.putNextEntry(new ZipEntry(next.getName()));
                    if (next.getName().indexOf("document.xml.rels") > 0) { //如果是document.xml.rels由我们输入
                        if (documentXmlRelsInput != null) {
                            while ((len = documentXmlRelsInput.read(buffer)) != -1) {
                                zipout.write(buffer, 0, len);
                            }
                            documentXmlRelsInput.close();
                        }
                    } else
                     if ("word/document.xml".equals(next.getName())) {//如果是word/document.xml由我们输入
                        if (documentInput != null) {
                            while ((len = documentInput.read(buffer)) != -1) {
                                zipout.write(buffer, 0, len);
                            }
                            documentInput.close();
                        }
                    } else {
                        while ((len = is.read(buffer)) != -1) {
                            zipout.write(buffer, 0, len);
                        }
                        is.close();
                    }
                }
            }


            //写入图片
            List<Map<String, Object>> picList = (List<Map<String, Object>>) dataMap.get("picList");
            for (Map<String, Object> pic : picList) {
                ZipEntry next = new ZipEntry("word" + File.separator + "media" + File.separator + pic.get("name"));
                zipout.putNextEntry(new ZipEntry(next.toString()));
                InputStream in = (ByteArrayInputStream)pic.get("code");
                while ((len = in.read(buffer)) != -1) {
                    zipout.write(buffer, 0, len);
                }
                in.close();
            }

        } catch (Exception e) {
            System.out.println("word导出失败:"+e.getStackTrace());
            //logger.error();
        }finally {
            if(zipout!=null){
                try {
                    zipout.close();
                } catch (IOException e) {
                    System.out.println("io异常");

                }
            }
            if(outputStream!=null){
                try {
                    outputStream.close();
                } catch (IOException e) {
                    System.out.println("io异常");
                }
            }
        }
    }
    public static void main(String arg[]){
        Map dataMap = new HashMap();
        OrderBoxVo orderBoxVo = new OrderBoxVo();
        orderBoxVo.setSeq(1);
        orderBoxVo.setBoxNo("box123123");
        orderBoxVo.setFbaNo("fba222222");
        orderBoxVo.setL("100");
        orderBoxVo.setK("200");
        orderBoxVo.setG("300");
        OrderBoxVo orderBoxVo2 = new OrderBoxVo();
        orderBoxVo2.setSeq(2);
        orderBoxVo2.setBoxNo("boxzzzz3333");
        orderBoxVo2.setFbaNo("fba1111111");
        orderBoxVo2.setL("100");
        orderBoxVo2.setK("200");
        orderBoxVo2.setG("300");
        ArrayList orderBoxVoList = new ArrayList();
        orderBoxVoList.add(orderBoxVo);
        orderBoxVoList.add(orderBoxVo2);

//        ArrayList minuteList=new ArrayList();
//        MinuteTest minuteTest1=new MinuteTest();
//        minuteTest1.setMeeting_decision_content("决策1");
//        minuteTest1.setMeeting_decision_executor("执行者1");
//        minuteTest1.setMeeting_decision_deadline("截至日期1");
//        minuteList.add(minuteTest1);
//
//        MinuteTest minuteTest2=new MinuteTest();
//        minuteTest2.setMeeting_decision_content("决策2");
//        minuteTest2.setMeeting_decision_executor("执行者2");
//        minuteTest2.setMeeting_decision_deadline("截至日期2");
//        minuteList.add(minuteTest2);

//        dataMap.put("meeting_name", "如何使象牙山发展得更加美好");
//        dataMap.put("time", "2019-09-15 15:30");
//        dataMap.put("site", "会议室212");
//        dataMap.put("organizer", "张三");
//        dataMap.put("department", "策划部");
//        dataMap.put("attendee", "谢大脚、谢广坤、刘能");
//        dataMap.put("meeting_content", "关于象牙山发展中的每个人的义务");
//        dataMap.put("recorder", "王五");
//        dataMap.put("checker", "大老板");
//        dataMap.put("minuteList",minuteList);
          dataMap.put("entName", "菜鸟");
          dataMap.put("orderNo", "YGJT20220905111222");
          dataMap.put("productName", "铁路-德国产品");
          dataMap.put("countryName", "德国");
          dataMap.put("price", "89890");
          dataMap.put("weight", "1000");
          dataMap.put("volume", "2000");
          dataMap.put("boxNum", "500");
          dataMap.put("orderBoxVoList", orderBoxVoList);


        //指定输出docx路径
        File outFile = new File("D:\\tmp\\new.docx") ;
        try {
            createDocx(dataMap,new FileOutputStream(outFile));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
