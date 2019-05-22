/**
 * @program: User
 * * @description: 历史消息 实现类
 * * @author:cro
 * * @create: 2019-05-06 17:18
 **/

package com.szm.chat.service;

import com.szm.chat.dao.HistoryMSGDao;
import com.szm.chat.dao.IHistoryMSGDao;
import com.szm.chat.dao.IUserDao;
import com.szm.chat.dao.UserDao;
import com.szm.chat.entity.HistoryMSG;
import com.szm.chat.entity.Message;
import com.szm.chat.util.JDBCUtil;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class HistoryMSGService implements IHistoryMSGService {
    @Override
    public  List<HistoryMSG> showHistoryMSG(String namefrom, String nameto) throws SQLException {
        Connection con=null;
        List<HistoryMSG> historyMSGList=null;
        try {
            con= JDBCUtil.getConnection();
            con.setAutoCommit(false);
            IHistoryMSGDao hisdao=new HistoryMSGDao();
            hisdao.setConnection(con);
            historyMSGList=hisdao.selectHistoryMSG(namefrom,nameto);
            con.commit();
        } catch (SQLException e) {
            con.rollback();
            e.printStackTrace();
        }finally {
            JDBCUtil.release(con,null,null);
        }
        return historyMSGList;
    }

    @Override
    public void writeHistoryMSG(String sentUsername,String getUsername, Message message) throws SQLException {
        Connection con=null;
        try {
            con= JDBCUtil.getConnection();
            con.setAutoCommit(false);
            IHistoryMSGDao hisdao=new HistoryMSGDao();
            hisdao.setConnection(con);
            hisdao.addHistoryMSG(sentUsername,getUsername,message);
            con.commit();

        } catch (SQLException e) {
            con.rollback();
            e.printStackTrace();
        }finally {
            JDBCUtil.release(con,null,null);
        }
    }
}
