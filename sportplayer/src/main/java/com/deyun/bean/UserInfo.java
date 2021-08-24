package com.deyun.bean;

import lombok.Data;

import java.util.Date;

/**
 * Created by Administrator on 2020/10/27.
 */
@Data
public class UserInfo {
    private int id;
    private String trueName;
    private String username;
    private String sex;
    private String birth;
    private String address;
    private String phone;
    private String idcard;
}
