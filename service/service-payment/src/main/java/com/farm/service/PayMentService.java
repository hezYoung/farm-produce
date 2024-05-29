/**
 * 1. @ClassName PayMentService
 * 2. @Description TODO
 * 3. @Author Young
 * 4. @Date 2023/12/21 10:22
 */
package com.farm.service;

import com.farm.model.entity.pay.PaymentInfo;

public interface PayMentService {
    /**
     * 保存支付记录
     * @param orderNo
     */
    PaymentInfo savePayment(String orderNo);
}
