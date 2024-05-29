/**
 * 1. @ClassName LoginController
 * 2. @Description TODO
 * 3. @Author Young
 * 4. @Date 2023/10/25 10:44
 */
package com.farm.controller;

import com.farm.AuthContextUtil;
import com.farm.model.dto.system.LoginDto;
import com.farm.model.entity.system.SysUser;
import com.farm.model.vo.common.Result;
import com.farm.model.vo.common.ResultCodeEnum;
import com.farm.model.vo.system.LoginVo;
import com.farm.model.vo.system.SysMenuVo;
import com.farm.model.vo.system.ValidateCodeVo;
import com.farm.service.MenuService;
import com.farm.service.SysUserService;
import com.farm.service.ValidateCodeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "用户接口")
@RestController
        @RequestMapping(value = "/admin/system/index")
public class LoginController {

    @Autowired
    private SysUserService sysUserService ;
    @Autowired
    private MenuService menuService;
    @Autowired
    private ValidateCodeService validateCodeService;
    @Operation(summary = "验证码接口")
    @GetMapping(value = "/generateValidateCode")
    public Result<ValidateCodeVo> generateValidateCode() {
        ValidateCodeVo validateCodeVo = validateCodeService.generateValidateCode();
        return Result.build(validateCodeVo , ResultCodeEnum.SUCCESS) ;
    }
    @Operation(summary = "登录接口")
    @PostMapping(value = "/login")
    public Result<LoginVo> login(@RequestBody LoginDto loginDto) {
        LoginVo loginVo = sysUserService.login(loginDto) ;
        return Result.build(loginVo , ResultCodeEnum.SUCCESS) ;
    }
    @Operation(summary = "获取用户接口")
    @GetMapping(value = "/getUserInfo")
    public Result<SysUser> getUserInfo() {
        return Result.build(AuthContextUtil.get()  , ResultCodeEnum.SUCCESS) ;
    }
    @Operation(summary = "退出登录接口")
    @GetMapping(value = "/logout")
    public Result logout(@RequestHeader(value = "token") String token) {
        sysUserService.logout(token) ;
        return Result.build(null , ResultCodeEnum.SUCCESS) ;
    }

}
