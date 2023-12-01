package com.my.service.impl;

import com.my.mapper.DeptLogMapper;
import com.my.mapper.DeptMapper;
import com.my.mapper.EmpMapper;
import com.my.pojo.Dept;
import com.my.pojo.DeptLog;
import com.my.service.DeptLogService;
import com.my.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class DeptServiceImpl implements DeptService {

    @Autowired
    private DeptMapper deptMapper;

    @Autowired
    private EmpMapper empMapper;

    @Autowired
    private DeptLogService deptLogService;

    @Override
    public List<Dept> list() {
        return deptMapper.list();
    }

    @Transactional(rollbackFor = Exception.class) //spring事务管理
    @Override
    public void delete(Integer id) {

        try {
            deptMapper.deleteById(id); //delete department by id

            empMapper.deleteByDeptId(id); //delete employee by dept id
        }finally {
            DeptLog deptLog = new DeptLog();
            deptLog.setCreateTime(LocalDateTime.now());
            deptLog.setLog("执行了解散部门操作，此次结算的是" + id + "号部门");
            deptLogService.insert(deptLog);
        }

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
