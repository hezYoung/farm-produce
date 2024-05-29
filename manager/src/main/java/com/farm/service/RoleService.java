/**
 * 1. @ClassName RoleService
 * 2. @Description TODO
 * 3. @Author Young
 * 4. @Date 2023/10/26 14:25
 */
package com.farm.service;

import com.farm.model.dto.system.AssginRoleDto;
import com.farm.model.dto.system.SysRoleDto;
import com.farm.model.entity.system.SysRole;
import com.github.pagehelper.PageInfo;

import java.util.Map;

public interface RoleService {
    PageInfo<SysRole> findByPage(SysRoleDto sysRoleDto, Integer pageNum, Integer pageSize);

    void addrole(SysRole sysRole);

    void updaterole(SysRole sysRole);

    void deletebyid(Integer roleId);

    Map<String, Object> findAllRoles(Long userId);

    void assignRole(AssginRoleDto assginRoleDto);
}
