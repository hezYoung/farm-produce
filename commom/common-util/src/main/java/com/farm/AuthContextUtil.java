/**
 * 1. @ClassName AuthContextUtil
 * 2. @Description TODO
 * 3. @Author Young
 * 4. @Date 2023/10/26 10:17
 */
package com.farm;

import com.farm.model.entity.system.SysUser;

public class AuthContextUtil {
    // 创建一个ThreadLocal对象
    private static final ThreadLocal<SysUser> threadLocal = new ThreadLocal<>() ;
   //存储方法
    public static void set (SysUser sysUser){
        threadLocal.set(sysUser);
    }
    //获取
    public static SysUser get(){
       return threadLocal.get();
    }
    //删除
    public static void remove(){
        threadLocal.remove();
    }
}

