package com.deyun.dao;

import com.deyun.bean.Plan;
import com.deyun.entity.Calories;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author astupidcoder
 * @since 2021-04-13
 */
public interface CaloriesMapper extends BaseMapper<Calories> {
    List<Plan> queryPlan(String name);
}
