package com.deyun.controller;

import com.deyun.bean.User;
import com.deyun.dao.UserDao;
import com.deyun.service.ExportService;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import sun.net.www.http.HttpClient;


import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

/**
 * Created by Administrator on 2020/8/19.
 */
@RestController
public class ExportController {
    @Autowired
    UserDao userDao;
    @Autowired
    ExportService exportService;

   /* private HttpServletResponse response;*/

    @GetMapping("/exportUser")
    public void export(HttpServletResponse response) throws IOException {
       String s= exportService.exportExcelBook(response,userDao);
    }
}
