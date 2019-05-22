/**
 * @program: User
 * * @description: 历史消息记录 dao 实现类
 * * @author:cro
 * * @create: 2019-05-06 17:50
 **/

package com.szm.chat.dao;

import com.szm.chat.entity.HistoryMSG;
import com.szm.chat.entity.Message;
import com.szm.chat.entity.User;
import com.szm.chat.util.JDBCUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class HistoryMSGDao extends Message implements IHistoryMSGDao {
    Connection con=null;
    //设置连接
    @Override
    public void setConnection(Connection con){
        this.con=con;
    }
    @Override
    public List<HistoryMSG> selectHistoryMSG(String namefrom,String nameto) throws SQLException {
        List<HistoryMSG> historyMSGList=new ArrayList<>();
        String sql="select namefrom,nameto,msg,datetime from T_HISTORY where (namefrom in (?,?)) and (nameto in (?,?)) order by datetime";
        PreparedStatement pstmt=con.prepareStatement(sql);
        pstmt.setString(1,namefrom);
        pstmt.setString(2,nameto);
        pstmt.setString(3,namefrom);
        pstmt.setString(4,nameto);
        ResultSet rs=pstmt.executeQuery();
        while (rs.next()){
            HistoryMSG historyMSG=new HistoryMSG();
            historyMSG.setNamefrom(rs.getString(1));
            historyMSG.setNameto(rs.getString(2));
            historyMSG.setMsg(rs.getString(3));
            historyMSG.setSentTime(rs.getTimestamp(4));
            historyMSGList.add(historyMSG);
        }
        JDBCUtil.release(null,pstmt,null);
        return historyMSGList;
    }

    @Override
    /*msgid namefrom nameto msg datetime*//*message-- sentid getid msg sentdate*/
    public void addHistoryMSG(String sentUsername,String getUsername, Message message) throws SQLException {
//        Timestamp d = new Timestamp(System.currentTimeMillis());
        String sql="insert into T_HISTORY values(SEQ1.nextval,?,?,?,?)";
        PreparedStatement pstmt=con.prepareStatement(sql);

        pstmt.setString(1,sentUsername);
        pstmt.setString(2,getUsername);
        pstmt.setString(3,message.getMsg());
        pstmt.setTimestamp(4,message.getSentTime());
        pstmt.executeUpdate();
        JDBCUtil.release(null,pstmt,null);
    }
}
