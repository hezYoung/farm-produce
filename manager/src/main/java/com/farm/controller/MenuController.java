/**
 * 1. @ClassName MenuController
 * 2. @Description TODO
 * 3. @Author Young
 * 4. @Date 2023/10/30 16:25
 */
package com.farm.controller;

import com.farm.model.entity.system.SysMenu;
import com.farm.model.vo.common.Result;
import com.farm.model.vo.common.ResultCodeEnum;
import com.farm.model.vo.system.SysMenuVo;
import com.farm.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value="/admin/system/sysMenu")
public class MenuController {
@Autowired
    private MenuService menuService;
    @GetMapping("/findNodes")
    public Result<List<SysMenu>> findNodes() {
        List<SysMenu> list = menuService.findNodes();
        return Result.build(list , ResultCodeEnum.SUCCESS) ;
    }

    /*添加*/
    @PostMapping("/save")
    public Result save(@RequestBody SysMenu sysMenu) {
        menuService.save(sysMenu);
        return Result.build(null , ResultCodeEnum.SUCCESS) ;
    }
    @PutMapping("/updateById")
    public Result updatebyid(@RequestBody SysMenu sysMenu){
        menuService.udatebyid(sysMenu);
        return Result.build(null,ResultCodeEnum.SUCCESS);
    }
    @DeleteMapping("/removeById/{id}")
    public Result removeById(@PathVariable Long id) {
        menuService.removeById(id);
        return Result.build(null , ResultCodeEnum.SUCCESS) ;
    }
    //分配动态菜单
    @GetMapping("/menus")
    public Result menus() {
        List<SysMenuVo> sysMenuVoList =  menuService.getmenu() ;
        return Result.build(sysMenuVoList , ResultCodeEnum.SUCCESS) ;
    }
}

