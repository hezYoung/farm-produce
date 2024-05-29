/**
 * 1. @ClassName CategoryBrandController
 * 2. @Description TODO
 * 3. @Author Young
 * 4. @Date 2023/11/7 11:16
 */
package com.farm.controller;

import com.farm.model.dto.product.CategoryBrandDto;
import com.farm.model.entity.product.Brand;
import com.farm.model.entity.product.CategoryBrand;
import com.farm.model.vo.common.Result;
import com.farm.model.vo.common.ResultCodeEnum;
import com.farm.service.CategoryBrandService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/admin/product/categoryBrand")
public class CategoryBrandController {
    @Autowired
    private CategoryBrandService categoryBrandService;

    @GetMapping("/{page}/{limit}")
    public Result<PageInfo<CategoryBrand>> getpage(@PathVariable(value = "page") Integer page,
                                                   @PathVariable(value = "limit") Integer limit,
                                                   CategoryBrandDto CategoryBrandDto) {
        PageInfo<CategoryBrand> pageInfo = categoryBrandService.getpage(page, limit, CategoryBrandDto);
        return Result.build(pageInfo, ResultCodeEnum.SUCCESS);
    }

    //添加
    @PostMapping("/save")
    public Result insertlist(@RequestBody CategoryBrand categoryBrandDto) {
       categoryBrandService.insertlist(categoryBrandDto);
       return Result.build(null,ResultCodeEnum.SUCCESS);
    }

    //修改
    @PutMapping("updateById")
    public Result updateById(@RequestBody CategoryBrand categoryBrand) {
        categoryBrandService.updatebyid(categoryBrand);
        return Result.build(null , ResultCodeEnum.SUCCESS) ;
    }

    //删除
    @DeleteMapping("/deleteById/{id}")
    public Result deletebyid(@PathVariable Long id) {
        categoryBrandService.deletebyid(id);
        return Result.build(null, ResultCodeEnum.SUCCESS);
    }
    //加载品牌数据到新增框
    @GetMapping("/findBrandByCategoryId/{categoryId}")
    public Result findBrandByCategoryId(@PathVariable Long categoryId) {
        List<Brand> brandList =   categoryBrandService.findBrandByCategoryId(categoryId);
        return Result.build(brandList , ResultCodeEnum.SUCCESS) ;
    }
}

