package com.my.dao.impl;

import com.my.dao.EmpDao;
import com.my.pojp.Emp;
import com.my.utils.XmlParserUtils;
import org.springframework.stereotype.Repository;

import java.util.List;

//将当前累交给ioc容器管理，成为ioc容器中的bean
//@Component
@Repository("daoA") //可以指定词实现类存为bean的名字为daoA，默认是类名首字母小写
public class EmpDaoA implements EmpDao {

    @Override
    public List<Emp> listEmp() {
        //1.加载并解析emp.xml
        String file = this.getClass().getClassLoader().getResource("emp.xml").getFile();
        System.out.println(file);
        List<Emp> empList = XmlParserUtils.parse(file, Emp.class);
        return empList;
    }
}
