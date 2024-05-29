/**
 * 1. @ClassName SysRoleUserMapper
 * 2. @Description TODO
 * 3. @Author Young
 * 4. @Date 2023/10/30 14:52
 */
package com.farm.mapper;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SysRoleUserMapper {
    void deletebyid(Long userId);


    void assignRole(Long userId, Long roleId);
}
