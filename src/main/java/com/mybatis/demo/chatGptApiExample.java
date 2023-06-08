package com.mybatis.demo;

import java.io.IOException;
import java.net.*;
import java.util.*;

import com.google.gson.Gson;
import okhttp3.*;

class OpenAIExample {

    // 设置 API 密钥和请求参数
    private static final String API_URL = "https://api.openai.com/v1/chat/completions";
    private static final String API_KEY = "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";  // replace with your actual API key

    public static void main(String[] args) {

        // 设置代理服务器地址和端口
        String proxyHost = "127.0.0.1";
        int proxyPort = 7890;

        // 创建代理对象
        Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(proxyHost, proxyPort));

        // 设置代理选择器
        ProxySelector.setDefault(new ProxySelector() {
            @Override
            public List<Proxy> select(URI uri) {
                return Arrays.asList(proxy);
            }

            @Override
            public void connectFailed(URI uri, SocketAddress sa, IOException ioe) {
                System.out.println("Proxy connection failed");
            }
        });

        Map<String, Object> payload = new HashMap<>();
        payload.put("model", "gpt-3.5-turbo");

        List<Map<String, String>> messages = new ArrayList<>();
        Map<String, String> message1 = new HashMap<>();
        message1.put("role", "system");
        message1.put("content", "You are a helpful assistant.");
        messages.add(message1);

        Map<String, String> message2 = new HashMap<>();
        message2.put("role", "user");
        message2.put("content", "美国的首都是哪个城市.");
        messages.add(message2);

        payload.put("messages", messages);
        Gson gson = new Gson();
        String jsonString = gson.toJson(payload);
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"), jsonString);
        // 创建 HTTP 请求
        Request request = new Request.Builder()
                .url(API_URL)
                .addHeader("Content-Type", "application/json")
                .addHeader("Authorization", "Bearer " + API_KEY)
//                .addHeader("User-Agent", "Apifox/1.0.0 (https://apifox.com)")
                .addHeader("Accept", "*/*")
                .addHeader("Host", "api.openai.com")
                .addHeader("Connection", "keep-alive")
                .post(requestBody)
                .build();

        // 发送请求并获取响应
        OkHttpClient client = new OkHttpClient();
        try {
            Response response = client.newCall(request).execute();
            if (response.isSuccessful() && response.body() != null) {
                String responseBody = response.body().string();
                System.out.println(responseBody);
            } else {
                System.out.println("Request failed");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("https.proxyHost: " + proxy.toString());

    }


}
