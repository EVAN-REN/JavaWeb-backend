package com.my.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

//class of paging query
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageBean {

    private Long total; //total data
    private List rows; //data


}
