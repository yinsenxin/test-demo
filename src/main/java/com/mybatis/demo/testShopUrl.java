package com.mybatis.demo;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class testShopUrl {
    public static void main(String[] args) {

        String storeName = "3c7dc6";
        String apiKey = "9c80077b639978c75d5efdd628a6d302";
        String password = "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";

        String url = "https://" + apiKey + ":" + password + "@" + storeName + ".myshopify.com/admin/products.json";

        try (CloseableHttpClient client = HttpClients.createDefault()) {
            HttpGet request = new HttpGet(url);
            HttpResponse response = client.execute(request);
            HttpEntity entity = response.getEntity();

            if (entity != null) {
                String result = EntityUtils.toString(entity);
                JSONObject jsonObject = JSON.parseObject(result);
                JSONArray products = jsonObject.getJSONArray("products");

                for (int i = 0; i < products.size(); i++) {
                    JSONObject product = products.getJSONObject(i);
                    String title = product.getString("title");
                    String handle = product.getString("handle");

                    System.out.println(title);
                    System.out.println("https://" + storeName + ".myshopify.com/products/" + handle);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }



    }
}
