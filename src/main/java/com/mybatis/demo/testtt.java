package com.mybatis.demo;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

public class testtt {
    public static void main(String[] args) throws Throwable {

//        int j;
//        boolean flag;
//        for (int i = 2; i < 1000; i++) {
//            flag = false;
//            for (j = 2; j < i; j++) {
//                if (i % j == 0) {
//                    flag = true;
//                    break;
//                }
//            }
//            if (flag == false) {
//                System.out.print(i + "    ");
//            }
//
//        User user = new User();
//
//        user.setName("张三");
//        user.setDesc("123");
//        String string = JSON.toJSONString(user);
//
//        JSONObject jsonObject = JSON.parseObject(string);
//        String name = jsonObject.getString("name2");
//        System.out.println(name);
//
//        String s = "";
      String str = "{\n" +
              "\t\"AlarmInfoPlate\": {\n" +
              "\t\t\"resultType\": 0,\n" +
              "\t\t\"channel\": 0,\n" +
              "\t\t\"deviceName\": \"车牌识别一体机\",\n" +
              "\t\t\"ipaddr\": \"192.168.1.98\",\n" +
              "\t\t\"result\": {\n" +
              "\t\t\t\"PlateResult\": {\n" +
              "\t\t\t\t\"confidence\": 85,\n" +
              "\t\t\t\t\"direction\": 4,\n" +
              "\t\t\t\t\"license\": \"京 Q2X0G5\",\n" +
              "\t\t\t\t\"location\": {\n" +
              "\t\t\t\t\t\"RECT\": {\n" +
              "\t\t\t\t\t\t\"bottom\": 381,\n" +
              "\t\t\t\t\t\t\"left\": 499,\n" +
              "\t\t\t\t\t\t\"right\": 640,\n" +
              "\t\t\t\t\t\t\"top\": 344\n" +
              "\t\t\t\t\t}\n" +
              "\t\t\t\t},\n" +
              "\t\t\t\t\"timeUsed\": 99,\n" +
              "\t\t\t\t\"triggerType\": 0,\n" +
              "\t\t\t\t\"type\": 1,\n" +
              "\t\t\t\t\"platecolor\": \"蓝\",\n" +
              "\t\t\t\t\"recotime\": \"2018-1-24 16:33:17\",\n" +
              "\t\t\t\t\"imageFile\": \"\",\n" +
              "\t\t\t\t\"imageFileLen\": 0,\n" +
              "\t\t\t\t\"imageFragmentFile\": \"\",\n" +
              "\t\t\t\t\"imageFragmentFileLen\": 0\n" +
              "\t\t\t}\n" +
              "\t\t},\n" +
              "\t\t\"seriaIno\": \"70b50907010000e1\",\n" +
              "\t\t\"romid\": \"70b50907010000e1\",\n" +
              "\t\t\"sn\": \"\",\n" +
              "\t\t\"nParkID\": 1,\n" +
              "\t\t\"ParkID\": \"1\",\n" +
              "\t\t\"ParkName\": \"Defult\",\n" +
              "\t\t\"ParkDoor\": \"Defult\"\n" +
              "\t},\n" +
              "\t\"WhiteListInfo\": {\n" +
              "\t\t\"Info\": {\n" +
              "\t\t\t\"WhiteListEnable\": 0,\n" +
              "\t\t\t\"TimeMatchEable\": 0,\n" +
              "\t\t\t\"CreateTime\": \"\",\n" +
              "\t\t\t\"StartTime\": \"\",\n" +
              "\t\t\t\"EndTime\": \"\",\n" +
              "\t\t\t\"BlackList\": 0\n" +
              "\t\t},\n" +
              "\t\t\"WhiteListControl\": {\n" +
              "\t\t\t\"OpenDoor\": 0,\n" +
              "\t\t\t\"TimeCheck\": 0,\n" +
              "\t\t\t\"InteriorCar\": 0\n" +
              "\t\t}\n" +
              "\n" +
              "\t}\n" +
              "\n" +
              "}";

        JSONObject jsonObject = JSON.parseObject(str);
        JSONObject alarmInfoPlate = jsonObject.getJSONObject("AlarmInfoPlate1");

        System.out.println(alarmInfoPlate);

//        System.out.println(.getJSONObject("result").getJSONObject("PlateResult").get("license"));

    }

}



