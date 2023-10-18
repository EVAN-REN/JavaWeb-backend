package com.my.controller;

import com.my.pojp.User;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@RestController
public class RequestController {

    //1.simple parameter:
    //original method:through HttpServletRequest get the parameter
//    @RequestMapping("simpleParam")
//    public String simpleParam(HttpServletRequest request){
//        String name = request.getParameter("name");
//        String ageStr = request.getParameter("age");
//
//        int age = Integer.parseInt(ageStr);
//        System.out.println(name + ":" + age);
//        return "OK";
//    }

    //using springboot get the parameter
    //当username和name不对应时，不汇报错，但会找不到返回null
    // 但可以用@RequestParam来添加一个映射，添加映射的参数可以在required中设置是否必须
    @RequestMapping("simpleParam")
    public String simpleParam(@RequestParam(name = "name", required = false) String username, Integer age){
        System.out.println(username + ":" + age);
        return "OK";
    }


    //2.entity parameter
    //simple entity
    @RequestMapping("simplePojo")
    public String simplePojo(User user){
        System.out.println(user);
        return "OK";
    }

    //complex entity
    @RequestMapping("complexPojo")
    public String complexPojo(User user){
        System.out.println(user);
        return "OK";
    }

//    3.array parameter
    @RequestMapping("arrayParam")
    public String arrayParam(String[] hobby){
        System.out.println(Arrays.toString(hobby));
        return "OK";
    }

//    list parameter，需要加@RequestParam
    @RequestMapping("listParam")
    public String listParam(@RequestParam List<String> hobby){
        System.out.println(hobby);
        return "OK";
    }

//    4.datetime parameter,要设置格式
    @RequestMapping("dateParam")
    public String dateParam(@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime updateTime){
        System.out.println(updateTime);
        return "OK";
    }

//    5.json parameter
    @RequestMapping("jsonParam")
    public String jsonParam(@RequestBody User user){
        System.out.println(user);
        return "OK";
    }

//    6.path parameter
    @RequestMapping("path/{id}")
    public String pathParam(@PathVariable Integer id){
        System.out.println(id);
        return "OK";
    }

    @RequestMapping("path/{id}/{name}")
    public String pathParam2(@PathVariable Integer id, @PathVariable String name){
        System.out.println(id);
        System.out.println(name);
        return "OK";
    }

}
