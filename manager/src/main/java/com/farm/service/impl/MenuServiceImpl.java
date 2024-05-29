/**
 * 1. @ClassName MenuServiceImpl
 * 2. @Description TODO
 * 3. @Author Young
 * 4. @Date 2023/10/30 16:26
 */
package com.farm.service.impl;

import com.farm.AuthContextUtil;
import com.farm.exception.ZdyException;
import com.farm.mapper.MenuMapper;
import com.farm.mapper.SysRoleMenuMapper;
import com.farm.model.entity.system.SysMenu;
import com.farm.model.entity.system.SysUser;
import com.farm.model.vo.common.ResultCodeEnum;
import com.farm.model.vo.system.SysMenuVo;
import com.farm.service.MenuService;
import com.github.xiaoymin.knife4j.core.util.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Service
public class MenuServiceImpl implements MenuService {
    @Autowired
    private MenuMapper menuMapper;
@Autowired
private SysRoleMenuMapper sysRoleMenuMapper;
    @Override
    public List<SysMenu> findNodes() {
        List<SysMenu> sysMenuList = menuMapper.selectAll() ;
        if (CollectionUtils.isEmpty(sysMenuList)) return null;
        List<SysMenu> treeList = buildTree(sysMenuList); //构建树形数据
        return treeList;

    }

    @Override
    public void save(SysMenu sysMenu) {
        menuMapper.save(sysMenu);
        // 新添加一个菜单，那么此时就需要将该菜单所对应的父级菜单设置为半开
        updateSysRoleMenuIsHalf(sysMenu) ;
    }

    private void updateSysRoleMenuIsHalf(SysMenu sysMenu) {
        // 查询是否存在父节点
        SysMenu parentMenu = menuMapper.selectById(sysMenu.getParentId());

        if(parentMenu != null) {

            // 将该id的菜单设置为半开
            sysRoleMenuMapper.updateSysRoleMenuIsHalf(parentMenu.getId()) ;

            // 递归调用
            updateSysRoleMenuIsHalf(parentMenu) ;

        }
    }

    @Override
    public void udatebyid(SysMenu sysMenu) {
        menuMapper.updateid(sysMenu);
    }

    @Override
    public void removeById(Long id) {
        int count = menuMapper.countByParentId(id);  // 先查询是否存在子菜单，如果存在不允许进行删除
        if (count > 0) {
            throw new ZdyException(ResultCodeEnum.NODE_ERROR);
        }
        menuMapper.deleteById(id);		// 不存在子菜单直接删除
    }

    @Override
    public List<SysMenuVo> getmenu() {
        SysUser sysUser = AuthContextUtil.get();
        Long userId = sysUser.getId();          // 获取当前登录用户的id

        List<SysMenu> sysMenuList = menuMapper.getmenu(userId) ;

        //构建树形数据
        List<SysMenu> sysMenuTreeList = buildTree(sysMenuList);
        return this.buildMenus(sysMenuTreeList);
    }
    // 将List<SysMenu>对象转换成List<SysMenuVo>对象
    private List<SysMenuVo> buildMenus(List<SysMenu> menus) {

        List<SysMenuVo> sysMenuVoList = new LinkedList<SysMenuVo>();
        for (SysMenu sysMenu : menus) {
            SysMenuVo sysMenuVo = new SysMenuVo();
            sysMenuVo.setTitle(sysMenu.getTitle());
            sysMenuVo.setName(sysMenu.getComponent());
            List<SysMenu> children = sysMenu.getChildren();
            if (!CollectionUtils.isEmpty(children)) {
                sysMenuVo.setChildren(buildMenus(children));
            }
            sysMenuVoList.add(sysMenuVo);
        }
        return sysMenuVoList;
    }
    /**
     * 使用递归方法建菜单
     * @param sysMenuList
     * @return
     */
    private List<SysMenu> buildTree(List<SysMenu> sysMenuList) {
        List<SysMenu> tree = new ArrayList<>();
        for (SysMenu sysMenu : sysMenuList) {
            if (sysMenu.getParentId().longValue() == 0){
        tree.add(findChildren(sysMenu,sysMenuList));
            }
        }
        return tree;
    }

   
    /**
     * 递归查找子节点
     * @param sysMenuList
     * @return
     */
    private SysMenu findChildren(SysMenu sysMenu, List<SysMenu> sysMenuList) {
        sysMenu.setChildren(new ArrayList<SysMenu>());
        for (SysMenu menu : sysMenuList) {
            if (sysMenu.getId().longValue() == menu.getParentId().longValue() ){
                sysMenu.getChildren().add(findChildren(menu,sysMenuList));
            }
        }
        return sysMenu;
    }
//    private SysMenu findChildren(SysMenu menu, List<SysMenu> sysMenuList) {
//        menu.setChildren(new ArrayList<SysMenu>());
//        for (SysMenu treenode : sysMenuList) {
//            if (menu.getId() == treenode.getParentId()) {
//                menu.getChildren().add(findChildren(menu,sysMenuList));
//            }
//        }
//        return menu;
//    }
}

