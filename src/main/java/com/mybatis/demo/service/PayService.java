package com.mybatis.demo.service;

import com.alipay.api.AlipayApiException;
import com.alipay.api.response.AlipayTradePagePayResponse;

import java.math.BigDecimal;
import java.util.Map;

public interface PayService {


    String tradeCreate(Integer entId, BigDecimal amount) throws AlipayApiException;


    String tradeNotify(Map<String, String> params);
}
