package com.deyun.controller;

import com.alibaba.fastjson.JSON;
import com.deyun.bean.Sport;
import com.deyun.dao.SportDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.Array;
import java.text.DateFormat;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Administrator on 2020/9/7.
 */
@RestController
public class SportController {
    @Autowired
    SportDao sportDao;
    @RequestMapping("/getSportList")
    public String getSportList(@RequestBody Sport sport){
        HashMap<String,Object> data =new HashMap<>();
        if(sport.getDate()!=null){
            DateFormat df1=DateFormat.getDateInstance();
            sport.setTime(df1.format(sport.getDate()));
        }
        List<Sport> list =sportDao.getSportList(sport);
        data.put("list",list);
        String s= JSON.toJSONString(data);
        return s;
    }
    @RequestMapping("/addSportPlay")
    public String addSportPlay(@RequestBody Sport sport){
        if(sport.getDate()!=null){
            DateFormat df1=DateFormat.getDateInstance();
            sport.setTime(df1.format(sport.getDate()));
        }
        else if(sport.getDate()==null){
            return "defaut";
        }
        sport.setUserId(sportDao.query(sport.getUsername()));
        int i=sportDao.add(sport);
        return i>0?"success":"error";
    }
    @RequestMapping("/deletePlay")
    public String deletePlay(int id){
       int i= sportDao.deletePlay(id);
        return i>0?"success":"error";
    }

    @RequestMapping("/edit")
    public String edit(@RequestBody Sport sport){
        if(sport.getDate()!=null){
            DateFormat df1=DateFormat.getDateInstance();
            sport.setTime(df1.format(sport.getDate()));
        }
        int i =sportDao.edit(sport);
        return i>0?"success":"error";
    }

    @RequestMapping("/queryById")
    public String queryById(int id){
        Sport sport =sportDao.queryById(id);
        String s= JSON.toJSONString(sport);
        return s;
    }
}
