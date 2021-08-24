package com.deyun.dao;

import com.deyun.bean.Menu;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestBody;
import java.util.List;

/**
 * Created by Administrator on 2020/7/10.
 */
@Repository
public interface MenuDao {

     List<Menu> getMenus(String username);
     List<Menu> getAll();
    List<Menu> menuList();
    List<Menu> menu();
    void delete(int roleId);
    void add(@Param("roleId")int roleId,@Param("menuId")int menuId);
    List<Integer>  selectMenuByRoleId(@Param("roleId")Integer roleId);
}
