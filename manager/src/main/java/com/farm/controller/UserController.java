/**
 * 1. @ClassName UserController
 * 2. @Description TODO
 * 3. @Author Young
 * 4. @Date 2023/10/27 9:52
 */
package com.farm.controller;

import com.farm.model.dto.system.SysUserDto;
import com.farm.model.entity.system.SysUser;
import com.farm.model.vo.common.Result;
import com.farm.model.vo.common.ResultCodeEnum;
import com.farm.service.SysUserService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/system/sysUser")
public class UserController {
@Autowired
    private SysUserService sysUserService;
    @GetMapping(value = "/findByPage/{pageNum}/{pageSize}")
    public Result<PageInfo<SysUser>> findByPage(SysUserDto sysUserDto ,
                                                @PathVariable(value = "pageNum") Integer pageNum ,
                                                @PathVariable(value = "pageSize") Integer pageSize) {
        PageInfo<SysUser> pageInfo = sysUserService.findByPage(sysUserDto , pageNum , pageSize) ;
        return Result.build(pageInfo , ResultCodeEnum.SUCCESS) ;
    }
//    添加用户
    @PostMapping("/adduser")
    public Result adduser(@RequestBody SysUser sysUser){
        sysUserService.adduser(sysUser);
        return Result.build(null,ResultCodeEnum.SUCCESS);
    }
    //修改用户
    @PutMapping("/updateuser")
    public Result updateuser(@RequestBody SysUser sysUser){
        sysUserService.updateuser(sysUser);
        return Result.build(null,ResultCodeEnum.SUCCESS);
    }
    //删除用户
    @DeleteMapping("/deleteuser/{userId}")
    public Result deleteuser(@PathVariable Integer userId){
        sysUserService.deleteuser(userId);
        return Result.build(null,ResultCodeEnum.SUCCESS);
    }

}

