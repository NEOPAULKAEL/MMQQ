/**
 * @program: User
 * * @description: 在线用户 业务 接口
 * * @author:cro
 * * @create: 2019-05-06 17:06
 **/

package com.szm.chat.service;

import com.szm.chat.entity.OnLineUser;
import com.szm.chat.entity.User;

import java.net.Socket;
import java.sql.SQLException;
import java.util.List;

public interface IOnLineUserService{

    //查询在线用户
    List<OnLineUser> onlineUserInfo()throws SQLException;

    /*上线*/
    void userOnline(User user, Socket s) throws SQLException;

    /*用户下线*/
    void userOffline(String username) throws SQLException;
}
