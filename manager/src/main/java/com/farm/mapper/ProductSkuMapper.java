/**
 * 1. @ClassName ProductSkuMapper
 * 2. @Description TODO
 * 3. @Author Young
 * 4. @Date 2023/11/10 14:20
 */
package com.farm.mapper;

import com.farm.model.entity.product.ProductSku;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProductSkuMapper {
    void insert(ProductSku productSku);

    public abstract List<ProductSku> selectByProductId(Long id);
    void udatebyid(ProductSku productSku);
    public abstract  void updateById(ProductSku productSku);
    void deleteByProductId(Long id);
    public abstract void save(ProductSku productSku);
}
