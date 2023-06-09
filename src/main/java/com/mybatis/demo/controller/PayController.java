package com.mybatis.demo.controller;

import com.alibaba.fastjson.JSON;
import com.mybatis.demo.service.PayService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Map;


@RestController
@RequestMapping("/pay")
public class PayController {
    private static Logger log = LoggerFactory.getLogger(PayController.class);
    @Autowired
    private PayService payService;

    @RequestMapping(value = "/account", method = RequestMethod.GET)
    public String select(@RequestParam("entId") Integer entId,
                         @RequestParam("amount") BigDecimal amount) throws Exception {

        return payService.tradeCreate(entId, amount);
    }

    @PostMapping("/notify")
    public String tradeNotify(@RequestParam Map<String, String> params) {
        log.info("支付通知,正在执行,通知参数:{}", JSON.toJSONString(params));
        return payService.tradeNotify(params);
    }

}
