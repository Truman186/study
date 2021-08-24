package com.deyun.bean;

import lombok.Data;

/**
 * Created by Administrator on 2020/10/28.
 */
@Data
public class PswForm {
    private String username;
    private String oldPsw;
    private String newPsw;
    private String surePsw;
}
