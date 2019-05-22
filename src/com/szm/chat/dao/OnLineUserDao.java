/**
 * @program: User
 * * @description: 在线用户 dao 实现类
 * * @
 * * @create: 2019-05-06 17:44
 **/

package com.szm.chat.dao;

import com.szm.chat.entity.OnLineUser;
import com.szm.chat.entity.User;
import com.szm.chat.util.JDBCUtil;

import java.net.InetAddress;
import java.net.Socket;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OnLineUserDao implements IOnLineUserDao {

    Connection con = null;

    @Override
    public void setConnection(Connection con) {
        this.con = con;
    }

    @Override
    //添加在线用户记录
    public void insertOnlineUser(User user, Socket s) throws SQLException {
        InetAddress addr=s.getInetAddress();
        String ip=addr.toString();
        Timestamp d = new Timestamp(System.currentTimeMillis());
        String sql= "insert into T_ONLINEUSER values(SEQ3.nextval,?,?,?,1)";
        PreparedStatement pst = con.prepareStatement(sql);
        pst.setInt(1,user.getId());
        pst.setString(2,ip);
        pst.setTimestamp(3,d);
        pst.executeUpdate();
        JDBCUtil.release(null,pst,null);
    }

    @Override
    //查看在线用户
    public List<OnLineUser> selectOnlineUser()throws SQLException {

        String sql ="select o.user_id,u.name,u.nickname from T_ONLINEUSER o,T_USER u where o.user_id=u.id and state=1 group by o.user_id,u.name,u.nickname";
        PreparedStatement pst = con.prepareStatement(sql);
        ResultSet rs = pst.executeQuery();
        OnLineUser onLineUser;
        List<OnLineUser> list=new ArrayList<OnLineUser>();
        while (rs.next()){
            onLineUser = new OnLineUser();
            onLineUser.setId(rs.getInt(1));
            onLineUser.setUsername(rs.getString(2));
            onLineUser.setNickname(rs.getString(3));
            list.add(onLineUser);
        }
        JDBCUtil.release(null,pst,null);
        return list;
    }
    /*用户下线*/
    @Override
    public void updateState(String username) throws SQLException {
        String sql="update T_ONLINEUSER set state=0 where user_id=(select id from T_USER where name=?)";
        PreparedStatement pst = con.prepareStatement(sql);
        pst.setString(1,username);
        pst.executeUpdate();
        JDBCUtil.release(null,pst,null);
    }
}
