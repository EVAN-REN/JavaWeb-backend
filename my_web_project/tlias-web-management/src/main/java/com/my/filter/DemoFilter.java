package com.my.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

//@WebFilter(urlPatterns = "/*") //javaweb组件
public class DemoFilter implements Filter {

    //init method，只调用1次
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("init method execute");
    }

    //when intercept request, call the function
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("Demo intercept a request");
        System.out.println("Demo before release pass");
        filterChain.doFilter(servletRequest, servletResponse);
        System.out.println("Demo after release pass");
    }

    //destroy method，只调用1次
    @Override
    public void destroy() {
        System.out.println("destroy method execute");
    }
}
