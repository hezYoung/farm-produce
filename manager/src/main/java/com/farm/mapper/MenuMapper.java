/**
 * 1. @ClassName MenuMapper
 * 2. @Description TODO
 * 3. @Author Young
 * 4. @Date 2023/10/30 16:27
 */
package com.farm.mapper;

import com.farm.model.entity.system.SysMenu;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MenuMapper {

    List<SysMenu> selectAll();

    void save(SysMenu sysMenu);

    void updateid(SysMenu sysMenu);

    int countByParentId(Long id);
    List<SysMenu> getmenu(Long userId);
    void deleteById(Long id);

    SysMenu selectById(Long id) ;
}
