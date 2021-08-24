package com.deyun.controller;

import ch.qos.logback.core.util.TimeUtil;
import com.alibaba.fastjson.JSON;
import com.deyun.bean.*;
import com.deyun.dao.UserDao;
import org.apache.catalina.connector.Request;
import org.apache.catalina.connector.RequestFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * Created by fangling on 2020/7/12.
 */
@RestController
public class UserController {
    @Autowired
    UserDao userDao;
    @RequestMapping("/alluser")
    public String getUserList(QueryInfo queryInfo){
        //获取1查询信息和当前编号
        int numbers= userDao.getUserCounts("%"+queryInfo.getQuery()+"%");
       int pageStart = (queryInfo.getPageNum()-1)*queryInfo.getPageSize();
       List<User> users= userDao.getAllUser("%"+queryInfo.getQuery()+"%",pageStart,queryInfo.getPageSize());
      // System.out.println(users);
      HashMap<String,Object> res = new HashMap<>();
      res.put("numbers",numbers);
      res.put("data",users);
      String res_string = JSON.toJSONString(res);
      return res_string;
    }
    @RequestMapping("/updateUserState")
    public String updateUserState(@RequestParam String username){
      /*  User user =userDao.selectByID(id);
        System.out.println(user);
        Boolean states =user.getState();
        Boolean state =!states;
        System.out.println(state);*/
        int i = userDao.updateState(username);
        System.out.println(i);
        return i>0?"success":"error";
    }
    /*@RequestMapping("/updateUserState")
    public String updateUserState(@RequestParam("userInfo") User userInfo) {
        int id = userInfo.getId();
        Boolean state = userInfo.getState();
        System.out.println(state);
        int i = userDao.updateState(id, state);
        System.out.println(state);
        return i > 0 ? "success" : "error";
    }*/
    @RequestMapping("/addUser")
    public  String addUser(@RequestBody UserDto user){
        user.setRoleId(1);
     /*   System.out.println("1111111");
        System.out.println(user.getEmail());
        System.out.println(user.getPassword());
        System.out.println(user.getUsername());*/
        user.setState(false);
        int num =userDao.getUserCounts(user.getUsername());
        if(num>0){
            return "exist";
        }
        int i= userDao.addUser(user);
       return i>0?"success":"error";
    }
    @RequestMapping("/deleteUser")
    public String deleteUser(String username){

        //int id =8;
        int i=userDao.deleteUser(username);
        return i>0?"success":"error";
    }

    @RequestMapping("/getUpdateUser")
    public String getUpdateUser(String username){
       User user = userDao.selectByName(username);
       String str = JSON.toJSONString(user);
       return str;
    }

    @RequestMapping("/editUser")
    public String editUser(@RequestBody User user){
       int i= userDao.editUser(user);
        return i>0?"success":"error";
    }
    @GetMapping("/getList")
    public List<User> getList(){
        List<User> list =userDao.getList();
        return list;
    }
    @RequestMapping("/editRole")
    public String editRole(@RequestBody UserDto user){
        System.out.println(user.getUsername());
        System.out.println(user.getRoleId());
        String username =user.getUsername();
        int roleId =user.getRoleId();
        int i =userDao.editRole(username,roleId);
        return i>0?"success":"error";
    }
    @RequestMapping("/queryUserInfo")
    public String queryUserInfo(String username){
        UserInfo  userInfo =userDao.queryUserInfo(username);
        if (userInfo==null){
            UserInfo userInfo1=new UserInfo();
            userInfo1.setUsername(username);
            String str = JSON.toJSONString(userInfo1);
            return str;
        }
        String str = JSON.toJSONString(userInfo);
        return str;
    }
    @PostMapping("/updateUserInfo")
    public String updateUserInfo(@RequestBody UserInfo userInfo) {
        /*if(userInfo.getBirth()!=null){
            DateFormat fmt =new  SimpleDateFormat("yyyy-MM-dd");
            Date date = fmt.parse(userInfo.getBirth());
            userInfo.setBirth(date+"");
        }*/
        int num =userDao.queryNum(userInfo.getUsername());
        if(num==1){
            int i= userDao.updateUserInfo(userInfo);
            return i>0?"success":"error";
        }
      int j=userDao.addUserInfo(userInfo);
        return j>0?"success":"error";
    }
    @PostMapping("/editPsw")
    public String editPsw(@RequestBody PswForm pswForm) {
        if (!pswForm.getOldPsw().equals(pswForm.getNewPsw()) ) {
            if (pswForm.getNewPsw() .equals( pswForm.getSurePsw())) {
                if (pswForm.getOldPsw() .equals(userDao.selectByName(pswForm.getUsername()).getPassword())) {
                    int i = userDao.editPsw(pswForm);
                    return i > 0 ? "success" : "error3";
                }
                return "erro2";
            }
            return "erro1";
        }
        return "erro4";
    }
}
