package com.deyun.controller;

import com.alibaba.fastjson.JSON;
import com.deyun.service.LoginRecordService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Map;

/**
 * Created by Administrator on 2020/10/24.
 */
@RestController
public class LoginRecordController {
    @Resource
    LoginRecordService loginRecordService;
    @GetMapping("/loginRecord")
    public  String loginRecord(){
        Map map =loginRecordService.countLoginRecord();
        String s= JSON.toJSONString(map);
        return s;
    }
}
