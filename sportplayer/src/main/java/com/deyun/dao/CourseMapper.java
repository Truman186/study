package com.deyun.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.deyun.entity.Course;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author astupidcoder
 * @since 2021-04-07
 */
@Repository
@Mapper
public interface CourseMapper extends BaseMapper<Course> {

}
