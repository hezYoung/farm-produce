/**
 * 1. @ClassName PayMentServiceImpl
 * 2. @Description TODO
 * 3. @Author Young
 * 4. @Date 2023/12/21 10:22
 */
package com.farm.service.impl;

import com.farm.feign.order.OrderFeignClient;
import com.farm.feign.product.ProductFeignClient;
import com.farm.mapper.PayMentMapper;
import com.farm.model.dto.product.SkuSaleDto;
import com.farm.model.entity.order.OrderInfo;
import com.farm.model.entity.order.OrderItem;
import com.farm.model.entity.pay.PaymentInfo;
import com.farm.model.vo.common.Result;
import com.farm.service.PayMentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PayMentServiceImpl implements PayMentService {
    @Autowired
    private PayMentMapper payMentMapper;

    @Autowired
    private ProductFeignClient productFeignClient;

    @Autowired
    private OrderFeignClient orderFeignClient ;
    @Transactional
    @Override
    public PaymentInfo savePayment(String orderNo) {
        // 查询支付信息数据，如果已经已经存在了就不用进行保存(一个订单支付失败以后可以继续支付)
        PaymentInfo paymentInfo = payMentMapper.getByOrderNo(orderNo);
        if(null == paymentInfo) {
            OrderInfo orderInfo = orderFeignClient.getOrderInfoByOrderNo(orderNo).getData();
            paymentInfo = new PaymentInfo();
            paymentInfo.setUserId(orderInfo.getUserId());
            paymentInfo.setPayType(orderInfo.getPayType());
            String content = "";
            for(OrderItem item : orderInfo.getOrderItemList()) {
                content += item.getSkuName() + " ";
            }
            paymentInfo.setContent(content);
            paymentInfo.setAmount(orderInfo.getTotalAmount());
            paymentInfo.setOrderNo(orderNo);
            paymentInfo.setPaymentStatus(0);
            payMentMapper.save(paymentInfo);
        }

        // 3、更新订单的支付状态
        orderFeignClient.updateOrderStatus(orderNo);
        // 4、更新商品销量
        Result<OrderInfo> orderInfoByOrderNo = orderFeignClient.getOrderInfoByOrderNo(paymentInfo.getOrderNo());
        OrderInfo data = orderInfoByOrderNo.getData();
        List<SkuSaleDto> skuSaleDtoList = data.getOrderItemList().stream().map(item -> {
            SkuSaleDto skuSaleDto = new SkuSaleDto();
            skuSaleDto.setSkuId(item.getSkuId());
            skuSaleDto.setNum(item.getSkuNum());
            return skuSaleDto;
        }).collect(Collectors.toList());
        productFeignClient.updateSkuSaleNum(skuSaleDtoList) ;
        return paymentInfo;

    }
}

