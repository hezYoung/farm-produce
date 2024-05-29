///**
// * 1. @ClassName OrderFeignClient
// * 2. @Description TODO
// * 3. @Author Young
// * 4. @Date 2023/12/28 16:18
// */
//package com.farm.feign.cart.fallback;
//
//import com.farm.feign.cart.CartFeignClient;
//import com.farm.model.entity.h5.CartInfo;
//import com.farm.model.entity.order.OrderInfo;
//import com.farm.model.vo.common.Result;
//import com.farm.model.vo.common.ResultCodeEnum;
//import org.springframework.stereotype.Component;
//
//import java.util.ArrayList;
//import java.util.List;
//
///**
// * 降级处理
// */
//@Component
//public class CartFeignClientFallback implements CartFeignClient {
//
//
//    @Override
//    public List<CartInfo> getCkecked() {
//        throw new RuntimeException("数据异常");
//    }
//
//    @Override
//    public Result deleteChecked() {
//        return Result.build("获取数据失败，超时", ResultCodeEnum.DATA_ERROR);
//    }
//}
//
