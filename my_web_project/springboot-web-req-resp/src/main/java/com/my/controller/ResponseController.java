package com.my.controller;

import com.my.pojp.Address;
import com.my.pojp.Result;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

//@RestController包含@Controller和@ResponseBody
@RestController
public class ResponseController {

//    response type(response type are too many, hard to manage)
    /*
    @RequestMapping("/hello")
    public String hello(){
        System.out.println("Hello World!");
        return "Hello World";
    }

    @RequestMapping("/getAddr")
    public Address getAddr(){
        Address addr = new Address();
        addr.setProvince("Henan");
        addr.setCity("Zhengzhou");
        return addr;
    }

    @RequestMapping("/listAddr")
    public List<Address> listAddr(){
        List<Address> list = new ArrayList<>();

        Address addr = new Address();
        addr.setProvince("Beijing");
        addr.setCity("Beijing");

        Address addr2 = new Address();
        addr2.setProvince("Henan");
        addr2.setCity("Zhengzhou");

        list.add(addr);
        list.add(addr2);
        return list;
    }
    */

    //use result.java to unify
    //保证返回的格式标准，利于维护，code,msg,data三个成员变量
    @RequestMapping("/hello")
    public Result hello(){
        System.out.println("Hello World!");
//        return new Result(1, "success", "Hello World");
        return  Result.success("Hello World");
    }

    @RequestMapping("/getAddr")
    public Result getAddr(){
        Address addr = new Address();
        addr.setProvince("Henan");
        addr.setCity("Zhengzhou");
        return Result.success(addr);
    }

    @RequestMapping("/listAddr")
    public Result listAddr(){
        List<Address> list = new ArrayList<>();

        Address addr = new Address();
        addr.setProvince("Beijing");
        addr.setCity("Beijing");

        Address addr2 = new Address();
        addr2.setProvince("Henan");
        addr2.setCity("Zhengzhou");

        list.add(addr);
        list.add(addr2);
        return Result.success(list);
    }

}
