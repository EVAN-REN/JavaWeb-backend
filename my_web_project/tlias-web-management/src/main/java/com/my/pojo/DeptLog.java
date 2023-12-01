package com.my.pojo;

import java.time.LocalDateTime;

public class DeptLog {
    private Integer id;
    private String log;

    private LocalDateTime createTime;

    @Override
    public String toString() {
        return "DeptLog{" +
                "id=" + id +
                ", log='" + log + '\'' +
                ", createTime=" + createTime +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLog() {
        return log;
    }

    public void setLog(String log) {
        this.log = log;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }
}
