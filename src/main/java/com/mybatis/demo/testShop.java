package com.mybatis.demo;

import com.alibaba.fastjson.JSON;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

public class testShop {
    public static void main(String[] args) {

        String storeName = "3c7dc6"; // 替换为你的店铺名称
        String token = "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"; // 替换为你的API令牌

//        String url = "https://" + storeName + ".myshopify.com/admin/products.json";
        String url = "https://shopify.yproject.fr/api/2020-10/graphql.json";

        HttpClient client = HttpClientBuilder.create().build();
        HttpGet request = new HttpGet(url);

        // 添加授权头
        request.addHeader("X-Shopify-Access-Token", token);

        try {
            HttpResponse response = client.execute(request);

            // 检查状态码
            if(response.getStatusLine().getStatusCode() == 200) {
                String json = EntityUtils.toString(response.getEntity());
                String prettyJson = JSON.toJSONString(JSON.parseObject(json), true);
                System.out.println(prettyJson);
            } else {
                System.out.println("请求失败，状态码：" + response.getStatusLine().getStatusCode());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
