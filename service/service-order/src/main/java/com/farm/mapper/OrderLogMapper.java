/**
 * 1. @ClassName OrderLogMapper
 * 2. @Description TODO
 * 3. @Author Young
 * 4. @Date 2023/12/18 16:35
 */
package com.farm.mapper;

import com.farm.model.entity.order.OrderLog;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OrderLogMapper {
    /**
     * 订单日志备注
     * @param orderLog
     */
    void save(OrderLog orderLog);
}
