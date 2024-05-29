/**
 * 1. @ClassName RoleController
 * 2. @Description TODO
 * 3. @Author Young
 * 4. @Date 2023/10/26 14:24
 */
package com.farm.controller;

import com.farm.model.dto.system.AssginRoleDto;
import com.farm.model.dto.system.SysRoleDto;
import com.farm.model.entity.system.SysRole;
import com.farm.model.vo.common.Result;
import com.farm.model.vo.common.ResultCodeEnum;
import com.farm.service.RoleService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping(value = "/admin/system/sysRole")
public class RoleController {
    @Autowired
    private RoleService sysRoleService ;

    @PostMapping("/findByPage/{pageNum}/{pageSize}")
    public Result<PageInfo<SysRole>> findByPage(@RequestBody SysRoleDto sysRoleDto ,
                                                @PathVariable(value = "pageNum") Integer pageNum ,
                                                @PathVariable(value = "pageSize") Integer pageSize) {
        PageInfo<SysRole> pageInfo = sysRoleService.findByPage(sysRoleDto , pageNum , pageSize) ;
        return Result.build(pageInfo , ResultCodeEnum.SUCCESS) ;
    }
    /*添加角色*/
    @PostMapping("/addrole")
    public Result addrole(@RequestBody SysRole sysRole){
        sysRoleService.addrole(sysRole);
        return Result.build(null,ResultCodeEnum.SUCCESS);
    }
    /*修改角色*/
    @PutMapping("/updaterole")
    public Result updaterole(@RequestBody SysRole sysRole){
        sysRoleService.updaterole(sysRole);
        return Result.build(null,ResultCodeEnum.SUCCESS);
    }
    /*删除角色*/
    @DeleteMapping("/deleteById/{roleId}")
    public Result deleteById(@PathVariable Integer roleId){
        sysRoleService.deletebyid(roleId);
        return Result.build(null,ResultCodeEnum.SUCCESS);
    }
    //查询角色列表
    @GetMapping(value = "/findAllRoles/{userId}")
    public Result<Map<String , Object>> findAllRoles(@PathVariable(value = "userId") Long userId) {
        Map<String, Object> resultMap = sysRoleService.findAllRoles(userId);
        return Result.build(resultMap , ResultCodeEnum.SUCCESS)  ;
    }
    //保存角色
    @PostMapping("/assignRole")
    public Result assignRole(@RequestBody AssginRoleDto assginRoleDto){
        sysRoleService.assignRole(assginRoleDto);
        return Result.build(null , ResultCodeEnum.SUCCESS) ;
    }
}

