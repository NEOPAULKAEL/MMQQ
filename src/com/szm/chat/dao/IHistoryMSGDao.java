/**
 * @program: User
 * * @description: 历史消息记录 dao 接口
 * * @author:cro
 * * @create: 2019-05-06 17:46
 **/

package com.szm.chat.dao;

import com.szm.chat.entity.HistoryMSG;
import com.szm.chat.entity.Message;
import com.szm.chat.entity.User;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface IHistoryMSGDao {
    Connection con=null;
    //设置连接
    public void setConnection(Connection con);
    /*选择历史聊天记录*/
    public List<HistoryMSG> selectHistoryMSG(String namefrom, String nameto) throws SQLException;
    /*添加聊天记录*/
    public void addHistoryMSG(String sentUsername,String getUsername, Message message) throws SQLException;
}
