package com.mybatis.demo.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.AlipayConstants;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.alipay.api.response.AlipayTradePagePayResponse;
import com.mybatis.demo.po.AliPayConfigPo;
import com.mybatis.demo.service.PayService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Map;
import java.util.UUID;

@Service
@Slf4j
public class PayServiceImpl implements PayService {
    @Autowired
    private AliPayConfigPo aliPayConfigPo;

    @Override
    public String tradeCreate(Integer entId, BigDecimal amount) throws AlipayApiException {
        AlipayClient alipayClient = new DefaultAlipayClient(aliPayConfigPo.getGateWayUrl(), aliPayConfigPo.getAppId(), aliPayConfigPo.getPrivateKey(), "json", AlipayConstants.CHARSET_UTF8, aliPayConfigPo.getPublicKey(), AlipayConstants.SIGN_TYPE_RSA2);
        AlipayTradePagePayRequest request = new AlipayTradePagePayRequest();
        request.setNotifyUrl(aliPayConfigPo.getNotifyUrl());
        request.setReturnUrl(aliPayConfigPo.getReturnUrl());
        JSONObject bizContent = new JSONObject();
        bizContent.put("out_trade_no", "O2023" + UUID.randomUUID());
        bizContent.put("total_amount", amount.doubleValue());
        bizContent.put("subject", "账户充值");
        bizContent.put("product_code", "FAST_INSTANT_TRADE_PAY");

        request.setBizContent(bizContent.toString());
        AlipayTradePagePayResponse response = alipayClient.pageExecute(request);
        if (response.isSuccess()) {
            log.info("调用成功,返回结果:[{}]",response.getBody());
            return response.getBody();
        } else {
            log.info("调用失败!!");
        }
        return null;

    }

    @Override
    public String tradeNotify(Map<String, String> params) {
        String result = "failure";
        try {
            //异步通知验签
            boolean signVerified = AlipaySignature.rsaCheckV1(params,
                    aliPayConfigPo.getPublicKey(),
                    AlipayConstants.CHARSET_UTF8,
                    AlipayConstants.SIGN_TYPE_RSA2);
            if (!signVerified) {
                // TODO 验签失败则记录异常日志，并在response中返回failure.
                log.error("支付成功,异步通知验签失败!");
                return result;
            }
            log.info("支付成功,异步通知验签成功!");
            //TODO 验签成功后，按照支付结果异步通知中的描述，对支付结果中的业务内容进行二次校验
            //1.验证out_trade_no 是否为商家系统中创建的订单号
            String outTradeNo = params.get("out_trade_no");
            //1.判断 total_amount 是否确实为该订单的实际金额
            String totalAmount = params.get("total_amount");
//            if (payInfo.getPayAmount().compareTo(BigDecimal.valueOf(Long.parseLong(totalAmount))) != 0) {
//                //告警
//                throw new RuntimeException("异步通知中的金额和数据库里的不一致，orderNo=" + payResponse.getOrderId());
//            }
            //3.校验通知中的 seller_id是否为 out_trade_no 这笔单据的对应的操作方
            String sellerId = params.get("seller_id");
            if (!sellerId.equals(aliPayConfigPo.getSellerId())) {
                log.error("商家PID校验失败");
                return result;
            }
            //4.验证 app_id 是否为该商家本身
            String appId = params.get("app_id");
            if (!appId.equals(aliPayConfigPo.getAppId())){
                log.error("app_id校验失败");
                return result;
            }
            //在支付宝的业务通知中，只有交易通知状态为 TRADE_SUCCESS 或 TRADE_FINISHED 时，支付宝才会认定为买家付款成功
            String tradeStatus = params.get("trade_status");
            if (!"TRADE_SUCCESS".equals(tradeStatus) && !"TRADE_FINISHED".equals(tradeStatus)){
                log.error("支付未成功");
                return result;
            }

            //TODO 处理自身业务


            result = "success";
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }

        return result;
    }
}
