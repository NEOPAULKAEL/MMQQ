package com.szm.chat.dao;

import com.szm.chat.entity.User;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * 管理员用户权限
 * @author Vector_Wu
 * @version 1.0
 */

public interface IAdminDao {
    //Dao层接收业务层的对象开启连接
    public void setConnection(Connection con);

    //删除用户
    void deleteUser(User user) throws SQLException;

    //授权用户
    void updateUserPower(User user) throws SQLException;

    //查询所有用户
    User selectUser(int id) throws SQLException;
}
