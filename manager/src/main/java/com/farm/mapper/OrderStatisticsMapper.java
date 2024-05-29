/**
 * 1. @ClassName OrderStatisticsMapper
 * 2. @Description TODO
 * 3. @Author Young
 * 4. @Date 2023/11/15 16:05
 */
package com.farm.mapper;

import com.farm.model.dto.order.OrderStatisticsDto;
import com.farm.model.entity.order.OrderStatistics;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface OrderStatisticsMapper {
    void insert(OrderStatistics orderStatistics);

    List<OrderStatistics> selectList(OrderStatisticsDto orderStatisticsDto);
}
