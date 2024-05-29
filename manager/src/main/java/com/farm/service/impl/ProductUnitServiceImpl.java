/**
 * 1. @ClassName ProductUnitServiceImpl
 * 2. @Description TODO
 * 3. @Author Young
 * 4. @Date 2023/11/10 10:56
 */
package com.farm.service.impl;

import com.farm.mapper.ProductMapper;
import com.farm.mapper.ProductUnitMapper;
import com.farm.model.entity.base.ProductUnit;
import com.farm.service.ProductService;
import com.farm.service.ProductUnitService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductUnitServiceImpl implements ProductUnitService {
    @Resource
    private ProductUnitMapper productUnitMapper;

    @Override
    public List<ProductUnit> findall() {
        return productUnitMapper.findall();
    }
}

