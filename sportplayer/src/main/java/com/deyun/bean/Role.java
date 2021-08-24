package com.deyun.bean;

import lombok.Data;

/**
 * Created by Administrator on 2020/8/25.
 */
@Data
public class Role {
    private Integer roleId;
    private String roleName;
    private Boolean status;
    private int sort;
    public Role() {
    }

    public Role(Integer roleId, String roleName) {
        this.roleId = roleId;
        this.roleName = roleName;
    }

}
