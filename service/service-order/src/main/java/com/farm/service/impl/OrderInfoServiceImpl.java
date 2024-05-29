/**
 * 1. @ClassName OrderInfoServiceImpl
 * 2. @Description TODO
 * 3. @Author Young
 * 4. @Date 2023/12/15 16:51
 */
package com.farm.service.impl;

import com.farm.exception.ZdyException;
import com.farm.feign.cart.CartFeignClient;
import com.farm.feign.product.ProductFeignClient;
import com.farm.mapper.MakeOrderMapper;
import com.farm.mapper.OrderItemMapper;
import com.farm.mapper.OrderLogMapper;
import com.farm.model.dto.h5.OrderInfoDto;
import com.farm.model.entity.h5.CartInfo;
import com.farm.model.entity.order.OrderInfo;
import com.farm.model.entity.order.OrderItem;
import com.farm.model.entity.order.OrderLog;
import com.farm.model.entity.product.ProductSku;
import com.farm.model.vo.common.ResultCodeEnum;
import com.farm.model.vo.h5.TradeVo;
import com.farm.service.OrderInfoService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;

@Service
public class OrderInfoServiceImpl implements OrderInfoService {

    @Autowired
    private CartFeignClient cartFeignClient ;

    @Autowired
    private ProductFeignClient productFeignClient;

@Autowired
private MakeOrderMapper makeOrderMapper;


    @Autowired
    private OrderItemMapper orderItemMapper;

    @Autowired
    private OrderLogMapper orderLogMapper;
    @Override
    public TradeVo getTrade() {
        // 获取选中的购物项列表数据
        List<CartInfo> cartInfoList = cartFeignClient.getCkecked() ;
        List<OrderItem> orderItemList = new ArrayList<>();
        for (CartInfo cartInfo : cartInfoList) {        // 将购物项数据转换成功订单明细数据
            OrderItem orderItem = new OrderItem();
            orderItem.setSkuId(cartInfo.getSkuId());
            orderItem.setSkuName(cartInfo.getSkuName());
            orderItem.setSkuNum(cartInfo.getSkuNum());
            orderItem.setSkuPrice(cartInfo.getCartPrice());
            orderItem.setThumbImg(cartInfo.getImgUrl());
            orderItemList.add(orderItem);
        }

        // 计算总金额
        BigDecimal totalAmount = new BigDecimal(0);
        for(OrderItem orderItem : orderItemList) {
            totalAmount = totalAmount.add(orderItem.getSkuPrice().multiply(new BigDecimal(orderItem.getSkuNum())));
        }
        TradeVo tradeVo = new TradeVo();
        tradeVo.setTotalAmount(totalAmount);
        tradeVo.setOrderItemList(orderItemList);
        return tradeVo;
    }

