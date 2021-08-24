package com.deyun.service;

import com.deyun.bean.CaloriesVO;
import com.deyun.bean.Plan;
import com.deyun.entity.Calories;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author astupidcoder
 * @since 2021-04-13
 */
public interface ICaloriesService extends IService<Calories> {
    List<Calories> getCalories(String name);

    List<Plan> queryPlan(String name);

    Boolean addPlan(Plan plan);

    Boolean remove(Integer id);

    Plan intelligentCalories(Plan plan);
}
