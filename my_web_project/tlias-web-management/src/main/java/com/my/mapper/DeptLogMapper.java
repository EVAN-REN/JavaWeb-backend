package com.my.mapper;

import com.my.pojo.DeptLog;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface DeptLogMapper {

    @Insert("insert into deptlog(log, create_time) VALUES (#{log}, #{createTime})")
    void insert(DeptLog deptLog);
}
