/**
 * @program: User
 * * @description: 消息 数据操作对象 实现类
 * * @author:cro
 * * @create: 2019-05-06 17:43
 **/

package com.szm.chat.dao;

import com.szm.chat.entity.Message;
import com.szm.chat.util.JDBCUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MessageDao implements IMessageDao {
    Connection con=null;
    //设置连接

    @Override
    public void setConnection(Connection con) {
       this.con=con;
    }
    //添加消息
    @Override
    public void addMessage(Message message) throws SQLException {
        String sql="insert into  T_Message values(SEQ2.nextval,?,?,?,?)";
        PreparedStatement pstmt=con.prepareStatement(sql);

        pstmt.setInt(1,message. getsentid());
        pstmt.setInt(2,message.getgetid());
        pstmt.setString(3,message.getMsg());
        pstmt.setTimestamp(4,message.getSentTime());
        pstmt.executeUpdate();
        JDBCUtil.release(null,pstmt,null);
    }
    //根据sent_id查询消息
    @Override
    public Message getMessage(int sent_id) throws SQLException {
        String sql="select get_id,send_id,message,Time from T_MESSAGE where send_id=?";
        PreparedStatement pstmt=con.prepareStatement(sql);
        pstmt.setInt(1,sent_id);
        ResultSet rs=pstmt.executeQuery();
        Message message=null;
        while (rs.next()){
           message=new Message();
           message.setgetid(rs.getInt(1));
           message.setsentid(rs.getInt(2));
           message.setMsg(rs.getString(3));
           message.setSentTime(rs.getTimestamp(4));
        }
        JDBCUtil.release(null,pstmt,null);
        return message;
    }
}
