/**
 * 1. @ClassName SysUserServiceImpl
 * 2. @Description TODO
 * 3. @Author Young
 * 4. @Date 2023/10/25 10:48
 */
package com.farm.service.impl;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import com.farm.AuthContextUtil;
import com.farm.exception.ZdyException;
import com.farm.mapper.SysUserMapper;
import com.farm.model.dto.system.LoginDto;
import com.farm.model.dto.system.SysUserDto;
import com.farm.model.entity.system.SysMenu;
import com.farm.model.entity.system.SysUser;
import com.farm.model.vo.common.ResultCodeEnum;
import com.farm.model.vo.system.LoginVo;
import com.farm.model.vo.system.SysMenuVo;
import com.farm.service.SysUserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.DigestUtils;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Service
public class SysUserServiceImpl implements SysUserService {
    @Autowired
    private SysUserMapper sysUserMapper;
    @Autowired
    private RedisTemplate<String , String> redisTemplate ;
    @Override
    public LoginVo login(LoginDto loginDto) {

        // 校验验证码是否正确
        String captcha = loginDto.getCaptcha();     // 用户输入的验证码
        String codeKey = loginDto.getCodeKey();     // redis中验证码的数据key

        // 从Redis中获取验证码
        String redisCode = redisTemplate.opsForValue().get("user:login:validatecode:" + codeKey);
        if(StrUtil.isEmpty(redisCode) || !StrUtil.equalsIgnoreCase(redisCode , captcha)) {
            throw new ZdyException(ResultCodeEnum.VALIDATECODE_ERROR) ;
        }

        // 验证通过删除redis中的验证码
        redisTemplate.delete("user:login:validatecode:" + codeKey) ;

        //查询用户名
        SysUser sysUser = sysUserMapper.selectByUserName(loginDto.getUserName());
        if (sysUser == null)
        {
            throw new ZdyException(ResultCodeEnum.DATA_ERROR);
        }
        //获取数据库密码
        String db_password = sysUser.getPassword();
        //获取前台密码
        String input_password = loginDto.getPassword();
        //加密
        String md5InputPassword = DigestUtils.md5DigestAsHex(input_password.getBytes());
        //验证
        if(!db_password.equals(md5InputPassword)){
            throw new ZdyException(ResultCodeEnum.LOGIN_ERROR);
        }
        // 生成令牌，保存数据到Redis中
        String token= UUID.randomUUID().toString().replace("-","");
        redisTemplate.opsForValue().set("user:login" + token , JSON.toJSONString(sysUser), 1 , TimeUnit.DAYS);

        // 构建响应结果对象
        LoginVo loginVo = new LoginVo() ;
        loginVo.setToken(token);
        loginVo.setRefresh_token("");

        // 返回
        return loginVo;
    }

    @Override
    public SysUser getUserInfo(String token) {
        String userJson = redisTemplate.opsForValue().get("user:login" + token);
        return JSON.parseObject(userJson , SysUser.class) ;
    }

    @Override
    public void logout(String token) {
        redisTemplate.delete("user:login" + token) ;
    }

    @Override
    public PageInfo<SysUser> findByPage(SysUserDto sysUserDto, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<SysUser> sysUserList= sysUserMapper.findbyPage(sysUserDto);
        PageInfo<SysUser> sysUserPageInfo = new PageInfo<>(sysUserList);
        return sysUserPageInfo;

    }

    @Override
    public void adduser(SysUser sysUser) {
        //检查用户是否存在
        SysUser dbSysUser =  sysUserMapper.findbyname(sysUser.getUserName());
        //判断
        if (dbSysUser != null){
            throw new ZdyException(ResultCodeEnum.USER_NAME_IS_EXISTS);
        }
        //密码加密
        String password = sysUser.getPassword();
        String digestPassword = DigestUtils.md5DigestAsHex(password.getBytes());
        sysUser.setPassword(digestPassword);
        sysUser.setStatus(1);
        sysUserMapper.saveSysUser(sysUser) ;
    }

    @Override
    public void updateuser(SysUser sysUser) {
        sysUserMapper.updateuser(sysUser);

    }

    @Override
    public void deleteuser(Integer userId) {
        sysUserMapper.deleteuserById(userId);
    }



}

