/**
 * @program: User
 * * @description: 消息 实现类
 * * @author:cro
 * * @create: 2019-05-06 17:17
 **/

package com.szm.chat.service;

import com.szm.chat.dao.IMessageDao;
import com.szm.chat.dao.MessageDao;
import com.szm.chat.entity.HistoryMSG;
import com.szm.chat.entity.Message;
import com.szm.chat.util.JDBCUtil;

import java.sql.Connection;
import java.sql.SQLException;

public class MessageService implements IMessageService {
    //发消息
    @Override
    public void sentMessage(Message message) throws SQLException {
        Connection con=null;
        try {
            con= JDBCUtil.getConnection();
            con.setAutoCommit(false);
            IMessageDao messageDao=new MessageDao();
            messageDao.setConnection(con);
            messageDao.addMessage(message);
            con.commit();

        } catch (SQLException e) {
            con.rollback();
            e.printStackTrace();
        }finally {
            JDBCUtil.release(con,null,null);
        }
    }
    //接收消息
    @Override
    public Message takeMessage(int sent_id) throws SQLException {
        Connection con=null;
        Message message=new Message();
        try {
            con= JDBCUtil.getConnection();
            con.setAutoCommit(false);
            IMessageDao messageDao=new MessageDao();
            messageDao.setConnection(con);
            message=messageDao.getMessage(sent_id);
            con.commit();
        } catch (SQLException e) {
            con.rollback();
            e.printStackTrace();
        }finally {
            JDBCUtil.release(con,null,null);
        }
        return message;
    }
}
