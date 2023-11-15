package com.my.service;

import com.my.pojo.Dept;

import java.util.List;

public interface DeptService {

//    inquire all the data of departments
    List<Dept> list();

//    delete department
    void delete(Integer id);

//    add department
    void add(Dept dept);

//    inquire department
    Dept inquire(Integer id);

//    modify department
    void modify(Dept dept);

}
