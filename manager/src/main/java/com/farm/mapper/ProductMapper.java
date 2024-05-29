/**
 * 1. @ClassName ProductMapper
 * 2. @Description TODO
 * 3. @Author Young
 * 4. @Date 2023/11/10 9:48
 */
package com.farm.mapper;

import com.farm.model.dto.product.ProductDto;
import com.farm.model.entity.base.ProductUnit;
import com.farm.model.entity.product.Product;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProductMapper {
    List<Product> getpagelist(ProductDto productDto);
    public abstract  void updateById(Product product);

    void insert(Product product);
    public abstract void save(Product product);
    public abstract Product selectById(Long id);
    void updatebyid(Product product);

    void deleteById(Long id);

}
