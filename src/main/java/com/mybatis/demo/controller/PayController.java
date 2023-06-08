package com.mybatis.demo.controller;

import com.alibaba.fastjson.JSONObject;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.alipay.api.response.AlipayTradePagePayResponse;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/pay")
public class PayController {

    @RequestMapping(value = "/account", method = RequestMethod.GET)
    public String select() throws AlipayApiException {
        String privateKey = "MIIEvAIBADANBgkqhkiG9w0BAQEFAASCBKYwggSiAgEAAoIBAQCHd0B0Z9I7jXbHw4saG179bxfFOOYMIx/Vkq5UOzat0EHyyJkTXRFNu656iWjE8/D8517F/EvZbe1zPC9Xkra6f9sX5AFgdstEI6CQxsXoWGyoIE0FkQ45Fq93flCp6E98KNCk02vx73X8MQN+oclPexN4gm3NyetIEjnV6jHjGECnNEQmxfyKubFnxRDd0YxO/AbqErizKeiR47uTeQt2FuTC2fY5GHYuK7i0SceBPHmI6dzBnG1ytRaeAL5XmaSJFvCoV8eVy96vPVOsNaI3YXuceYXlH/5KbZ4UOo/vsbqRH3ppOEjToaijpoHwYwLvRafWA20Mgtyev/EiRVHrAgMBAAECggEAPm6bagqfPYsI/zAD39hVgv+8OMzsWROnDEs5xOA1Zw3gFwCUa/QskWK6GwdRHdQ/cI9BgoWwf1jGYqGOQBIB3Ron427Vo5ucOp8g1RiHBXobObEJTlN39p+Z5rx0eJn0aAh3gD9gNHRkMAJ21dnCabsTUirqaDlzEhg0R35T15EagcV+CmI+NuqNvLrbJU08Yz306d+9TO4eY8xeFkvJ6JDfRT6zbfNNqEN4nuLtejPDzrS9NNpjD5BBSNsS3YV8lJOACoeNddjATYEItPUsYrwM+YBkjDjZHmaaRP6AxuLVO7GrnZusamrrsGhTMcosKc7mOAFWrnmR6pv90d4iQQKBgQDlFjlQ6HvAzZNlO02tl94VwSkc9rRz1ngdnsnzsDQFMdKMMuFnEUsDCvfQeFiOShHakT6w2zQ1MMwjx4gpQHYEtpoRtiFl5BWh2RsA3jtbKnZx7WZJWUVwhbSRmQgC/KIOGCgu9vig1psHHhr7pDXOZyqtLXzklmGf0wyvn4SWuQKBgQCXYWF5S8fn69xzpILZFYjbv/I9HZsHoMJggzK3oDGqDE3FtiD3Mpgoa19LlVBaBhk+5l+jBV0iX6YEbIWRy/lDC/PL8YEAcvkTY1yeFfijib6X/qInQAkF7SMoqGrQcCp0twR0cN1a14BKS6OQp2W9DvHa1TaRRxrCPE8jJukbwwKBgBqNVN9X0uAp86eMFBjGvWrwFH/S6F90wD9OpEGmbqEbgTfWveFgU71qjAaRrFHxq9gVdZqyDX8MxNxwyAtf+U3ajEj5Py28HfXAYCzdlSuf5FKYHX7q5e2A0cj+X7tY5L+VdTLcVTzvItDUYHRCOCpQMtYXq54i//WyVIoWhxuhAoGAQraF0IDXHpxRUR2FFMpqv/19cfWkJt5wCzyGk2ojuP8nHyS+rnI7oESBgLvS53mckp3QM/XfL5Dbh0OUMcakmSmhTZWm9l206xh07q5cHyZLOozbRVTxJQ122yotuKJV843chrxesPQb86IV+V/sdD8r0vb3z1p4rnUJo0I4pSUCgYAjz602D6pLyczLKL7fMxXGJjr81kDMj27dKgpFqz7ICEo2ruT9PbnhnR0qg6nd4LeJOI2XNDFyuZ9/TcYyaQisLous3ewpdgYUzXkgO8uE8xXMqxSbyuTibrTJ9G26xQej/T+VNrgr3NhzaPXrRE40l6BcQQk8SJndf9QuD/N8EA==";
        String publicKey = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAiMjkobYxS7BD4cVtDlmDzjKkT4AD95/BPI2+pdoTCnwUMMrQPryU4CLZ33C6Ic0o1ZED6Ebu4Ww8M0lYw1EcneP5h2wFxRj7GNCvDrIXpyTH+GZTG6EUPI4tAlZrH8dN8hVNwoXJ35ND2rgSqJDxqXAmlPJTQlj4I/V+A4WeKaND4DQuohZsIYaduPznvD6JYg4dA/qI25peXra8unbtzHNxrB+nwjgZB8ek31FzS9TiVavNHnOH/tDxr/rU229uraVzLqDO5cV6JqFL03J19FW/2J2Kb2X6hDvCurlupTd3gkciiSNBnzK128h5mtEHO4ZEE4phqOc8J8EWt/FeYQIDAQAB";

        AlipayClient alipayClient = new DefaultAlipayClient("https://openapi-sandbox.dl.alipaydev.com/gateway.do", "9021000122682852", privateKey, "json", "UTF-8", publicKey, "RSA2");
        AlipayTradePagePayRequest request = new AlipayTradePagePayRequest();
        //异步接收地址，仅支持http/https，公网可访问
        request.setNotifyUrl("http://runww.natapp1.cc/pay/notify");
        //同步跳转地址，仅支持http/https
        request.setReturnUrl("");
        JSONObject bizContent = new JSONObject();
        //商户订单号，商家自定义，保持唯一性
        bizContent.put("out_trade_no", "O2023060715192");
        //支付金额，最小值0.01元
        bizContent.put("total_amount", 100);
        //订单标题，不可使用特殊符号
        bizContent.put("subject", "测试商品");
        //电脑网站支付场景固定传值FAST_INSTANT_TRADE_PAY
        bizContent.put("product_code", "FAST_INSTANT_TRADE_PAY");


        request.setBizContent(bizContent.toString());
        AlipayTradePagePayResponse response = alipayClient.pageExecute(request);
        if (response.isSuccess()) {
            System.out.println("调用成功");
        } else {
            System.out.println("调用失败");
        }
        String body = response.getBody();
        System.out.println(body);

        return body;
    }

}
