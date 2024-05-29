/**
 * 1. @ClassName SysRoleMenuService
 * 2. @Description TODO
 * 3. @Author Young
 * 4. @Date 2023/10/31 10:37
 */
package com.farm.service;

import com.farm.model.dto.system.AssginMenuDto;

import java.util.Map;

public interface SysRoleMenuService {
    Map<String, Object> findSysRoleMenuByRoleId(Long roleId);

    void saveAssign(AssginMenuDto assginMenuDto);
}
