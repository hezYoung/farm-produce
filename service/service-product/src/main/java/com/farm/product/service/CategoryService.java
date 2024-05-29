/**
 * 1. @ClassName CategoryService
 * 2. @Description TODO
 * 3. @Author Young
 * 4. @Date 2023/11/29 9:24
 */
package com.farm.product.service;

import com.farm.model.entity.product.Category;

import java.util.List;

public interface CategoryService {
    /**
     * 获取首页分类
     * @return
     */
    List<Category> findOneCategory();

    /**
     * 获取商品分类
     * @return
     */
    List<Category> findCategoryTree();
}
