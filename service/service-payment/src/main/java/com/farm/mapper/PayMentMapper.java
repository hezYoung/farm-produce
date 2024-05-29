/**
 * 1. @ClassName PayMentMapper
 * 2. @Description TODO
 * 3. @Author Young
 * 4. @Date 2023/12/21 10:23
 */
package com.farm.mapper;

import com.farm.model.entity.pay.PaymentInfo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PayMentMapper {
    PaymentInfo getByOrderNo(String orderNo);

    void save(PaymentInfo paymentInfo);
}
