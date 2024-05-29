/**
 * 1. @ClassName BrandMapper
 * 2. @Description TODO
 * 3. @Author Young
 * 4. @Date 2023/11/2 17:19
 */
package com.farm.mapper;

import com.farm.model.entity.product.Brand;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BrandMapper {
    List<Brand> findpage();

    void save(Brand brand);

    void updateById(Brand brand);

    void deletebyid(long id);

    List<Brand> findall();
}
