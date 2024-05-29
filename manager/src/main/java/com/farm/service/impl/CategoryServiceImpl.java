/**
 * 1. @ClassName CategoryServiceImpl
 * 2. @Description TODO
 * 3. @Author Young
 * 4. @Date 2023/11/1 9:22
 */
package com.farm.service.impl;

import cn.hutool.http.server.HttpServerResponse;
import com.alibaba.excel.EasyExcel;
import com.farm.listener.ExcelListener;
import com.farm.mapper.CategoryMapper;
import com.farm.model.entity.product.Category;
import com.farm.model.vo.product.CategoryExcelVo;
import com.farm.service.CategoryService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    public void importDate(MultipartFile file) {

        try {
            ExcelListener excelListener = new ExcelListener(categoryMapper);
            EasyExcel.read(file.getInputStream(),CategoryExcelVo.class,excelListener)
                    .sheet().doRead();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void exportData(HttpServletResponse response) {
        try {
            // 设置响应结果类型
            response.setContentType("application/vnd.ms-excel");
            response.setCharacterEncoding("utf-8");
            // 这里URLEncoder.encode可以防止中文乱码 当然和easyexcel没有关系
            String fileName = null;
            fileName = URLEncoder.encode("分类数据", "UTF-8");
            response.setHeader("Content-disposition", "attachment;filename=" + fileName + ".xlsx");
            response.setHeader("Content-disposition", "attachment;filename=" + fileName + ".xlsx");
            //获取数据
            List<Category> categories=categoryMapper.getall();
            List<CategoryExcelVo> list = new ArrayList<>();
            for (Category category : categories) {
                CategoryExcelVo categoryExcelVo = new CategoryExcelVo();
                BeanUtils.copyProperties(category,categoryExcelVo, CategoryExcelVo.class);
                list.add(categoryExcelVo);
            }
            EasyExcel.write(response.getOutputStream(),CategoryExcelVo.class).sheet("分类数据")
                    .doWrite(categories);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }

    @Override
    public List<Category> getcaegary(long id) {
        //获取一级id
        List<Category> categories=categoryMapper.getbyid(id);
        //判断下面是否有子id，封装一个haschildren给前端组件的字段
        if (!CollectionUtils.isEmpty(categories)){
            categories.forEach(category -> {
                int count=categoryMapper.getChirden(category.getId());
                if (count > 0) {
                    category.setHasChildren(true);
                }else {
                    category.setHasChildren(false);
                }
            });
        }
        return categories;
    }
}

