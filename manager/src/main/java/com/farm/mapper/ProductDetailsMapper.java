/**
 * 1. @ClassName ProductDetailsMapper
 * 2. @Description TODO
 * 3. @Author Young
 * 4. @Date 2023/11/10 14:21
 */
package com.farm.mapper;

import com.farm.model.entity.product.ProductDetails;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ProductDetailsMapper {
    void insert(ProductDetails productDetails);
    public abstract void updateById(ProductDetails productDetails);
    public abstract ProductDetails selectByProductId(Long id);
    void udatebyid(ProductDetails productDetails);
    public abstract void save(ProductDetails productDetails);
    void deleteByProductId(Long id);
}
