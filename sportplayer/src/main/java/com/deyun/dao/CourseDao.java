package com.deyun.dao;

import com.deyun.bean.ChooseForm;
import com.deyun.bean.Course;
import com.deyun.bean.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Administrator on 2020/10/12.
 */
@Repository
public interface CourseDao {
    List<Course> getList(Course course);
   void addCourse(Course course);
   int check(Course course);
   int editCheck(Course course);
    List<Course>  vertifyCourse(@Param("cname") String cname, @Param("username")String username);
    int editCourse(Course course);
    int deleteCourse(int cid);
    List<Course>  view(@Param("cname") String cname, @Param("username")String username);
    int chooseCourse(ChooseForm chooseForm);
    int beforeChoose(ChooseForm chooseForm);
    int editCourseNum(int cid);
    List<Course> myCourse(ChooseForm chooseForm);
    int dropCourse(ChooseForm chooseForm);
    int addCourseNum(int cid);
    List<User> sourceList(int cid);
    String mysourceList(@Param("username") String username,@Param("weekday") String weekday,@Param("time") String time);
     String mycourceList(@Param("username") String username,@Param("weekday") String weekday,@Param("time") String time);
    int checkCourse(Course course);
    int checkCouseNum(int cid);
}
