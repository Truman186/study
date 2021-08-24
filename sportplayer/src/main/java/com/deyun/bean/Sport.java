package com.deyun.bean;

import lombok.Data;

import java.util.Date;

/**
 * Created by Administrator on 2020/9/7.
 */
@Data
public class Sport {
    private int id;
    private int userId;
    private String name;
    private String site;
    private Date date;
    private String time;
    private String remake;
    private String username;

    @Override
    public String toString() {
        return "Sport{" +
                "id=" + id +
                ", userId=" + userId +
                ", name='" + name + '\'' +
                ", site='" + site + '\'' +
                ", date=" + date +
                ", time='" + time + '\'' +
                ", remake='" + remake + '\'' +
                ", username='" + username + '\'' +
                '}';
    }
}
