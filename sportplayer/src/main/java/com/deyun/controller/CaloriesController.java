package com.deyun.controller;


import com.alibaba.fastjson.JSON;
import com.deyun.bean.CaloriesVO;
import com.deyun.bean.Plan;
import com.deyun.entity.Calories;
import com.deyun.service.ICaloriesService;
import com.deyun.util.BeanUtil;
import com.deyun.util.Result;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author astupidcoder
 * @since 2021-04-13
 */
@RestController
@RequestMapping("/calories")
public class CaloriesController {
    @Resource
    ICaloriesService caloriesService;
    @GetMapping("/getCalories")
    public Result getCalories(String name){
        List<Calories> list = caloriesService.getCalories(name);
        return Result.success(list);
    }

    @PostMapping("/addCalories")
    public Result addCalories(@RequestBody Calories calories){
        boolean save = caloriesService.save(calories);
        if (save){
            return Result.success(calories);
        }
        return Result.fail("添加失败");
    }
    @PostMapping("/delete")
    public Result delete(Long id){
        boolean b = caloriesService.removeById(id);
        if (b){
            return Result.success(null);
        }
        return Result.fail("删除失败");
    }
    @PostMapping("/edit")
    public Result edit(@RequestBody Calories calories){
        boolean save = caloriesService.saveOrUpdate(calories);
        if (save){
            return Result.success(calories);
        }
        return Result.fail("添加失败");
    }

    @GetMapping("/queryPlan")
    public Result queryPlan(String username){
        return Result.success(caloriesService.queryPlan(username));
    }

    @GetMapping("/plan")
    public Result plan(String name){
        List<Calories> list = caloriesService.getCalories(name);
        List<CaloriesVO> caloriesVOS=new ArrayList<>();
        for (Calories calories:list ) {
            CaloriesVO caloriesVO= new CaloriesVO();
            BeanUtils.copyProperties(calories,caloriesVO);
            caloriesVO.setQuantity(1);
            caloriesVOS.add(caloriesVO);
        }
        return Result.success(caloriesVOS);
    }

    @PostMapping("/addPlan")
    public Result addPlan(@RequestBody Plan plan){
        Boolean flag = caloriesService.addPlan(plan);
        if (flag){
            return Result.success("添加成功");
        }
        return Result.fail("添加失败");
    }

    @PostMapping("/remove")
    public Result remove(Integer id){
        Boolean remove = caloriesService.remove(id);
        if (remove){
            return Result.success("删除成功");
        }
        return Result.fail("删除失败");
    }

    @PostMapping("/intelligentCalories")
    public Result intelligentCalories(@RequestBody Plan plan){
        return Result.success(caloriesService.intelligentCalories(plan));
    }
}
