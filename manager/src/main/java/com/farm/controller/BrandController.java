/**
 * 1. @ClassName BrandController
 * 2. @Description TODO
 * 3. @Author Young
 * 4. @Date 2023/11/2 17:16
 */
package com.farm.controller;

import com.farm.model.entity.product.Brand;
import com.farm.model.vo.common.Result;
import com.farm.model.vo.common.ResultCodeEnum;
import com.farm.service.BrandService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/admin/product/brand")
public class BrandController {
    @Autowired
    private BrandService brandService;

    @GetMapping("/getbrand/{page}/{limit}")
    public Result getbrand(@PathVariable Integer page, @PathVariable Integer limit) {
        PageInfo<Brand> pageInfo = brandService.findByPage(page, limit);
        return Result.build(pageInfo, ResultCodeEnum.SUCCESS);

    }

    //添加
    @PostMapping("/save")
    public Result save(@RequestBody Brand brand) {
        brandService.save(brand);
        return Result.build(null, ResultCodeEnum.SUCCESS);
    }

    @PutMapping("/updateById")
    public Result updateById(@RequestBody Brand brand) {
        brandService.updateById(brand);
        return Result.build(null, ResultCodeEnum.SUCCESS);
    }

    @DeleteMapping("/deleteById/{id}")
    public Result deletebyid(@PathVariable long id) {
        brandService.deletebyid(id);
        return Result.build(null, ResultCodeEnum.SUCCESS);
    }
//查询品牌列表
    @GetMapping("/findAll")
    public Result findall() {
        List<Brand> brandList = brandService.findall();
        return Result.build(brandList, ResultCodeEnum.SUCCESS);
    }
}

