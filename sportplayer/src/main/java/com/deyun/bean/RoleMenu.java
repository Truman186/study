package com.deyun.bean;

import lombok.Data;

import java.util.List;

/**
 * Created by Administrator on 2020/9/4.
 */
@Data
public class RoleMenu {
    private int roleId;
/*    private List<Integer> menuIdList;*/
    private String[] list;
}
