/**
 * 1. @ClassName MakeOrderMapper
 * 2. @Description TODO
 * 3. @Author Young
 * 4. @Date 2023/12/18 16:41
 */
package com.farm.mapper;

import com.farm.model.entity.order.OrderInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface MakeOrderMapper {
    /**
     * 保存订单内容
     * @param orderInfo
     */
    void save(OrderInfo orderInfo);

    /**
     * 订单号查询信息
     * @param orderId
     * @return
     */
    OrderInfo getById(@Param("orderId") String orderId);

    /**
     * 分页查询订单详情
     * @param nickName
     * @param orderStatus
     * @return
     */
    List<OrderInfo> findUserPage(String nickName, Integer orderStatus);
    /**
     * 保存支付记录
     */
    OrderInfo getByOrderNo(String orderNo) ;

    void updateById(OrderInfo orderInfo);

    /**
     * 取消订单
     * @param orderId
     */
    void cancleOrder(String orderId);

    /**
     * 所有订单
     * @return
     */
    List<OrderInfo> getallOrder(String nickName);
}
