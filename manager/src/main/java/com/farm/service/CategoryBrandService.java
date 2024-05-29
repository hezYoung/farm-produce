/**
 * 1. @ClassName CategoryBrandService
 * 2. @Description TODO
 * 3. @Author Young
 * 4. @Date 2023/11/7 11:17
 */
package com.farm.service;

import com.farm.model.dto.product.CategoryBrandDto;
import com.farm.model.entity.product.Brand;
import com.farm.model.entity.product.CategoryBrand;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface CategoryBrandService {
    PageInfo<CategoryBrand> getpage(Integer page, Integer limit, CategoryBrandDto categoryBrandDto);

    void insertlist(CategoryBrand categoryBrandDto);

    void updatebyid(CategoryBrand categoryBrand);

    void deletebyid(Long id);

    List<Brand> findBrandByCategoryId(Long categoryId);
}
