/**
 * 1. @ClassName OrderInfoServiceImpl
 * 2. @Description TODO
 * 3. @Author Young
 * 4. @Date 2023/11/15 16:21
 */
package com.farm.service.impl;

import cn.hutool.core.date.DateUtil;
import com.farm.mapper.OrderInfoMapper;
import com.farm.mapper.OrderStatisticsMapper;
import com.farm.model.dto.order.OrderStatisticsDto;
import com.farm.model.entity.order.OrderInfo;
import com.farm.model.entity.order.OrderStatistics;
import com.farm.model.vo.order.OrderStatisticsVo;
import com.farm.service.OrderInfoService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;
@Service
public class OrderInfoServiceImpl implements OrderInfoService {
    @Resource
    private OrderStatisticsMapper orderStatisticsMapper;

    @Resource
    OrderInfoMapper orderInfoMapper;
    @Override
    public OrderStatisticsVo getOrderStatisticsData(OrderStatisticsDto orderStatisticsDto) {
//        // 查询统计结果数据
//        List<OrderStatistics> orderStatisticsList = orderStatisticsMapper.selectList(orderStatisticsDto) ;
//
//        //日期列表
//        List<String> dateList = orderStatisticsList.stream().map(orderStatistics -> DateUtil.format(orderStatistics.getOrderDate(), "yyyy-MM-dd")).collect(Collectors.toList());
//
//        //统计金额列表
//        List<BigDecimal> amountList = orderStatisticsList.stream().map(OrderStatistics::getTotalAmount).collect(Collectors.toList());
//
//        // 创建OrderStatisticsVo对象封装响应结果数据
//        OrderStatisticsVo orderStatisticsVo = new OrderStatisticsVo() ;
//        orderStatisticsVo.setDateList(dateList);
//        orderStatisticsVo.setAmountList(amountList);
//
//        // 返回数据
//        return orderStatisticsVo;
        // 查询统计结果数据
        List<OrderStatistics> orderStatisticsList = orderStatisticsMapper.selectList(orderStatisticsDto);

        // 仅保留最近六条数据
        List<OrderStatistics> recentOrderStatisticsList = orderStatisticsList.subList(0, Math.min(orderStatisticsList.size(), 6));

        // 日期列表
        List<String> dateList = recentOrderStatisticsList.stream()
                .map(orderStatistics -> DateUtil.format(orderStatistics.getOrderDate(), "yyyy-MM-dd"))
                .collect(Collectors.toList());

        // 统计金额列表
        List<BigDecimal> amountList = recentOrderStatisticsList.stream()
                .map(OrderStatistics::getTotalAmount)
                .collect(Collectors.toList());

        // 创建OrderStatisticsVo对象封装响应结果数据
        OrderStatisticsVo orderStatisticsVo = new OrderStatisticsVo();
        orderStatisticsVo.setDateList(dateList);
        orderStatisticsVo.setAmountList(amountList);

        // 返回数据
        return orderStatisticsVo;

    }

    @Override
    public void removeById(Long id) {
        orderInfoMapper.deleteById(id);
    }

    @Override
    public void udatebyid(OrderInfo orderInfo) {
        orderInfoMapper.updateById(orderInfo);
    }

    @Override
    public PageInfo<OrderInfo> getallOrder(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
       List<OrderInfo> orderInfoList= orderInfoMapper.getallOrder();
        PageInfo<OrderInfo> orderInfoPageInfo = new PageInfo<>(orderInfoList);
        return orderInfoPageInfo;
    }
}

