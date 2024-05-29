/**
 * 1. @ClassName CategoryBrandMapper
 * 2. @Description TODO
 * 3. @Author Young
 * 4. @Date 2023/11/7 11:16
 */
package com.farm.mapper;

import com.farm.model.dto.product.CategoryBrandDto;
import com.farm.model.entity.product.Brand;
import com.farm.model.entity.product.CategoryBrand;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CategoryBrandMapper {
    List<CategoryBrandDto> getpage(CategoryBrandDto categoryBrandDto);

    void insertlist(CategoryBrand categoryBrandDto);

    void updatebyid(CategoryBrand categoryBrand);

    void deletebyid(Long id);

    public abstract List<Brand> findBrandByCategoryId(Long categoryId);}
