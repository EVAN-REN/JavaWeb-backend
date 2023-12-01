package com.my.filter;

import com.alibaba.fastjson.JSONObject;
import com.my.pojo.Result;
import com.my.utils.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
//@WebFilter(urlPatterns = "/*")
public class LoginCheckFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
        //1 获取请求url
        String url = req.getRequestURL().toString();
        log.info("request url:{}", url);

        //2 判断请求url中是否包含login，如果包含，说明是登陆操作，放行
        if(url.contains("login")){
            log.info("login, pass...");
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }

        //3 获取请求头中的令牌
        String jwt = req.getHeader("token");
        log.info(jwt);

        //4 判断令牌是否存在，如果不存在，返回错误结果（未登录）
        if(!StringUtils.hasLength(jwt)){
            log.info("token in request header is empty, return not login");
            Result error = Result.error("NOT_LOGIN");

            //没有restcontroller需要手动转换对象为json数据-------->阿里巴巴fastJSON
            String notLogin = JSONObject.toJSONString(error);
            resp.getWriter().write(notLogin);
            return;
        }

        //5 解析token，如果解析失败，返回错误结果（未登录）
        try {
            JwtUtils.testParseJwt(jwt);
        } catch (Exception e) {
            e.printStackTrace();
            log.info("fail to test parse jwt, renturn error message");
            Result error = Result.error("NOT_LOGIN");
            //没有restcontroller需要手动转换对象为json数据-------->阿里巴巴fastJSON
            String notLogin = JSONObject.toJSONString(error);
            resp.getWriter().write(notLogin);
            return;
        }

        //6 放行
        log.info("token valid, pass");
        filterChain.doFilter(servletRequest, servletResponse);
    }
}
