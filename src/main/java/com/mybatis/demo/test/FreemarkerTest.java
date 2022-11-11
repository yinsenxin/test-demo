package com.mybatis.demo.test;



import com.mybatis.demo.entity.OrderBoxVo;
import com.mybatis.demo.util.FreeMarkUtils;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import sun.misc.BASE64Encoder;

import java.io.*;
import java.util.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;

public class FreemarkerTest {
    // https://my.oschina.net/u/3737136/blog/2958421?tdsourcetag=s_pcqq_aiomsg
    private static String document="document.xml";
    private static String documentXmlRels="document.xml.rels";
    private static String xmlContentTypes = "[Content_Types].xml";

    //outputStream 输出流可以自己定义 浏览器或者文件输出流
    public static void createDocx(Map dataMap, OutputStream outputStream) {
        ZipOutputStream zipout = null;
        InputStream in=null;
        try {
            //图片配置文件模板
            String xmlDocumentXmlRelsComment = FreeMarkUtils.getFreemarkerContent(dataMap, documentXmlRels);
            ByteArrayInputStream documentXmlRelsInput =
                    new ByteArrayInputStream(xmlDocumentXmlRelsComment.getBytes());

            //内容模板
            ByteArrayInputStream documentInput = FreeMarkUtils.getFreemarkerContentInputStream(dataMap, document);

            ByteArrayInputStream contentTypesInput = FreeMarkUtils.getFreemarkerContentInputStream(dataMap, xmlContentTypes);


            //读取 document.xml.rels  文件 并获取rId 与 图片的关系 (如果没有图片 此文件不用编辑直接读取就行了)
            Document document = DocumentHelper.parseText(xmlDocumentXmlRelsComment);
            // 获取根节点
            Element rootElt = document.getRootElement();
            // 获取根节点下的子节点head
            Iterator iter = rootElt.elementIterator();
            List<Map<String, String>> picList = (List<Map<String, String>>) dataMap.get("modelList");
            // 遍历Relationships节点
            while (iter.hasNext()) {
                Element recordEle = (Element) iter.next();
                String id = recordEle.attribute("Id").getData().toString();
                String target = recordEle.attribute("Target").getData().toString();
                if (target.indexOf("media") == 0) {
                    for (Map<String, String> picMap : picList) {
                        if (target.endsWith(picMap.get("name"))) {
                            picMap.put("rId", id);
                        }
                    }
                }
            }
            //覆盖原来的picList;
            dataMap.put("modelList", picList);

            ByteArrayInputStream documentInput2 = FreeMarkUtils.getFreemarkerContentInputStream(dataMap, "document.xml");

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
                    if (next.getName().equals("[Content_Types].xml")) {
                        if (contentTypesInput != null) {
                            while ((len = contentTypesInput.read(buffer)) != -1) {
                                zipout.write(buffer, 0, len);
                            }
                            contentTypesInput.close();
                        }

                    } else if (next.getName().indexOf("document.xml.rels") > 0) { //如果是document.xml.rels由我们输入
                        if (documentXmlRelsInput != null) {
                            while ((len = documentXmlRelsInput.read(buffer)) != -1) {
                                zipout.write(buffer, 0, len);
                            }
                            documentXmlRelsInput.close();
                        }
                    } else if ("word/document.xml".equals(next.getName())) {//如果是word/document.xml由我们输入
                        if (documentInput2 != null) {
                            while ((len = documentInput2.read(buffer)) != -1) {
                                zipout.write(buffer, 0, len);
                            }
                            documentInput2.close();
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
            len = -1;
            if (picList != null && !picList.isEmpty()) {
                for (Map<String, String> pic : picList) {
                    ZipEntry next = new ZipEntry("word" + File.separator + "media" + File.separator + pic.get("name"));
                    zipout.putNextEntry(new ZipEntry(next.toString()));
                    in = new FileInputStream(pic.get("path"));
                    while ((len = in.read(buffer)) != -1) {
                        zipout.write(buffer, 0, len);
                    }
                    in.close();
                }
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
    public static void main(String arg[]) throws FileNotFoundException {
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
          dataMap.put("entName", "菜鸟");
          dataMap.put("orderNo", "YGJT20220905111222");
          dataMap.put("productName", "铁路-德国产品");
          dataMap.put("countryName", "德国");
          dataMap.put("price", "89890");
          dataMap.put("weight", "1000");
          dataMap.put("volume", "2000");
          dataMap.put("boxNum", "500");
          dataMap.put("orderBoxVoList", orderBoxVoList);

        List<Map<String, Object>> picList = new ArrayList<>();

        Map<String, Object> picMap = new HashMap<>();
        // 要按顺序
        picMap.put("path", "D:\\tmp\\222.jpeg");
        picMap.put("name", "222.jpeg");
        picMap.put("rId", "rId17");

        Map<String, Object> picMap2 = new HashMap<>();
        // 要按顺序
        picMap2.put("path", "D:\\tmp\\t11.png");
        picMap2.put("name", "t11.png");
        picMap2.put("rId", "rId18");
        picList.add(picMap);
        picList.add(picMap2);
        dataMap.put("modelList", picList);

        //      图片类型
        List<String> picTypes = new ArrayList<>();
        picTypes.add("jpg");
        picTypes.add("png");
        picTypes.add("jpeg");
        dataMap.put("mdlTypes", picTypes);


        //指定输出docx路径
        File outFile = new File("D:\\tmp\\"+ System.currentTimeMillis() +".docx") ;
        try {
            createDocx(dataMap,new FileOutputStream(outFile));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static String getImgFileToBase64(String imgFile) {
        //将图片文件转化为字节数组字符串，并对其进行Base64编码处理
        InputStream inputStream = null;
        byte[] buffer = null;
        //读取图片字节数组
        try {
            inputStream = new FileInputStream(imgFile);
            int count = 0;
            while (count == 0) {
                count = inputStream.available();
            }
            buffer = new byte[count];
            inputStream.read(buffer);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                try {
                    // 关闭inputStream流
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        // 对字节数组Base64编码
        return new BASE64Encoder().encode(buffer);
    }

}
