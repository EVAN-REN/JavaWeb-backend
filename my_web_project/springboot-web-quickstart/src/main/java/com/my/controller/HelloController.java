package com.my.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    @RequestMapping("/hello")
    public String helle(){
        System.out.println("Hello World");
        return "Hello World";
    }
}
