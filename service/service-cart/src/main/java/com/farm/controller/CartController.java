/**
 * 1. @ClassName CartController
 * 2. @Description TODO
 * 3. @Author Young
 * 4. @Date 2023/12/13 14:06
 */
package com.farm.controller;

import com.farm.model.entity.h5.CartInfo;
import com.farm.model.vo.common.Result;
import com.farm.model.vo.common.ResultCodeEnum;
import com.farm.service.CartService;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/order/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @GetMapping("addToCart/{skuId}/{skuNum}")
    public Result addToCart( @PathVariable("skuId") Long skuId,
                           @PathVariable("skuNum") Integer skuNum) {
        cartService.addToCart(skuId, skuNum);
        return Result.build(null, ResultCodeEnum.SUCCESS);
    }
    @GetMapping("cartList")
    public Result<List<CartInfo>> cartList() {
        List<CartInfo> cartInfoList = cartService.getCartList();
        return Result.build(cartInfoList, ResultCodeEnum.SUCCESS);
    }
    @DeleteMapping("deleteCart/{skuId}")
    public Result deleteCart(@PathVariable("skuId") Long skuId) {
        cartService.deleteCart(skuId);
        return Result.build(null, ResultCodeEnum.SUCCESS);
    }
    @GetMapping(value = "deleteChecked")
    public Result deleteChecked() {
        cartService.deleteChecked() ;
        return Result.build(null , ResultCodeEnum.SUCCESS) ;
    }
    @GetMapping("checkCart/{skuId}/{isChecked}")
    public Result checkCart(@PathVariable(value = "skuId") Long skuId,
                             @PathVariable(value = "isChecked") Integer isChecked) {
        cartService.checkCart(skuId, isChecked);
        return Result.build(null, ResultCodeEnum.SUCCESS);
    }
    @GetMapping(value = "/getCkecked")
    public List<CartInfo> getCkecked() {
        List<CartInfo> cartInfo = cartService.getCkecked() ;
        return cartInfo;
    }
}
