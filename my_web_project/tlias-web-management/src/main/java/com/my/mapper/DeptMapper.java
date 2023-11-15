package com.my.mapper;

import com.my.pojo.Dept;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface DeptMapper {

    //inquire all departments
    @Select("select * from dept")
    List<Dept> list();

//    delete department by id
    @Delete("delete from dept where id = #{id}")
    void deleteById(Integer id);

//    insert department
    @Insert("insert into dept(name, create_time, update_time) values (#{name}, #{createTime}, #{updateTime})")
    void insert(Dept dept);

//    query department
    @Select("select * from dept where id = #{id}")
    Dept query(Integer id);

//    update department
    @Update("update dept set name = #{name}, update_time = #{updateTime} where id = #{id}")
    void update(Dept dept);
}
