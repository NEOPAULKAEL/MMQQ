/**
 * @program: User
 * * @description: 用户 业务 接口
 * * @author:cro
 * * @create: 2019-05-06 17:05
 **/

package com.szm.chat.service;

import com.szm.chat.entity.User;

import java.sql.SQLException;
import java.util.List;

public interface IUserService {
    /*添加用户*/
    void register(User user) throws SQLException;
    /*登录*/
    User  login(String name,String passwordinput)  throws SQLException;
    /*查询个人信息*/
    User userInfo(String name) throws SQLException;
    /*修改个人信息*/
    void changeUserInfo(User user) throws SQLException;
    /*查找所有用户*/
    List<User> showAllUser() throws SQLException;
}
