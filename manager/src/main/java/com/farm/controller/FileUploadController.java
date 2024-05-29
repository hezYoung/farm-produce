/**
 * 1. @ClassName FileUploadController
 * 2. @Description TODO
 * 3. @Author Young
 * 4. @Date 2023/10/30 10:14
 */
package com.farm.controller;

import com.farm.model.vo.common.Result;
import com.farm.model.vo.common.ResultCodeEnum;
import com.farm.service.FileUploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/admin/system")
public class FileUploadController {
    @Autowired
    private FileUploadService fileUploadService;
    @PostMapping("/fileUpload")
    public Result fileupload(MultipartFile file){
       String url= fileUploadService.uoloadfile(file);
        return Result.build(url, ResultCodeEnum.SUCCESS);
    }
}

