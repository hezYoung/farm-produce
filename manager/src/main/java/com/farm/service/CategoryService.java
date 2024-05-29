/**
 * 1. @ClassName CategoryService
 * 2. @Description TODO
 * 3. @Author Young
 * 4. @Date 2023/11/1 9:22
 */
package com.farm.service;

import cn.hutool.http.server.HttpServerResponse;
import com.farm.model.entity.product.Category;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface CategoryService {
    List<Category> getcaegary(long id);

    void exportData(HttpServletResponse response);

    void importDate(MultipartFile file);
}
