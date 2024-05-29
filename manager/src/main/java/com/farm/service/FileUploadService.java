/**
 * 1. @ClassName FileUploadService
 * 2. @Description TODO
 * 3. @Author Young
 * 4. @Date 2023/10/30 10:16
 */
package com.farm.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


public interface FileUploadService {
    String uoloadfile(MultipartFile file);
}
