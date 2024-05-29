/**
 * 1. @ClassName SysRoleMenuMapper
 * 2. @Description TODO
 * 3. @Author Young
 * 4. @Date 2023/10/31 10:40
 */
package com.farm.mapper;

import com.farm.model.dto.system.AssginMenuDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SysRoleMenuMapper {
    List<Long> findNowmenus(Long roleId);

    void saveAssign(AssginMenuDto assginMenuDto);

    void deleteByRoleId(Long roleId);

    void updateSysRoleMenuIsHalf(Long menuId);
}
