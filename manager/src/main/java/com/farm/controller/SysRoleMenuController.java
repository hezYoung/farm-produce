/**
 * 1. @ClassName SysRoleMenuController
 * 2. @Description TODO
 * 3. @Author Young
 * 4. @Date 2023/10/31 10:36
 */
package com.farm.controller;

import com.farm.model.dto.system.AssginMenuDto;
import com.farm.model.vo.common.Result;
import com.farm.model.vo.common.ResultCodeEnum;
import com.farm.service.SysRoleMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping(value = "/admin/system/sysRoleMenu")
public class SysRoleMenuController {

    @Autowired
    private SysRoleMenuService sysRoleMenuService;

    @GetMapping(value = "/findSysRoleMenuByRoleId/{roleId}")
    public Result<Map<String, Object>> findSysRoleMenuByRoleId(@PathVariable(value = "roleId") Long roleId) {
        Map<String, Object> sysRoleMenuList = sysRoleMenuService.findSysRoleMenuByRoleId(roleId);
        return Result.build(sysRoleMenuList, ResultCodeEnum.SUCCESS);
    }

    @PostMapping("/doAssign")
    public Result saveAssign(@RequestBody AssginMenuDto assginMenuDto) {
        sysRoleMenuService.saveAssign(assginMenuDto);
        return Result.build(null,ResultCodeEnum.SUCCESS);
    }
}
