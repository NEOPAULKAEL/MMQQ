/**
 * @program: User
 * * @description: 用户 数据访问对象 接口
 * * @author:cro
 * * @create: 2019-05-06 17:38
 **/

package com.szm.chat.dao;

import com.szm.chat.entity.User;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface IUserDao {
    /*Dao层接收业务层的对象开启连接*/
    public void setConnection(Connection con);
    //添加用户
    void addUser(User user) throws SQLException;
    //登录,查询用户密码
    User  getPassword(String name,String password) throws SQLException;
    //查询个人信息，根据用户名
    User selectUser(String name) throws SQLException;
    //修改个人信息,根据id获取对应用户
    void updateUser(User user) throws SQLException;
    /*查找所有用户*/
    List<User> selectAllUser() throws SQLException;
}
