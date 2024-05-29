/**
 * 1. @ClassName LoginAuthInterceptor
 * 2. @Description TODO
 * 3. @Author Young
 * 4. @Date 2023/10/26 10:24
 */
package com.farm.intercptor;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import com.farm.AuthContextUtil;
import com.farm.model.entity.system.SysUser;
import com.farm.model.vo.common.Result;
import com.farm.model.vo.common.ResultCodeEnum;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.concurrent.TimeUnit;

@Component
public class LoginAuthInterceptor implements HandlerInterceptor {
    @Autowired
    private RedisTemplate<String , String> redisTemplate ;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 获取请求方式
        String method = request.getMethod();
        if ("OPTIONS".equals(method)){
            return true;//如果是预检请求，就直接放行
        }
        //获取token
        String token = request.getHeader("token");
        if (StrUtil.isEmpty(token)){
            responseNoLoginInfo(response) ;
            return false;
        }
        // 如果token不为空，那么此时验证token的合法性
        String userinfo = redisTemplate.opsForValue().get("user:login" + token);
        if (StrUtil.isEmpty(userinfo)){
            responseNoLoginInfo(response);
            return false;
        }
        // 将用户数据存储到ThreadLocal中
        SysUser user = JSON.parseObject(userinfo, SysUser.class);
        AuthContextUtil.set(user);
        // 重置Redis中的用户数据的有效时间
redisTemplate.expire("user:login" + token,30, TimeUnit.MINUTES);

        return true;
    }
    //响应208状态码给前端
    private void responseNoLoginInfo(HttpServletResponse response) {
        Result<Object> result = Result.build(null, ResultCodeEnum.LOGIN_AUTH);
        PrintWriter writer = null;
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=utf-8");
        try {
            writer = response.getWriter();
            writer.print(JSON.toJSONString(result));
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (writer != null) writer.close();
        }
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
    AuthContextUtil.remove();
    }
}

