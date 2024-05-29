/**
 * 1. @ClassName ProductDetailsMapper
 * 2. @Description TODO
 * 3. @Author Young
 * 4. @Date 2023/12/7 14:39
 */
package com.farm.product.mapper;

import com.farm.model.entity.product.ProductDetails;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ProductDetailsMapper {
    ProductDetails getByProductId(Long productId);
}
