/**
 * 1. @ClassName ProductSpecServiceImpl
 * 2. @Description TODO
 * 3. @Author Young
 * 4. @Date 2023/11/9 14:09
 */
package com.farm.service.impl;

import com.farm.mapper.ProductSpecMapper;
import com.farm.model.entity.product.ProductSpec;
import com.farm.service.ProductSpecService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductSpecServiceImpl implements ProductSpecService {
    @Resource
    private ProductSpecMapper productSpecMapper;

    @Override
    public void insert(ProductSpec productSpec) {
        productSpecMapper.insert(productSpec);
    }

    @Override
    public void updatebyid(ProductSpec productSpec) {
        productSpecMapper.updatebyid(productSpec);
    }

    @Override
    public void deletebyid(long id) {
        productSpecMapper.deletebyid(id);
    }

    @Override
    public PageInfo<ProductSpec> getpagelist(Integer page, Integer limit) {
        PageHelper.startPage(page, limit);
        List<ProductSpec> productSpecs=productSpecMapper.getpagelist();
        PageInfo pageInfo = new PageInfo(productSpecs);
        return pageInfo;

    }

    @Override
    public List<ProductSpec> findall() {
        return productSpecMapper.findall();
    }
}

