/**
 * 1. @ClassName ProductService
 * 2. @Description TODO
 * 3. @Author Young
 * 4. @Date 2023/11/29 9:34
 */
package com.farm.product.service;

import com.farm.model.dto.product.SkuSaleDto;
import com.farm.model.entity.product.ProductSku;
import com.farm.model.vo.h5.ProductItemVo;

import java.util.List;

public interface ProductService {
    /**
     * 获取首页热门商品
     * @return
     */
    List<ProductSku> findProductSkuBySale();

    /**
     * 获取商品列表
     * @return
     */
    List<ProductSku>  findByPage(Integer category3Id);
    /**详情
     * @return
     */
    ProductItemVo item(Long skuId);

    ProductSku getBySkuId(Long skuId);

    Boolean updateSkuSaleNum(List<SkuSaleDto> skuSaleDtoList);

    /**
     * 搜索
     * @param sKuname
     * @return
     */
    List<ProductSku> findBySkuName(String skuName);
}
