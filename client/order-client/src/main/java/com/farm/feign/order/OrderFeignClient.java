/**
 * 1. @ClassName OrderFeignClient
 * 2. @Description TODO
 * 3. @Author Young
 * 4. @Date 2023/12/21 10:14
 */
package com.farm.feign.order;

import com.farm.model.entity.order.OrderInfo;
import com.farm.model.vo.common.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "service-order")
public interface OrderFeignClient {

        @GetMapping("/api/order/orderInfo/updateOrderStatus/{orderNo}")
        public abstract Result updateOrderStatus(@PathVariable(value = "orderNo") String orderNo ) ;
        @GetMapping("/api/order/orderInfo/getOrderInfoByOrderNo/{orderNo}")
        public Result<OrderInfo> getOrderInfoByOrderNo(@PathVariable String orderNo) ;

}
