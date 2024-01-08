package com.my.controller;

import com.my.anno.Log;
import com.my.pojo.Dept;
import com.my.pojo.Result;
import com.my.service.DeptService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Logger;

//department controller
@Slf4j
@RequestMapping("/depts")
@RestController
public class DeptController {

    //日志记录或用注解
//    private static Logger log = (Logger) LoggerFactory.getLogger(DeptController.class);

//    @RequestMapping(value = "/depts",method = RequestMethod.GET)//指定请求方式为get，也可用注解

    @Autowired
    private DeptService deptService;

    @GetMapping
    public Result list(){
        log.info("inquire all the data of departments");

        //call service to inquire the data of department
        List<Dept> deptList = deptService.list();

        return Result.success(deptList);
    }

//    delete department
    @Log
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id){
        log.info("delete department by id:{}", id);
        deptService.delete(id);
        return Result.success();
    }

//    add department
    @Log
    @PostMapping
    public Result add(@RequestBody Dept dept){
        log.info("add department:{}", dept);
        deptService.add(dept);
        return Result.success();
    }

//    inquire department
    @GetMapping("/{id}")
    public Result inquire(@PathVariable Integer id){
        log.info("inquire department ID:{}", id);
        Dept dept = deptService.inquire(id);
        return Result.success(dept);
    }

//    modify department
    @PutMapping
    public Result modify(@RequestBody Dept dept){
        log.info("modify department:{}", dept);
        deptService.modify(dept);
        return Result.success();
    }

}
