package com.mybatis.demo.test;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.io.*;

public class TestPOI {

    public static void main(String[] args) throws Exception {


        File file = new File("D:\\testxxx.xls");
        InputStream in = new FileInputStream(file);
        HSSFWorkbook workbook = new HSSFWorkbook (in);
        HSSFSheet sheet = workbook.getSheetAt(0);

        HSSFRow row = sheet.getRow(2);

        row.getCell(2).setCellValue("2222");

//        OutputStream out = new FileOutputStream(file);

//        workbook.write(out);

    }
}
