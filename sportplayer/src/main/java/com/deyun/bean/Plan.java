package com.deyun.bean;

import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
public class Plan  implements Serializable {
    private Integer id;
    private String username;
    private String remark;
    private String meals;
    private String plan;
    private Integer sumCalories;
    List<CaloriesVO> list=new ArrayList<>();
    List <Integer> value=new ArrayList<>();
}
