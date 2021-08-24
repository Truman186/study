package com.deyun.dao;

import com.deyun.bean.Role;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Administrator on 2020/8/25.
 */
@Repository
public interface RoleDao {
     List<Role> getRole(String rolename);
     List<Role> getAllRole();

     int addRole(Role role);
     int delete(int roleId);
     int changeStatus(int roleId);
}
