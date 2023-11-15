package com.my.controller;

import com.my.pojo.Emp;
import com.my.pojo.PageBean;
import com.my.pojo.Result;
import com.my.service.EmpService;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

//employee controller
@Slf4j
@RequestMapping("/emps")
@RestController
public class EmpController {

    @Autowired
    private EmpService empService;

    @GetMapping
    public Result page(@RequestParam(defaultValue = "1") Integer page,
                       @RequestParam(defaultValue = "10") Integer pageSize,
                       String name, Short gender,
                       @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate begin,
                       @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate end){
//        if(page == null) page = 1;
//        if(pageSize == null) pageSize = 10;
        log.info("paging query, parameter: {},{},{},{},{},{}", page,pageSize,name,gender,begin,end);

        PageBean pageBean = empService.page(page, pageSize, name, gender, begin, end);
        return Result.success(pageBean);
    }

    /**
     * delete in batches
     */
    @DeleteMapping("/{ids}")
    public Result delete(@PathVariable List<Integer> ids){
        log.info("delete in batches, ids:{}", ids);
        empService.delete(ids);
        return Result.success();
    }

    /**
     * add a new employee
     * @return
     */
    @PostMapping
    public Result save(@RequestBody Emp emp){
        log.info("add a new employee:{}", emp);
        empService.save(emp);

        return Result.success();
    }

    /**
     * query employee
     */
    @GetMapping("/{id}")
    public Result getById(@PathVariable Integer id){
        log.info("query employee by id : {}", id);
        Emp emp = empService.getById(id);
        return Result.success(emp);
    }

    /**
     * update employee
     * @return
     */
    @PutMapping
    public Result update(@RequestBody Emp emp){
        log.info("update employee: {}", emp);
        empService.update(emp);

        return Result.success();
    }

}
