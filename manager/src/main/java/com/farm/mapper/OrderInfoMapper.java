/**
 * 1. @ClassName OrderInfoMapper
 * 2. @Description TODO
 * 3. @Author Young
 * 4. @Date 2023/11/15 16:04
 */
package com.farm.mapper;

import com.farm.model.entity.order.OrderInfo;
import com.farm.model.entity.order.OrderStatistics;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface OrderInfoMapper {
    OrderStatistics selectOrderStatistics(@Param("createTime") String createTime);

    List<OrderInfo> getallOrder();

    void updateById(OrderInfo orderInfo);

    void deleteById(Long id);
}
