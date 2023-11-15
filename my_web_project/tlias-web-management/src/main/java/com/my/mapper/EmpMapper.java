package com.my.mapper;

import com.my.pojo.Emp;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.time.LocalDate;
import java.util.List;

@Mapper
public interface EmpMapper {

    /**
     * query the count of data
     * @return
     */
    @Select("select count(*) from emp")
    public Long count();

    /**
     * paging query
     * @param start
     * @param pageSize
     * @return
     */
//    @Select("select * from emp limit #{start},#{pageSize}")
//    public List<Emp> page(Integer start, Integer pageSize);

    /**
     * employee query
     * @return
     */
//    @Select("select * from emp")
    public List<Emp> list(String name, Short gender, LocalDate begin, LocalDate end);


    /**
     * delete in batches
     * @param ids
     */
    void delete(List<Integer> ids);

    /**
     * insert a new employee
     */
    @Insert("insert into emp(username, name, gender, image, job, entrydate, dept_id, create_time, update_time) " +
            "values (#{username}, #{name}, #{gender}, #{image}, #{job}, #{entrydate}, #{deptId}, #{createTime}, #{updateTime})")
    void insert(Emp emp);

    /**
     * query employee by id
     * @param id
     * @return
     */
    @Select("select * from emp where id = #{id}")
    Emp getById(Integer id);

    /**
     * update employee
     * @param emp
     */
    void update(Emp emp);

    /**
     * query employee by username and password
     * @param emp
     * @return
     */
    @Select("select * from emp where username = #{username} and password = #{password}")
    Emp getByUsernameAndPassword(Emp emp);
}
