package com.itheima;

import com.itheima.mapper.EmpMapper;
import com.itheima.pojo.Emp;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@SpringBootTest
class SpringbootMybatisCrudApplicationTests {

    @Autowired
    private EmpMapper empMapper;

    @Test
    public void testDelete(){
        int delete = empMapper.delete(17);
        System.out.println(delete);
    }

    @Test
    public void testInsert(){
         Emp emp = new Emp();
         emp.setUsername("Tom2");
         emp.setName("汤姆2");
         emp.setImage("1.jpg");
         emp.setGender((short)1);
         emp.setJob((short)1);
         emp.setEntrydate(LocalDate.of(2000,1,1));
         emp.setCreateTime(LocalDateTime.now());
         emp.setUpdateTime(LocalDateTime.now());
         emp.setDeptId(1);

         empMapper.insert(emp);
        System.out.println(emp.getId());
    }

    //更新员工
    @Test
    public void testUpdate(){
        Emp emp = new Emp();
        emp.setId(18);
        emp.setUsername("Tom1");
        emp.setName("汤米1");
        emp.setImage("1.jpg");
        emp.setGender((short)1);
        emp.setJob((short)1);
        emp.setEntrydate(LocalDate.of(2000,1,1));
        emp.setUpdateTime(LocalDateTime.now());
        emp.setDeptId(1);

        empMapper.update(emp);
    }

    @Test
    public void testUpdate2(){
        Emp emp = new Emp();
        emp.setId(18);
        emp.setUsername("Tom222");
        emp.setName("汤米222");
        emp.setGender((short)2);
        emp.setUpdateTime(LocalDateTime.now());

        empMapper.update2(emp);
    }

    //根据ID查询员工
    @Test
    public void testGetById(){
        Emp emp = empMapper.getById(20);
        System.out.println(emp);
    }

    //根据条件查询员工
    @Test
    public void testList(){
//        List<Emp> empList = empMapper.list("张", (short)1, LocalDate.of(2010,1,1), LocalDate.of(2020,1,1));
        List<Emp> empList = empMapper.list(null, null, null, null);
        System.out.println(empList);
    }

//    批量删除
    @Test
    public void testDeleteByIds(){
        List<Integer> ids = Arrays.asList(13,14,15);
        empMapper.deleteByIds(ids);

    }
}
