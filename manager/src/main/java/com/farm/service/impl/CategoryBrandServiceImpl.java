/**
 * 1. @ClassName CategoryBrandServiceImpl
 * 2. @Description TODO
 * 3. @Author Young
 * 4. @Date 2023/11/7 11:17
 */
package com.farm.service.impl;

import com.farm.mapper.CategoryBrandMapper;
import com.farm.model.dto.product.CategoryBrandDto;
import com.farm.model.entity.product.Brand;
import com.farm.model.entity.product.CategoryBrand;
import com.farm.service.CategoryBrandService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryBrandServiceImpl implements CategoryBrandService {
    @Resource
    private CategoryBrandMapper categoryBrandMapper;



    @Override
    public void deletebyid(Long id) {
        categoryBrandMapper.deletebyid(id);
    }

    @Override
    public List<Brand> findBrandByCategoryId(Long categoryId) {
        return categoryBrandMapper.findBrandByCategoryId(categoryId);
    }

    @Override
    public PageInfo<CategoryBrand> getpage(Integer page, Integer limit, CategoryBrandDto categoryBrandDto) {
        PageHelper.startPage(page,limit);
       List<CategoryBrandDto> categoryBrandDtoList= categoryBrandMapper.getpage(categoryBrandDto);
        PageInfo objectPageInfo = new PageInfo(categoryBrandDtoList);
        return objectPageInfo;
    }

    @Override
    public void updatebyid(CategoryBrand categoryBrand) {
        categoryBrandMapper.updatebyid(categoryBrand);
    }

    @Override
    public void insertlist(CategoryBrand categoryBrandDto) {
        categoryBrandMapper.insertlist(categoryBrandDto);
    }
}

