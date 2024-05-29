/**
 * 1. @ClassName IndexController
 * 2. @Description TODO
 * 3. @Author Young
 * 4. @Date 2023/11/29 9:23
 */
package com.farm.product.controller;

import com.farm.model.entity.product.Category;
import com.farm.model.entity.product.ProductSku;
import com.farm.model.vo.common.Result;
import com.farm.model.vo.common.ResultCodeEnum;
import com.farm.model.vo.h5.IndexVo;
import com.farm.product.service.CategoryService;
import com.farm.product.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping(value="/api/product/index")
public class IndexController {
    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ProductService productService;

    @Operation(summary = "获取首页数据")
    @GetMapping
    public Result<IndexVo> findData(){
        CompletableFuture<List<Category>> categoryFuture = CompletableFuture.supplyAsync(() -> categoryService.findOneCategory());
        CompletableFuture<List<ProductSku>> productSkuFuture = CompletableFuture.supplyAsync(() -> productService.findProductSkuBySale());

        return CompletableFuture.allOf(categoryFuture, productSkuFuture)
                .thenApplyAsync(ignored -> {
                    List<Category> categoryList = categoryFuture.join();
                    List<ProductSku> productSkuList = productSkuFuture.join();

                    IndexVo indexVo = new IndexVo();
                    indexVo.setCategoryList(categoryList);
                    indexVo.setProductSkuList(productSkuList);

                    return Result.build(indexVo, ResultCodeEnum.SUCCESS);
                })
                .join();
    }
}

