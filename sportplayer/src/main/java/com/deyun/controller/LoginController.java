package com.deyun.controller;

import com.alibaba.fastjson.JSON;
import com.deyun.bean.LoginRecord;
import com.deyun.bean.User;
import com.deyun.bean.UserDto;
import com.deyun.dao.UserDao;
import com.deyun.util.TimeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.InetAddress;
import java.util.HashMap;
import java.util.Objects;

/**
 * Created by Administrator on 2020/7/8.
 */
@RestController
public class LoginController {
    @Autowired
    UserDao userDao;
    @RequestMapping("/login")
    @Transactional
    public String login(@RequestBody  User user){
        String flag ="error";
        User us = userDao.getUserByMassege(user.getUsername(),user.getPassword());
        HashMap<String,Object> res =new HashMap<>();
        if (us!=null){
            flag= "ok";
        }
        res.put("flag",flag);
        res.put("user",us);
        String res_json = JSON.toJSONString(res);
        InetAddress ia=null;
        try {
            ia=ia.getLocalHost();
            String localip=ia.getHostAddress();
            LoginRecord loginRecord =new LoginRecord();
            loginRecord.setTime(TimeUtil.getTimeNow("yyyy-MM-dd",0));
            loginRecord.setUsername(user.getUsername());
            loginRecord.setIp(localip);
            userDao.addLoginRecord(loginRecord);   //添加登录记录
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return res_json;
    }
    @RequestMapping("/regist")
    public String regist(@RequestBody UserDto user){
        String flag ="success";
        User us=userDao.check(user.getUsername());
        HashMap<String,Object> res =new HashMap<>();
        if (us!=null){
            flag= "erro";
        }
        user.setRoleId(1);
        user.setState(false);
        userDao.regist(user);
        return flag;
    }
}
