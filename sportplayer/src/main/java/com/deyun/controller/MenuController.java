package com.deyun.controller;

import com.alibaba.fastjson.JSON;
import com.deyun.bean.Menu;
import com.deyun.bean.RoleMenu;
import com.deyun.dao.MenuDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Administrator on 2020/7/10.
 */
@RestController
public class MenuController {
    @Autowired
    MenuDao menuDao;
    @RequestMapping("/menus")
    public String getAllMenu(String username){

        HashMap<String,Object> data =new HashMap<>();
        List<Menu> menus =menuDao.getMenus(username);
        if(menus!=null){
            data.put("menus",menus);
            data.put("flag",200);
        }
        else {
            data.put("flag",404);
        }
       String s= JSON.toJSONString(data);

        return s;
}
    @RequestMapping("/allmenus")
    public String getMenu(){
        HashMap<String,Object> data =new HashMap<>();
        List<Menu> menus =menuDao.getAll();
        System.out.println(menus);
        if(menus!=null){
            data.put("menus",menus);
            data.put("flag",200);
        }
        else {
            data.put("flag",404);
        }
        String s= JSON.toJSONString(data);

        return s;
    }

    @RequestMapping("/menuList")
    public String menuList(){
        HashMap<String,Object> data =new HashMap<>();
        List<Menu> menuList =menuDao.menuList();
        data.put("menuList",menuList);
        String s= JSON.toJSONString(data);
        return s;
    }

}
