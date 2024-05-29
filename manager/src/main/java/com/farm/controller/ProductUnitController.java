/**
 * 1. @ClassName ProductUnitController
 * 2. @Description TODO
 * 3. @Author Young
 * 4. @Date 2023/11/10 10:53
 */
package com.farm.controller;

import com.farm.model.entity.base.ProductUnit;
import com.farm.model.vo.common.Result;
import com.farm.model.vo.common.ResultCodeEnum;
import com.farm.service.ProductUnitService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/admin/product/productUnit")
public class ProductUnitController {
    @Resource
    ProductUnitService productUnitService;
//加载商品单元数据
    @GetMapping("findAll")
    public Result<List<ProductUnit>> findall() {
       List<ProductUnit> productUnits= productUnitService.findall();
        return Result.build(productUnits, ResultCodeEnum.SUCCESS);
    }


}

