package com.deyun.util;

import com.deyun.bean.CaloriesVO;
import com.deyun.entity.Calories;
import org.springframework.beans.BeanUtils;
import org.springframework.cglib.beans.BeanCopier;

public class TestMain {
    public static void main(String[] args) {
        Calories calories=new Calories();
        calories.setId(1);
        calories.setName("aaa");
        CaloriesVO caloriesVO=new CaloriesVO();
        /*final BeanCopier copier = BeanCopier.create(Calories.class, CaloriesVO.class, false);
        copier.copy(calories, caloriesVO, null);
      */
        BeanUtils.copyProperties(calories,caloriesVO);
        System.out.println(caloriesVO.getName()+"/"+caloriesVO.getId());
    }
}
