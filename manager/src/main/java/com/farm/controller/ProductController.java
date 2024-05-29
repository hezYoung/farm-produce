/**
 * 1. @ClassName ProductController
 * 2. @Description TODO
 * 3. @Author Young
 * 4. @Date 2023/11/10 9:44
 */
package com.farm.controller;

import com.farm.model.dto.product.ProductDto;
import com.farm.model.entity.product.Product;
import com.farm.model.vo.common.Result;
import com.farm.model.vo.common.ResultCodeEnum;
import com.farm.service.ProductService;
import com.github.pagehelper.PageInfo;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value="/admin/product/product")
public class ProductController {
    @Resource
    private ProductService productService;
//分页查询数据
    @GetMapping(value = "/{page}/{limit}")
    public Result getpagelist(@PathVariable Integer page, @PathVariable Integer limit, ProductDto productDto) {
        PageInfo<Product> pageInfo=productService.getpagelist(page, limit, productDto);
        return Result.build(pageInfo, ResultCodeEnum.SUCCESS);

    }

    //保存
    @PostMapping("/save")
    public Result insertproduct(@RequestBody Product product) {
        productService.insertproduct(product);
        return Result.build(null, ResultCodeEnum.SUCCESS);
    }

    //回显商品详情
    @GetMapping("/getById/{id}")
    public Result<Product> getById(@PathVariable Long id) {
        Product product = productService.getById(id);
        return Result.build(product , ResultCodeEnum.SUCCESS) ;
    }
    //修改
    @PutMapping("/updateById")
    public Result updateById(@Parameter(name = "product", description = "请求参数实体类", required = true) @RequestBody Product product) {
        productService.updateById(product);
        return Result.build(null , ResultCodeEnum.SUCCESS) ;
    }

    //删除
    @DeleteMapping("/deleteById/{id}")
    public Result deleteById( @PathVariable Long id) {
        productService.deleteById(id);
        return Result.build(null , ResultCodeEnum.SUCCESS) ;
    }
    //商品审核
    @GetMapping("/updateAuditStatus/{id}/{auditStatus}")
    public Result updateAuditStatus(@PathVariable Long id, @PathVariable Integer auditStatus) {
        productService.updateAuditStatus(id, auditStatus);
        return Result.build(null , ResultCodeEnum.SUCCESS) ;
    }
    @GetMapping("/updateStatus/{id}/{status}")
    public Result updateStatus(@PathVariable Long id, @PathVariable Integer status) {
        productService.updateStatus(id, status);
        return Result.build(null , ResultCodeEnum.SUCCESS) ;
    }
}

