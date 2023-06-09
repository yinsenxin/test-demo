package com.mybatis.demo;


import okhttp3.*;

import java.io.IOException;
import java.net.*;
import java.util.Arrays;
import java.util.List;


public class t1 {
    public static void main(String[] args) throws URISyntaxException, IOException {

//        OkHttpClient client = new OkHttpClient();
//
//        MediaType mediaType = MediaType.parse("application/json");
//        RequestBody body = RequestBody.create(mediaType, "[{\"number\": \"RR123456888CN\"}]");
//        Request request = new Request.Builder()
//                .url("https://api.17track.net/track/v2/register")
//                .post(body)
//                .addHeader("17token", "9A4E4226CF1ED7F307FA3D93EBE28CBB")
//                .addHeader("Content-Type", "application/json")
//                .build();
//
//        Response response = client.newCall(request).execute();
//
//        System.out.println("Response code: " + response.code());
//        System.out.println("Response body: " + response.body().string());

        String str  = "1-22";
        System.out.println(str.split("-").length);
        System.out.println(str.contains("-"));

    }

}
