/**
 * 1. @ClassName OrderInfoService
 * 2. @Description TODO
 * 3. @Author Young
 * 4. @Date 2023/11/15 16:21
 */
package com.farm.service;

import com.farm.model.dto.order.OrderStatisticsDto;
import com.farm.model.entity.order.OrderInfo;
import com.farm.model.vo.order.OrderStatisticsVo;
import com.github.pagehelper.PageInfo;

public interface OrderInfoService {
    /**
     * 拉单统计
     * @param orderStatisticsDto
     * @return
     */
    OrderStatisticsVo getOrderStatisticsData(OrderStatisticsDto orderStatisticsDto);

    /**
     * 获取所有订单
     *
     * @return
     */

    PageInfo<OrderInfo> getallOrder(Integer pageNum, Integer pageSize);

    /**
     * 修改订单内容
     * @param orderInfo
     */
    void udatebyid(OrderInfo orderInfo);

    /**
     * 删除订单
     * @param id
     */
    void removeById(Long id);
}
