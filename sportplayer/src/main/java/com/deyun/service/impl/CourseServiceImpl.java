package com.deyun.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.deyun.dao.CourseMapper;
import com.deyun.service.ICourseService;
import com.deyun.entity.Course;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author astupidcoder
 * @since 2021-04-07
 */
@Service
public class CourseServiceImpl extends ServiceImpl<CourseMapper, Course> implements ICourseService {

    @Override
    public IPage<Course> selectPage(IPage<Course> page) {
        List<Course> list = this.lambdaQuery().list();
        return page.setRecords(list);
    }

    @Override
    public List<Course> getList(Integer status) {
        List<Course> list = this.lambdaQuery().eq(Course::getStatus, status).list();
        return list;
    }
}
