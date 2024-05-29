/**
 * 1. @ClassName SysUserMapper
 * 2. @Description TODO
 * 3. @Author Young
 * 4. @Date 2023/10/25 10:48
 */
package com.farm.mapper;

import com.farm.model.dto.system.SysUserDto;
import com.farm.model.entity.system.SysMenu;
import com.farm.model.entity.system.SysUser;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SysUserMapper {
    /**
     * 根据用户名查询用户数据
     * @param userName
     * @return
     */
    public abstract SysUser selectByUserName(String userName) ;

    List<SysUser> findbyPage(SysUserDto sysUserDto);

    SysUser findbyname(String userName);

    void saveSysUser(SysUser sysUser);

    void updateuser(SysUser sysUser);

    void deleteuserById(Integer userId);


}
