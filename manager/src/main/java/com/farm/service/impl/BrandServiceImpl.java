/**
 * 1. @ClassName BrandServiceImpl
 * 2. @Description TODO
 * 3. @Author Young
 * 4. @Date 2023/11/2 17:19
 */
package com.farm.service.impl;

import com.farm.mapper.BrandMapper;
import com.farm.model.entity.product.Brand;
import com.farm.service.BrandService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BrandServiceImpl implements BrandService {
    @Autowired
    private BrandMapper brandMapper;
    @Override
    public PageInfo<Brand> findByPage(Integer page, Integer limit) {
        PageHelper.startPage(page, limit);
        List<Brand> brandList =brandMapper.findpage();
        PageInfo<Brand> brandPageInfo = new PageInfo<>(brandList);
        return brandPageInfo;
    }

    @Override
    public List<Brand> findall() {

      List<Brand> brandList= brandMapper.findall();
      return brandList;
    }

    @Override
    public void save(Brand brand) {
        brandMapper.save(brand);
    }

    @Override
    public void updateById(Brand brand) {
        brandMapper.updateById(brand);
    }

    @Override
    public void deletebyid(long id) {
        brandMapper.deletebyid(id);
    }
}

