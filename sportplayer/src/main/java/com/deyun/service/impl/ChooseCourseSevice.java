package com.deyun.service.impl;

import com.deyun.bean.ChooseForm;
import com.deyun.dao.CourseDao;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by Administrator on 2020/10/28.
 */
@Service
public class ChooseCourseSevice implements Runnable{
    @Resource
    CourseDao courseDao;
    @Override
    public void run() {
        while (true){

        }
    }
    public synchronized String chooseCourse(ChooseForm chooseForm){
        if (courseDao.beforeChoose(chooseForm)==0){   //检查时间是否冲突，
            if(courseDao.checkCouseNum(chooseForm.getCid())>0){ //课余量是否大于1
                courseDao.chooseCourse(chooseForm);    //选课
                courseDao.editCourseNum(chooseForm.getCid());  //课余量减一
                return "Success";
            }
            return "erro";
        }
        return "erro";

    }
}