    @Transactional
    @Override
    public String submitOrder(OrderInfoDto orderInfoDto) {
        // 数据校验
        List<OrderItem> orderItemList = orderInfoDto.getOrderItemList();
        if (CollectionUtils.isEmpty(orderItemList)) {
            throw new ZdyException(ResultCodeEnum.DATA_ERROR);
        }
        //AtomicBoolean isValid = new AtomicBoolean(true);
        for (OrderItem orderItem : orderItemList) {
            ProductSku productSku = productFeignClient.getBySkuId(orderItem.getSkuId());
            if(null == productSku) {
                throw new ZdyException(ResultCodeEnum.DATA_ERROR);
            }
            //校验库存
            if(orderItem.getSkuNum().intValue() > productSku.getStockNum().intValue()) {
                throw new ZdyException(ResultCodeEnum.STOCK_LESS);
            }
//            if (null == productSku || orderItem.getSkuNum().intValue() > productSku.getStockNum().intValue()) {
//                isValid.set(false);
//                break;
//            }
        }
//        if (!isValid.get()) {
//            throw new ZdyException(ResultCodeEnum.DATA_ERROR);
//        }
        // 构建订单数据，保存订单
        OrderInfo orderInfo = new OrderInfo();
        //用户id
        long cartId=999;
        orderInfo.setUserId(cartId);
        //订单编号
        orderInfo.setOrderNo(String.valueOf(System.currentTimeMillis()));
        //订单商品
        StringBuilder skuNamesBuilder = new StringBuilder();
        for (OrderItem orderItem : orderItemList) {
            skuNamesBuilder.append(orderItem.getSkuName()).append(", "); // 拼接skuName并用逗号分隔
        }
        String allSkuNames = skuNamesBuilder.toString();
        // 去除最后一个逗号和空格
        if (allSkuNames.length() > 0) {
            allSkuNames = allSkuNames.substring(0, allSkuNames.length() - 2);
        }
        orderInfo.setSkuName(allSkuNames);
        //用户昵称
        orderInfo.setNickName(orderInfoDto.getNickName());
        //用户收货地址信息
        orderInfo.setReceiverName(orderInfoDto.getConsignee());
        orderInfo.setReceiverPhone(orderInfoDto.getTelNumber());
        orderInfo.setReceiverAddress(orderInfoDto.getAddress());

        //订单金额
        BigDecimal totalAmount = new BigDecimal(0);
        for (OrderItem orderItem : orderItemList) {
            totalAmount = totalAmount.add(orderItem.getSkuPrice().multiply(new BigDecimal(orderItem.getSkuNum())));
        }
        orderInfo.setTotalAmount(totalAmount);
        orderInfo.setCouponAmount(new BigDecimal(0));
        orderInfo.setOriginalTotalAmount(totalAmount);
        orderInfo.setFeightFee(orderInfoDto.getFeightFee());
        orderInfo.setPayType(2);
        orderInfo.setOrderStatus(0);
        makeOrderMapper.save(orderInfo);

        //保存订单明细
        for (OrderItem orderItem : orderItemList) {
            orderItem.setOrderId(orderInfo.getId());
            orderItemMapper.save(orderItem);
        }

        //记录日志
        OrderLog orderLog = new OrderLog();
        orderLog.setOrderId(orderInfo.getId());
        orderLog.setProcessStatus(0);
        orderLog.setNote("提交订单");
        orderLogMapper.save(orderLog);

        cartFeignClient.deleteChecked() ;

        return orderInfo.getOrderNo();
    }

    @Override
    public OrderInfo getOrderInfo(String orderId) {
        return makeOrderMapper.getById(orderId);
    }

    @Override
    public PageInfo<OrderInfo> findUserPage(Integer page, Integer limit, Integer orderStatus, String nickName) {
        PageHelper.startPage(page, limit);

        List<OrderInfo> orderInfoList = makeOrderMapper.findUserPage(nickName, orderStatus);

        List<CompletableFuture<Void>> futures = orderInfoList.stream()
                .map(orderInfo -> CompletableFuture.runAsync(() -> {
                    List<OrderItem> orderItem = orderItemMapper.findByOrderId(orderInfo.getId());
                    orderInfo.setOrderItemList(orderItem);
                }))
                .collect(Collectors.toList());

        CompletableFuture<Void> allOf = CompletableFuture.allOf(futures.toArray(new CompletableFuture[0]));
        allOf.join();

        return new PageInfo<>(orderInfoList);
    }


    @Override
    public OrderInfo getByOrderNo(String orderNo) {
        OrderInfo orderInfo = makeOrderMapper.getByOrderNo(orderNo);
        List<OrderItem> orderItem = orderItemMapper.findByOrderId(orderInfo.getId());
        orderInfo.setOrderItemList(orderItem);
        return orderInfo;
    }

    @Transactional
    @Override
    public void updateOrderStatus(String orderNo) {

        // 更新订单状态
        OrderInfo orderInfo = makeOrderMapper.getByOrderNo(orderNo);
        orderInfo.setOrderStatus(1);
        orderInfo.setPayType(2);
        orderInfo.setPaymentTime(new Date());
        makeOrderMapper.updateById(orderInfo);

        // 记录日志
        OrderLog orderLog = new OrderLog();
        orderLog.setOrderId(orderInfo.getId());
        orderLog.setProcessStatus(1);
        orderLog.setNote("支付宝支付成功");
        orderLogMapper.save(orderLog);
    }

    @Override
    public void cancleOrder(String orderId) {
    makeOrderMapper.cancleOrder(orderId);
    }

    @Override
    public List<OrderInfo> getallOrder(String nickName) {
        List<OrderInfo> orderInfoList = makeOrderMapper.getallOrder(nickName);

        orderInfoList.forEach(orderInfo -> {
            List<OrderItem> orderItem = orderItemMapper.findByOrderId(orderInfo.getId());
            orderInfo.setOrderItemList(orderItem);
        });

        return orderInfoList;
    }
}

