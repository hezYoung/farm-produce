/**
 * 1. @ClassName ProductSpecMapper
 * 2. @Description TODO
 * 3. @Author Young
 * 4. @Date 2023/11/9 14:08
 */
package com.farm.mapper;

import com.farm.model.entity.product.ProductSpec;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProductSpecMapper {
    List<ProductSpec> getpagelist();

    void insert(ProductSpec productSpec);

    void updatebyid(ProductSpec productSpec);

    void deletebyid(long id);

    List<ProductSpec> findall();
}
