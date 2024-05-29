/**
 * 1. @ClassName CartFeignClient
 * 2. @Description TODO
 * 3. @Author Young
 * 4. @Date 2023/12/15 15:49
 */
package com.farm.feign.cart;


import com.farm.model.entity.h5.CartInfo;
import com.farm.model.vo.common.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(value = "service-cart")
public interface CartFeignClient {

    @GetMapping(value = "/api/order/cart/getCkecked")
    public abstract List<CartInfo> getCkecked() ;
    @GetMapping(value = "/api/order/cart/deleteChecked")
    public abstract Result deleteChecked() ;

}