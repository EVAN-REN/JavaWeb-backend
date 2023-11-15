package com.my.controller;

import com.my.pojo.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Slf4j
@RestController
public class SessionController {

    //config cookie
    @GetMapping("/c1")
    public Result Cookie1(HttpServletResponse response){
        //config cookie and response
        response.addCookie(new Cookie("login_username","my"));
        return Result.success();
    }

    //get cookie
    @GetMapping("/c2")
    public Result Cookie2(HttpServletRequest request){
        //get all the cookies
        Cookie[] cookies = request.getCookies();
        for(Cookie cookie : cookies){
            //print cookie which name is "login_username"
            if(cookie.getName().equals("login_username")){
                System.out.println("login_username" + cookie.getValue());
            }
        }
        return Result.success();
    }

    //store value to HttpSession
    @GetMapping("/s1")
    public Result session1(HttpSession session){
        log.info("HttpSession-s1: {}", session.hashCode());

        //store data in session
        session.setAttribute("loginUser", "tom");
        return Result.success();
    }

    //get value from HttpSession
    public Result session2(HttpServletRequest request){
        HttpSession session = request.getSession();
        log.info("HttpSession-s2: {}", session.hashCode());

        //get data from session
        Object loginUser = session.getAttribute("loginUser");
        log.info("loginUser: {}", loginUser);
        return Result.success();
    }

}
