package com.deyun.bean;
import lombok.Data;
/**
 * Created by Administrator on 2020/8/21.
 */
@Data
public class UserDto {
    private int id;
    private String username;
    private String password;
    private String email;
    private int roleId;
    private Boolean state;
    public UserDto(){}
    public UserDto(String username, String password, String email, int roleId, Boolean state) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.roleId = roleId;
        this.state = state;
    }


    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", role='" + roleId + '\'' +
                ", state=" + state +
                '}';
    }
}
