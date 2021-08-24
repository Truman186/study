package com.deyun.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.deyun.entity.Course;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author astupidcoder
 * @since 2021-04-07
 */
public interface ICourseService extends IService<Course> {
   IPage<Course> selectPage(IPage<Course> page);
   List<Course> getList(Integer status);
}
