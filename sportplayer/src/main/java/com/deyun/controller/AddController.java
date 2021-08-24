package com.deyun.controller;

import com.deyun.bean.UserDto;
import com.deyun.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Administrator on 2020/9/14.
 */
@RestController
public class AddController {
    @Autowired
    UserDao userDao;
    @RequestMapping("/addUserTest")
    public void addUser(){
        for(int i=0;i<100;i++){
            String username ="test"+i;
            UserDto userDto =new UserDto();
            userDto.setUsername(username);
            userDto.setState(true);
            userDto.setPassword("123456");
            userDto.setRoleId(1);
            userDto.setEmail("188757554"+i+"qq.com");
            System.out.println("添加成功");
           int j= userDao.addUser(userDto);
           System.out.println(j);
        }
    }
}
