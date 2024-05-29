/**
 * 1. @ClassName RoleServiceImpl
 * 2. @Description TODO
 * 3. @Author Young
 * 4. @Date 2023/10/26 14:26
 */
package com.farm.service.impl;

import com.farm.mapper.RoleMapper;
import com.farm.mapper.SysRoleUserMapper;
import com.farm.model.dto.system.AssginRoleDto;
import com.farm.model.dto.system.SysRoleDto;
import com.farm.model.entity.system.SysRole;
import com.farm.service.RoleService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class RoleServiceImpl implements RoleService {
@Autowired
private RoleMapper roleMapper;
    @Autowired
    private SysRoleUserMapper sysRoleUserMapper ;
    @Override
    public PageInfo<SysRole> findByPage(SysRoleDto sysRoleDto, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<SysRole> sysRoleList =roleMapper.findByPage(sysRoleDto);
        PageInfo<SysRole> sysRolePageInfo = new PageInfo<>(sysRoleList);
        return sysRolePageInfo;
    }

    @Override
    public void addrole(SysRole sysRole) {
        roleMapper.addrole(sysRole);
    }

    @Override
    public void updaterole(SysRole sysRole) {
        roleMapper.updaterole(sysRole);
    }

    @Override
    public void deletebyid(Integer roleId) {
        roleMapper.delebyid(roleId);
    }

    @Override
    public Map<String, Object> findAllRoles(Long userId) {
        //查询已分配角色
        List<Long> sysnowRoleList=roleMapper.findnowRoles(userId);
        //查询所有角色
        List<SysRole> sysRoleList = roleMapper.findAllRoles() ;
        Map<String , Object> resultMap = new HashMap<>() ;
        resultMap.put("allRolesList" , sysRoleList) ;
        resultMap.put("sysUserRoles",sysnowRoleList);
        return resultMap;
    }
    @Transactional
    @Override
    public void assignRole(AssginRoleDto assginRoleDto) {
        // 删除之前的所有的用户所对应的角色数据
        sysRoleUserMapper.deletebyid(assginRoleDto.getUserId()) ;
        // 分配新的角色数据
        List<Long> roleIdList = assginRoleDto.getRoleIdList();
        for (Long roleId : roleIdList) {
            sysRoleUserMapper.assignRole(assginRoleDto.getUserId(),roleId);
        }
    }
}

