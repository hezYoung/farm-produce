/**
 * 1. @ClassName ProductService
 * 2. @Description TODO
 * 3. @Author Young
 * 4. @Date 2023/11/10 9:45
 */
package com.farm.service;

import com.farm.model.dto.product.ProductDto;
import com.farm.model.entity.product.Product;
import com.github.pagehelper.PageInfo;

public interface ProductService {
    PageInfo<Product> getpagelist(Integer page, Integer limit, ProductDto productDto);

    void insertproduct(Product product);

    Product getById(Long id);

    void updateById(Product product);

    void deleteById(Long id);

    void updateAuditStatus(Long id, Integer auditStatus);

    void updateStatus(Long id, Integer status);
}
