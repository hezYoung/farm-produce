/**
 * 1. @ClassName CartService
 * 2. @Description TODO
 * 3. @Author Young
 * 4. @Date 2023/12/13 14:07
 */
package com.farm.service;

import com.farm.model.entity.h5.CartInfo;

import java.util.List;

public interface CartService {
    /**
     * 加入购物车
     * @param skuId
     * @param skuNum
     */
    void addToCart(Long skuId, Integer skuNum);

    /**
     * 查询购物车
     * @return
     */
    List<CartInfo> getCartList();

    /**
     * 删除购物车
     * @param skuId
     */
    void deleteCart(Long skuId);

    /**
     * 选中购物车
     * @param skuId
     * @param isChecked
     */
    void checkCart(Long skuId, Integer isChecked);

    /**
     * 获取选中的购物车内容
     * @return
     */
    List<CartInfo> getCkecked();

    /**
     * 删除选中的购物车
     */
    void deleteChecked();
}
