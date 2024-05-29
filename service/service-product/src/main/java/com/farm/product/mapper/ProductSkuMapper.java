/**
 * 1. @ClassName ProductMapper
 * 2. @Description TODO
 * 3. @Author Young
 * 4. @Date 2023/11/29 9:39
 */
package com.farm.product.mapper;

import com.farm.model.entity.product.ProductSku;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ProductSkuMapper {
    List<ProductSku> findProductSkuBySale();

    List<ProductSku>  findByPage(Integer category3Id);

    ProductSku getById(Long skuId);

    List<ProductSku> findByProductId(Long productId);

    void updateSale(@Param("skuId") Long skuId, @Param("num") Integer num);

    List<ProductSku> findBySkuName(@Param("skuName") String skuName);
}
