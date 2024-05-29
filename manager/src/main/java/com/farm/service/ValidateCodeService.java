/**
 * 1. @ClassName ValidateCodeService
 * 2. @Description TODO
 * 3. @Author Young
 * 4. @Date 2023/10/25 16:17
 */
package com.farm.service;

import com.farm.model.vo.system.ValidateCodeVo;

public interface ValidateCodeService {
    ValidateCodeVo generateValidateCode();
}
