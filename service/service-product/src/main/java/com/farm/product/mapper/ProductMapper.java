/**
 * 1. @ClassName ProductMapper
 * 2. @Description TODO
 * 3. @Author Young
 * 4. @Date 2023/12/7 14:35
 */
package com.farm.product.mapper;

import com.farm.model.entity.product.Product;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ProductMapper {
    Product getById(Long productId);
}
