package com.deyun.dao;

import com.deyun.bean.Sport;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Administrator on 2020/9/7.
 */
@Repository
public interface SportDao {
    List<Sport> getSportList(Sport sport);
/*
    List<Sport> getSportList(@Param("username") String username,@Param("name") String name,@Param("time") String time);
*/
    int query(@Param("username") String username);
    int add(Sport sport);
    int deletePlay(int id);
    int edit(Sport sport);
    Sport queryById(@Param("id") int id);
}
