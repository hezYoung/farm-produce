/**
 * 1. @ClassName PayMentController
 * 2. @Description TODO
 * 3. @Author Young
 * 4. @Date 2023/12/21 10:20
 */
package com.farm.controller;

import com.farm.model.entity.pay.PaymentInfo;
import com.farm.model.vo.common.Result;
import com.farm.model.vo.common.ResultCodeEnum;
import com.farm.service.PayMentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/payment")
public class PayMentController {
    @Autowired
    private PayMentService payMentService;

    @PostMapping("savePayMent/{orderNo}")
    public Result<PaymentInfo> savepay(@PathVariable(value = "orderNo") String orderNo) {
        PaymentInfo paymentInfo = payMentService.savePayment(orderNo);
        return Result.build(paymentInfo,ResultCodeEnum.SUCCESS);
    }
}

