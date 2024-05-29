/**
 * 1. @ClassName MenuService
 * 2. @Description TODO
 * 3. @Author Young
 * 4. @Date 2023/10/30 16:26
 */
package com.farm.service;

import com.farm.model.entity.system.SysMenu;
import com.farm.model.vo.system.SysMenuVo;
import org.springframework.stereotype.Service;

import java.util.List;


public interface MenuService {
    List<SysMenu> findNodes();

    void save(SysMenu sysMenu);

    void udatebyid(SysMenu sysMenu);

    void removeById(Long id);
    List<SysMenuVo> getmenu();
}
