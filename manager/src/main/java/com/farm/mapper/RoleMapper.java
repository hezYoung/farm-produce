/**
 * 1. @ClassName RoleMapper
 * 2. @Description TODO
 * 3. @Author Young
 * 4. @Date 2023/10/26 14:26
 */
package com.farm.mapper;

import com.farm.model.dto.system.SysRoleDto;
import com.farm.model.entity.system.SysRole;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface RoleMapper {
    public abstract List<SysRole> findByPage(SysRoleDto sysRoleDto);

    void addrole(SysRole sysRole);

    void updaterole(SysRole sysRole);

    void delebyid(Integer roleId);

    List<SysRole> findAllRoles();

    List<Long> findnowRoles(Long userId);
}

