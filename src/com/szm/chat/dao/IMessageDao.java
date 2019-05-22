/**
 * @program: User
 * * @description: 消息 数据操作对象 接口
 * * @author:cro
 * * @create: 2019-05-06 17:42
 **/

package com.szm.chat.dao;

import com.szm.chat.entity.Message;

import java.sql.Connection;
import java.sql.SQLException;

public interface IMessageDao {
    /*Dao层接收业务层的对象开启连接*/
    public void setConnection(Connection con);
    //添加消息
    void addMessage(Message message) throws SQLException;
    //查询消息,根据sent_id
    Message getMessage(int sent_id) throws SQLException;
}
