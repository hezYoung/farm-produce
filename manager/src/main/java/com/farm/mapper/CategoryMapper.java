/**
 * 1. @ClassName CategoryMapper
 * 2. @Description TODO
 * 3. @Author Young
 * 4. @Date 2023/11/1 9:23
 */
package com.farm.mapper;

import com.farm.model.entity.product.Category;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CategoryMapper {
    List<Category> getbyid(long id);

    int getChirden(Long id);

    List<Category> getall();

    public abstract void batchInsert(List<Category> categoryList);
}
