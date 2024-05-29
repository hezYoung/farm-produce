/**
 * 1. @ClassName BrandService
 * 2. @Description TODO
 * 3. @Author Young
 * 4. @Date 2023/11/2 17:19
 */
package com.farm.service;

import com.farm.model.entity.product.Brand;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface BrandService {
    PageInfo<Brand> findByPage(Integer page, Integer limit);

    void save(Brand brand);

    void updateById(Brand brand);

    void deletebyid(long id);

    List<Brand> findall();
}
