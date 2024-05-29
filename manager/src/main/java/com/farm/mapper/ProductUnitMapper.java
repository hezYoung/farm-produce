/**
 * 1. @ClassName ProductUnitMapper
 * 2. @Description TODO
 * 3. @Author Young
 * 4. @Date 2023/11/10 10:55
 */
package com.farm.mapper;

import com.farm.model.entity.base.ProductUnit;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProductUnitMapper {
    List<ProductUnit> findall();
}
