/**
 * @program: User
 * * @description: 在线用户 dao 接口
 * * @author:cro
 * * @create: 2019-05-06 17:44
 **/

package com.szm.chat.dao;

import com.szm.chat.entity.OnLineUser;
import com.szm.chat.entity.User;

import java.net.Socket;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface IOnLineUserDao {
    //Dao层和业务层对象链接
    public void setConnection(Connection con);
    //记录在线用户
    void insertOnlineUser(User user, Socket s) throws SQLException;
    //查看在线用户
    List<OnLineUser> selectOnlineUser() throws SQLException;
    //修改用户在线状态为0
    void updateState(String username) throws SQLException;
}
