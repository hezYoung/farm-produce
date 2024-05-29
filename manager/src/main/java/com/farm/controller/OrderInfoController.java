/**
 * 1. @ClassName OrderInfoController
 * 2. @Description TODO
 * 3. @Author Young
 * 4. @Date 2023/11/15 16:14
 */
package com.farm.controller;

import com.farm.model.dto.h5.OrderInfoDto;
import com.farm.model.dto.order.OrderStatisticsDto;
import com.farm.model.entity.order.OrderInfo;
import com.farm.model.entity.system.SysMenu;
import com.farm.model.entity.system.SysRole;
import com.farm.model.vo.common.Result;
import com.farm.model.vo.common.ResultCodeEnum;
import com.farm.model.vo.order.OrderStatisticsVo;
import com.farm.service.OrderInfoService;
import com.github.pagehelper.PageInfo;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;

@RestController
@RequestMapping(value = "/admin/order/orderInfo")
public class OrderInfoController {
    @Resource
    private OrderInfoService orderInfoService;

    @GetMapping("/getOrderStatisticsData")
    public Result<OrderStatisticsVo> getOrderStatisticsData(OrderStatisticsDto orderStatisticsDto) {
        OrderStatisticsVo orderStatisticsVo = orderInfoService.getOrderStatisticsData(orderStatisticsDto);
        return Result.build(orderStatisticsVo, ResultCodeEnum.SUCCESS);
    }

    @GetMapping("/allOrder/{pageNum}/{pageSize}")
    public Result<PageInfo<OrderInfo>> allOrder(@PathVariable(value = "pageNum") Integer pageNum ,
                                                @PathVariable(value = "pageSize") Integer pageSize){
        PageInfo<OrderInfo> orderInfo =  orderInfoService.getallOrder(pageNum , pageSize);
        return Result.build(orderInfo, ResultCodeEnum.SUCCESS);
    }
    @PutMapping("/updateById")
    public Result updatebyid(@RequestBody OrderInfo orderInfo){
        orderInfoService.udatebyid(orderInfo);
        return Result.build(null,ResultCodeEnum.SUCCESS);
    }
    @DeleteMapping("/removeById/{id}")
    public Result removeById(@PathVariable Long id) {
        orderInfoService.removeById(id);
        return Result.build(null , ResultCodeEnum.SUCCESS) ;
    }

}

