/**
 * 1. @ClassName CategoryMapper
 * 2. @Description TODO
 * 3. @Author Young
 * 4. @Date 2023/11/29 9:29
 */
package com.farm.product.mapper;

import com.farm.model.entity.product.Category;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CategoryMapper {
    List<Category> findOneCategory();

    /**
     * 获取所有一级分类
     * @return
     */
    List<Category> findAll();
}
