/**
 * 1. @ClassName OrderController
 * 2. @Description TODO
 * 3. @Author Young
 * 4. @Date 2023/12/15 16:26
 */
package com.farm.controller;

import com.farm.model.dto.h5.OrderInfoDto;
import com.farm.model.entity.order.OrderInfo;
import com.farm.model.vo.common.Result;
import com.farm.model.vo.common.ResultCodeEnum;
import com.farm.model.vo.h5.TradeVo;
import com.farm.mq.DelayedMQSender;
import com.farm.service.OrderInfoService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/order/orderInfo")
public class OrderController {

    @Autowired
    private OrderInfoService orderInfoService;
    @Autowired
    DelayedMQSender delayedMQSender;

    @GetMapping("trade")
    public Result<TradeVo> trade() {
        TradeVo tradeVo = orderInfoService.getTrade();
        return Result.build(tradeVo, ResultCodeEnum.SUCCESS);
    }

    @PostMapping("submitOrder")
    public Result<Long> submitOrder(@RequestBody OrderInfoDto orderInfoDto, @RequestParam(required = false, defaultValue = "60000") Integer delayTime) {
        String orderId = orderInfoService.submitOrder(orderInfoDto);
        System.out.println(orderId);
        delayedMQSender.Send(orderId, delayTime);
        return Result.build(orderId, ResultCodeEnum.SUCCESS);
    }

    @GetMapping("pay/{orderId}")
    public Result<OrderInfo> getOrderInfo(@PathVariable String orderId) {
        OrderInfo orderInfo = orderInfoService.getOrderInfo(orderId);
        return Result.build(orderInfo, ResultCodeEnum.SUCCESS);
    }

    @GetMapping("order/{nickName}")
    public Result<PageInfo<OrderInfo>> list(
            Integer page,
            Integer limit,
            @PathVariable String nickName,
            Integer orderStatus) {
        System.out.println(page + limit + nickName + orderStatus);
        PageInfo<OrderInfo> pageInfo = orderInfoService.findUserPage(page, limit, orderStatus, nickName);
        return Result.build(pageInfo, ResultCodeEnum.SUCCESS);
    }

    @GetMapping("getOrderInfoByOrderNo/{orderNo}")
    public Result<OrderInfo> getOrderInfoByOrderNo(@PathVariable String orderNo) {
        OrderInfo orderInfo = orderInfoService.getByOrderNo(orderNo);
        return Result.build(orderInfo, ResultCodeEnum.SUCCESS);
    }

    @GetMapping("updateOrderStatus/{orderNo}")
    public Result updateOrderStatus(@PathVariable(value = "orderNo") String orderNo) {
        orderInfoService.updateOrderStatus(orderNo);
        return Result.build(null, ResultCodeEnum.SUCCESS);
    }

    @GetMapping("allOrder/{nickName}")
    public Result allOrder( @PathVariable String nickName) {
        List<OrderInfo> orderInfo= orderInfoService.getallOrder(nickName);
        return Result.build(orderInfo, ResultCodeEnum.SUCCESS);
    }
}
