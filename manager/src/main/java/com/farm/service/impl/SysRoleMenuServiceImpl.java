/**
 * 1. @ClassName SysRoleMenuServiceImpl
 * 2. @Description TODO
 * 3. @Author Young
 * 4. @Date 2023/10/31 10:37
 */
package com.farm.service.impl;

import com.farm.mapper.SysRoleMenuMapper;
import com.farm.model.dto.system.AssginMenuDto;
import com.farm.model.entity.system.SysMenu;
import com.farm.service.MenuService;
import com.farm.service.SysRoleMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SysRoleMenuServiceImpl implements SysRoleMenuService {
    @Autowired
    private SysRoleMenuMapper sysRoleMenuMapper;
    @Autowired
    private MenuService sysMenuService;
    @Override
    public Map<String, Object> findSysRoleMenuByRoleId(Long roleId) {
        List<SysMenu> menuServiceNodes = sysMenuService.findNodes();
        List<Long> roleMenuIds  =sysRoleMenuMapper.findNowmenus(roleId);
        HashMap<String, Object> map = new HashMap<>();
        map.put("sysMenuList",menuServiceNodes);
        map.put("roleMenuIds",roleMenuIds);

        return map;
    }

    @Override
    public void saveAssign(AssginMenuDto assginMenuDto) {
        // 根据角色的id删除其所对应的菜单数据
        sysRoleMenuMapper.deleteByRoleId(assginMenuDto.getRoleId());
        //添加操作
        List<Map<String, Number>> menuIdList = assginMenuDto.getMenuIdList();
        if (menuIdList != null && menuIdList.size()>0){
            sysRoleMenuMapper.saveAssign(assginMenuDto);
        }

    }
}

