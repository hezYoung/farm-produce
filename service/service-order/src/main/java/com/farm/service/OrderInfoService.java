/**
 * 1. @ClassName OrderInfoService
 * 2. @Description TODO
 * 3. @Author Young
 * 4. @Date 2023/12/15 16:51
 */
package com.farm.service;

import com.farm.model.dto.h5.OrderInfoDto;
import com.farm.model.entity.order.OrderInfo;
import com.farm.model.vo.h5.TradeVo;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface OrderInfoService {
    /**
     * 生成订单
     * @return
     */
    TradeVo getTrade();

    /**
     * 提交订单
     * @param orderInfoDto
     * @return
     */
    String submitOrder(OrderInfoDto orderInfoDto);

    /**
     * 订单号查询信息
     * @param orderId
     * @return
     */
    OrderInfo getOrderInfo(String orderId);

    /**
     * 订单详情
     * @param page
     * @param limit
     * @param orderStatus 状态
     * @return
     */
    PageInfo<OrderInfo> findUserPage(Integer page, Integer limit, Integer orderStatus,String nickName);

    /**
     * 订单号
     * @param orderNo
     * @return
     */
    OrderInfo getByOrderNo(String orderNo) ;

    /**
     * 更新支付状态
     * @param orderNo
     * @param
     */
    void updateOrderStatus(String orderNo);

    /**
     * 取消订单
     */
    void cancleOrder(String orderId);

    /**
     * 全部订单
     */
    List<OrderInfo> getallOrder(String nickName);
}

