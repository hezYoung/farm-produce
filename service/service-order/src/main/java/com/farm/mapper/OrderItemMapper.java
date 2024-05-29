/**
 * 1. @ClassName OrderItemMapper
 * 2. @Description TODO
 * 3. @Author Young
 * 4. @Date 2023/12/18 16:35
 */
package com.farm.mapper;

import com.farm.model.entity.order.OrderItem;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface OrderItemMapper {
    /**
     * 订单明细
     * @param orderItem
     */
    void save(OrderItem orderItem);

    /**
     * 订单商品详情
     * @param orderId
     * @return
     */
    List<OrderItem> findByOrderId(long orderId);
}
