package com.deyun.dao;

import com.deyun.bean.*;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Administrator on 2020/7/8.
 */
@Repository
public interface UserDao {
     User getUserByMassege(@Param("username") String username, @Param("password") String password);
     List<User>  getAllUser(@Param("username") String username,@Param("pageStart") int PageStart,@Param("pageSize") int pageSize);
     int getUserCounts(@Param("username") String username);
     int updateState(@Param("username") String username);
   // public int updateState(Integer id,Boolean state);
     int addUser(UserDto user);
     int deleteUser(String username);
     User selectByID(String username);
     User selectByName(String username);
     int editUser(User user);
     List<User> getList();
     int editRole(@Param("username")String username,@Param("roleId")int roleId);
     User check(@Param("username") String username);
     int regist(UserDto user);
     List<Integer> getIdByName(String username);
    int countLoginRecord(String time);
    int addLoginRecord(LoginRecord loginRecord);
    UserInfo queryUserInfo(String username);
    int updateUserInfo(UserInfo userInfo);
    int queryNum(String username);
    int addUserInfo(UserInfo userInfo);
    int countPerNum(String time);
    int editPsw(PswForm pswForm);
}

