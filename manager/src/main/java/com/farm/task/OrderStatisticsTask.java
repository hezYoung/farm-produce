/**
 * 1. @ClassName OrderStatisticsTask
 * 2. @Description TODO
 * 3. @Author Young
 * 4. @Date 2023/11/15 16:03
 */
package com.farm.task;

import cn.hutool.core.date.DateUtil;
import com.farm.mapper.OrderInfoMapper;
import com.farm.mapper.OrderStatisticsMapper;
import com.farm.model.entity.order.OrderStatistics;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component
@Slf4j
public class OrderStatisticsTask {
    @Autowired
    private OrderInfoMapper orderInfoMapper;

    @Autowired
    private OrderStatisticsMapper orderStatisticsMapper;
//    @Scheduled(cron = "0 */2 * * * ?")
    @Scheduled(cron = "0 0 10,12,15 * * ?")
    public void orderTotalAmountStatistics() {
        String createTime = DateUtil.offsetDay(new Date(), -1).toString(new SimpleDateFormat("yyyy-MM-dd"));
        System.out.println(createTime);
        OrderStatistics orderStatistics = orderInfoMapper.selectOrderStatistics(createTime);
        if(orderStatistics != null) {
            orderStatisticsMapper.insert(orderStatistics) ;
        }
    }
}

