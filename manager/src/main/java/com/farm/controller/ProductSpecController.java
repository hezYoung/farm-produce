/**
 * 1. @ClassName ProductSpecController
 * 2. @Description TODO
 * 3. @Author Young
 * 4. @Date 2023/11/9 14:07
 */
package com.farm.controller;

import com.farm.model.entity.product.ProductSpec;
import com.farm.model.vo.common.Result;
import com.farm.model.vo.common.ResultCodeEnum;
import com.farm.service.ProductSpecService;
import com.github.pagehelper.PageInfo;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/admin/product/productSpec")
public class ProductSpecController {
    @Resource
    private ProductSpecService productSpecService;

    //分页查询
    @GetMapping("/{page}/{limit}")
    public Result getpagelist(@PathVariable(value = "page") Integer page,
                              @PathVariable(value = "limit") Integer limit) {
        PageInfo<ProductSpec> pageInfo = productSpecService.getpagelist(page, limit);
        return Result.build(pageInfo, ResultCodeEnum.SUCCESS);
    }

    //添加
    @PostMapping("/save")
    public Result insertPs(@RequestBody ProductSpec productSpec) {
        productSpecService.insert(productSpec);
        return Result.build(null, ResultCodeEnum.SUCCESS);
    }

    //修改
    @PutMapping("/updatebyid")
    public Result updatebyid(@RequestBody ProductSpec productSpec) {
        productSpecService.updatebyid(productSpec);
        return Result.build(null, ResultCodeEnum.SUCCESS);
    }

    //逻辑删除
    @DeleteMapping("/deletebyid/{id}")
    public Result deletebyid(@PathVariable long id) {
        productSpecService.deletebyid(id);
        return Result.build(null, ResultCodeEnum.SUCCESS);
    }

    //加载商品规格
    @GetMapping("/findAll")
    public Result findall() {
      List<ProductSpec> productSpecs= productSpecService.findall();
        return Result.build(productSpecs, ResultCodeEnum.SUCCESS);
    }
}

