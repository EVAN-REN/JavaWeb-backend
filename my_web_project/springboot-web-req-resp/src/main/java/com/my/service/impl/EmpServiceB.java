package com.my.service.impl;

import com.my.dao.EmpDao;
import com.my.pojp.Emp;
import com.my.service.EmpService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

//将当前累交给ioc容器管理，成为ioc容器中的bean
//@Component
public class EmpServiceB implements EmpService {

    //创建Dao层对象，用面向接口方法
    //运行时，ioc容器会提供该类型的bean对象，并赋值给该变量 - 依赖注入
    @Autowired
    private EmpDao empDao;

    @Override
    public List<Emp> listEmp() {
        //1.调用dao,获取数据
        List<Emp> empList = empDao.listEmp();
        //2.对数据进行转换处理 - gender, job
        empList.stream().forEach(emp -> {
            //处理 gender 1:男 2:女
            String gender = emp.getGender();
            if("1".equals(gender)){
                emp.setGender("man");
            }else if("2".equals(gender)){
                emp.setGender("woman");
            }

            //处理job - 1:讲师， 2:班主任， 3:就业指导
            String job = emp.getJob();
            if("1".equals(job)){
                emp.setJob("teacher");
            }else if("2".equals(job)){
                emp.setJob("professor");
            }else if("3".equals(job)){
                emp.setJob("grader");
            }
        });
        return empList;
    }
}
