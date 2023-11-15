package com.my.service.impl;

import com.my.mapper.DeptMapper;
import com.my.pojo.Dept;
import com.my.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class DeptServiceImpl implements DeptService {

    @Autowired
    private DeptMapper deptMapper;

    @Override
    public List<Dept> list() {
        return deptMapper.list();
    }

    @Override
    public void delete(Integer id) {
        deptMapper.deleteById(id);
    }

    @Override
    public void add(Dept dept) {
        dept.setCreateTime(LocalDateTime.now());
        dept.setUpdateTime(LocalDateTime.now());

        deptMapper.insert(dept);
    }

    @Override
    public Dept inquire(Integer id) {
        return deptMapper.query(id);
    }

    @Override
    public void modify(Dept dept) {
        dept.setUpdateTime(LocalDateTime.now());

        deptMapper.update(dept);
    }
}
