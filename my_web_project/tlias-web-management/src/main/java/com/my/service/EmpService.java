package com.my.service;

import com.my.pojo.Emp;
import com.my.pojo.PageBean;

import java.time.LocalDate;
import java.util.List;

public interface EmpService {
    /**
     * paging query
     * @param page
     * @param pageSize
     * @return
     */
    PageBean page(Integer page, Integer pageSize, String name, Short gender, LocalDate begin, LocalDate end);

    /**
     * delete in batches
     * @param ids
     */
    void delete(List<Integer> ids);

    /**
     * add a new employee
     */
    void save(Emp emp);

    /**
     * query employee
     * @param id
     * @return
     */
    Emp getById(Integer id);

    /**
     * update employee
     * @param emp
     */
    void update(Emp emp);

    /**
     * employee login
     * @param emp
     * @return
     */
    Emp login(Emp emp);
}
