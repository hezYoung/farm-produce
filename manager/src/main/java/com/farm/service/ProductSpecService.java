/**
 * 1. @ClassName ProductSpecService
 * 2. @Description TODO
 * 3. @Author Young
 * 4. @Date 2023/11/9 14:09
 */
package com.farm.service;

import com.farm.model.entity.product.ProductSpec;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface ProductSpecService {
    PageInfo<ProductSpec> getpagelist(Integer page, Integer limit);

    void insert(ProductSpec productSpec);

    void updatebyid(ProductSpec productSpec);

    void deletebyid(long id);

    List<ProductSpec> findall();
}
