package com.farm.model.dto.h5;


import com.farm.model.entity.order.OrderItem;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class OrderInfoDto {

    //送货地址id
    private Long userAddressId;

    //下单用户
    private String nickName;

    //商品
    private String skuName;

    //运费
    private BigDecimal feightFee;

    //备注
    private String remark;

    //订单明细
    private List<OrderItem> orderItemList;

    private String address; // 收货地址

    private String consignee; // 收货人

    private String telNumber;//手机号
}