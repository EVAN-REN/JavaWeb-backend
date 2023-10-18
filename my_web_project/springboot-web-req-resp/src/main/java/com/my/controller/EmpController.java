package com.my.controller;

import com.my.pojp.Emp;
import com.my.pojp.Result;
import com.my.service.EmpService;
import com.my.service.impl.EmpServiceA;
import com.my.utils.XmlParserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
public class EmpController {

    //创建service层对象，面向接口创建
    //运行时，ioc容器会提供该类型的bean对象，并赋值给该变量 - 依赖注入
    //@Qualifier("empServiceA")
    @Autowired
    private EmpService empService;

    @RequestMapping("/listEmp")
    public Result list(){
        //1.调用service，获取数据
        List<Emp> empList = empService.listEmp();

        //2.响应数据
        return Result.success((empList));


//        //1.加载并解析emp.xml
//        String file = this.getClass().getClassLoader().getResource("emp.xml").getFile();
//        System.out.println(file);
//        List<Emp> empList = XmlParserUtils.parse(file, Emp.class);
//
//        //2.对数据进行转换处理 - gender, job
//        empList.stream().forEach(emp -> {
//            //处理 gender 1:男 2:女
//            String gender = emp.getGender();
//            if("1".equals(gender)){
//                emp.setGender("男");
//            }else if("2".equals(gender)){
//                emp.setGender("女");
//            }
//
//            //处理job - 1:讲师， 2:班主任， 3:就业指导
//            String job = emp.getJob();
//            if("1".equals(job)){
//                emp.setJob("teacher");
//            }else if("2".equals(job)){
//                emp.setJob("professor");
//            }else if("3".equals(job)){
//                emp.setJob("grader");
//            }
//        });
//
//        //3.响应数据
//        return Result.success((empList));
    }

}
