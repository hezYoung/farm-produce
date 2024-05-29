/**
 * 1. @ClassName CategoryController
 * 2. @Description TODO
 * 3. @Author Young
 * 4. @Date 2023/11/1 9:22
 */
package com.farm.controller;

import cn.hutool.http.server.HttpServerResponse;
import com.farm.model.entity.product.Category;
import com.farm.model.vo.common.Result;
import com.farm.model.vo.common.ResultCodeEnum;
import com.farm.service.CategoryService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping(value = "/admin/product/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @GetMapping("/getall/{id}")
    public Result getall(@PathVariable long id) {
        List<Category> categories = categoryService.getcaegary(id);
        return Result.build(categories, ResultCodeEnum.SUCCESS);
    }

    //导出功能
    @GetMapping("/exportData")
    public void exportData(HttpServletResponse response) {
        categoryService.exportData(response);
    }

    //导入功能
    @PostMapping("/importDate")
    public Result importDate(MultipartFile file) {
        categoryService.importDate(file);
        return Result.build(null, ResultCodeEnum.SUCCESS);
    }
}

