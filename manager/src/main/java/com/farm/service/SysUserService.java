/**
 * 1. @ClassName SysUserService
 * 2. @Description TODO
 * 3. @Author Young
 * 4. @Date 2023/10/25 10:47
 */
package com.farm.service;

import com.farm.model.dto.system.LoginDto;
import com.farm.model.dto.system.SysUserDto;
import com.farm.model.entity.system.SysUser;
import com.farm.model.vo.system.LoginVo;
import com.farm.model.vo.system.SysMenuVo;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface SysUserService {
    LoginVo login(LoginDto loginDto);

    SysUser getUserInfo(String token);

    void logout(String token);

    PageInfo<SysUser> findByPage(SysUserDto sysUserDto, Integer pageNum, Integer pageSize);

    void adduser(SysUser sysUser);

    void updateuser(SysUser sysUser);

    void deleteuser(Integer userId);


}

