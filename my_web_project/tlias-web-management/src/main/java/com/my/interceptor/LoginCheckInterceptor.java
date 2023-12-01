package com.my.interceptor;

import com.alibaba.fastjson.JSONObject;
import com.my.pojo.Result;
import com.my.utils.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@Component
public class LoginCheckInterceptor implements HandlerInterceptor {
    //目标资源运行前运行，返回true：放行，返回false，不放行
    @Override
    public boolean preHandle(HttpServletRequest req, HttpServletResponse resp, Object handler) throws Exception {
        System.out.println("preHandle...");

        //1 获取请求url
        String url = req.getRequestURL().toString();
        log.info("request url:{}", url);

        //2 判断请求url中是否包含login，如果包含，说明是登陆操作，放行
        if(url.contains("login")){
            log.info("login, pass...");
            return true;
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
            return false;
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
            return false;
        }

        //6 放行
        log.info("token valid, pass");
        return true;
    }

    //目标资源方法运行后运行
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("postHandle...");
    }

    //视图渲染完毕后运行，最后运行
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println("afterCompletion");
    }
}
