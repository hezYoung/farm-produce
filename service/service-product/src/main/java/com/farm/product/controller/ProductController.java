/**
 * 1. @ClassName ProductController
 * 2. @Description TODO
 * 3. @Author Young
 * 4. @Date 2023/12/7 11:05
 */
package com.farm.product.controller;

import com.farm.model.dto.product.SkuSaleDto;
import com.farm.model.entity.product.ProductSku;
import com.farm.model.vo.common.Result;
import com.farm.model.vo.common.ResultCodeEnum;
import com.farm.model.vo.h5.ProductItemVo;
import com.farm.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value="/api/product")
public class ProductController {
    @Autowired
    private ProductService productService;


    @GetMapping
    public Result<ProductSku> findByPage(Integer category3Id) {
        List<ProductSku> pageInfo = productService.findByPage(category3Id);
        return Result.build(pageInfo , ResultCodeEnum.SUCCESS) ;
    }
    @GetMapping("search")
    public Result<ProductSku> findByPage(String skuName) {
        List<ProductSku> list = productService.findBySkuName(skuName);
        return Result.build(list , ResultCodeEnum.SUCCESS) ;
    }

    /**
     * 商品详情
     * @param skuId
     * @return
     */
    @GetMapping("/item")
    public Result<ProductItemVo> item(Long skuId) {
        ProductItemVo productItemVo = productService.item(skuId);
        return Result.build(productItemVo , ResultCodeEnum.SUCCESS);
    }
    @GetMapping("getBySkuId/{skuId}")
    public ProductSku getBySkuId(@PathVariable Long skuId) {
        ProductSku productSku = productService.getBySkuId(skuId);
        return productSku;
    }
    @PostMapping("updateSkuSaleNum")
    public Boolean updateSkuSaleNum(@RequestBody List<SkuSaleDto> skuSaleDtoList) {
        return productService.updateSkuSaleNum(skuSaleDtoList);
    }
}

