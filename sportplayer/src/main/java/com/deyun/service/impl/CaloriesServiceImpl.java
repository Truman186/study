package com.deyun.service.impl;
import com.deyun.bean.CaloriesVO;
import com.deyun.bean.Plan;
import com.deyun.dao.PlandetailMapper;
import com.deyun.entity.Calories;
import com.deyun.dao.CaloriesMapper;
import com.deyun.entity.Foodplanmain;
import com.deyun.entity.Plandetail;
import com.deyun.service.ICaloriesService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.deyun.service.IFoodplanmainService;
import com.deyun.service.IPlandetailService;
import com.deyun.util.BeanUtil;
import com.deyun.util.CacheName;
import com.deyun.util.RedisClient;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author astupidcoder
 * @since 2021-04-13
 */
@Service
public class CaloriesServiceImpl extends ServiceImpl<CaloriesMapper, Calories> implements ICaloriesService {
    @Resource
    IFoodplanmainService foodplanmainService;
    @Autowired
    private RedisClient redisClient;
    @Resource
    PlandetailMapper plandetailMapper;
    @Resource
    IPlandetailService plandetailService;

    @Override
    public List<Calories> getCalories(String name) {
        Object o = redisClient.get(CacheName.caloriesCacheName + name);
        if (o==null){
            List<Calories> list = this.lambdaQuery().like(Calories::getName, name).list();
            redisClient.set(CacheName.caloriesCacheName + name,list);
            return list;
        }

        return (List<Calories>) o;
    }

    @Override
    @Cacheable(cacheNames =CacheName.plan)
    public List<Plan> queryPlan(String name) {
      /*  Object o = redisClient.get(CacheName.plan+name);
        if(o==null){
            List<Plan> plans = baseMapper.queryPlan(name);
            redisClient.set(CacheName.plan+name,plans);
            return plans;
        }
        return (List<Plan>) o;*/
      return  baseMapper.queryPlan(name);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean addPlan(Plan plan) {
        Foodplanmain foodplanmain = new Foodplanmain();
        foodplanmain.setRemark(plan.getRemark());
        foodplanmain.setMeals(plan.getMeals());
        foodplanmain.setUserName(plan.getUsername());
        boolean save = foodplanmainService.save(foodplanmain);
        if (!save) {
            return false;
        }
        List<CaloriesVO> list = plan.getList();
        list.forEach(caloriesVO -> {
            Plandetail plandetail = new Plandetail();
            plandetail.setCalories(caloriesVO.getId());
            plandetail.setMainId(foodplanmain.getId());
            plandetail.setQuantity(caloriesVO.getQuantity());
            int insert = plandetailMapper.insert(plandetail);
            if (insert < 1) {
                throw new RuntimeException("添加明细失败");
            }
        });
        return true;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean remove(Integer id) {
        List<Plandetail> list = plandetailService.lambdaQuery().eq(Plandetail::getMainId, id).list();
        List<Integer> ids = new ArrayList<>();
        list.forEach(plandetail -> {
            ids.add(plandetail.getId());
        });
        plandetailMapper.deleteBatchIds(ids);
        boolean b = foodplanmainService.removeById(id);
        if (!b) {
            throw new RuntimeException("删除失败");
        }
        return true;
    }

    @Override
    public Plan intelligentCalories(Plan plan) {
        List<Calories> list = this.list();
        list.sort((o1, o2) -> {
            Integer q1=o1.getCalories();
            Integer q2=o2.getCalories();
            return q1.compareTo(q2);
        });
        List<Integer> value = plan.getValue();
        Integer min = value.get(0);
        Integer max = value.get(1);
        List<CaloriesVO> caloriesVOS = new ArrayList<>();
        if (BeanUtil.isNotEmpty(list)) {
            list.forEach(calories -> {
                CaloriesVO caloriesVO = new CaloriesVO();
                BeanUtils.copyProperties(calories, caloriesVO);
                caloriesVOS.add(caloriesVO);
            });
            Integer sunCalories = 0;
            int i=1;
            do {
                for (CaloriesVO caloriesVO:caloriesVOS) {
                    caloriesVO.setQuantity(i);
                    sunCalories+=caloriesVO.getCalories();
                    if (sunCalories>max){
                        break;
                    }
                }
                i++;
            }while (!((min<sunCalories&&max>sunCalories)||(sunCalories>max)));
            List<CaloriesVO> collect = caloriesVOS.stream().filter(s -> s.getQuantity() > 0).collect(Collectors.toList());
            plan.setSumCalories(sunCalories);
            plan.setList(collect);
            return plan;
        }
        return null;
    }
}
