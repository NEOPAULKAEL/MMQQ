/**
 * @program: User
 * * @description: 管理员用户 接口
 * * @author:cro
 * * @create: 2019-05-06 17:08
 **/

package com.szm.chat.service;

import com.szm.chat.entity.User;

import java.sql.SQLException;

public interface IAdminService{

    //删除用户
    void deleteUsers(User user)throws SQLException;

    //授权普通用户
    void AuthorizationManagement (User user) throws SQLException;

    //查看所有用户
    User allUserInfo(int id)throws SQLException;
}
