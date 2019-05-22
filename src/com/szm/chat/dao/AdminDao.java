package com.szm.chat.dao;

import com.szm.chat.entity.OnLineUser;
import com.szm.chat.entity.User;
import com.szm.chat.util.JDBCUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 管理员用户权限
 *  * @author Vector_Wu
 *  * @version 1.0
 */

public class AdminDao implements IAdminDao {
    Connection con = null;

    public void setConnection(Connection con) {
        this.con = con;
    }

    @Override
    public void deleteUser(User user) throws SQLException {
        String sql ="select from T_USER where id=?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(0,1);
        JDBCUtil.release(null,ps,null);
    }

    @Override
    public void updateUserPower(User user) throws SQLException {
        String sql = "update T_USER set power=0 where id=?";
        PreparedStatement pst = con.prepareStatement(sql);
        pst.setInt(1,1);
        JDBCUtil.release(null,pst,null);
    }

    @Override
    public User selectUser(int id) throws SQLException {
        String sql ="select id,sex,mail from T_USER";
        PreparedStatement prepa = con.prepareStatement(sql);
        ResultSet rs = prepa.executeQuery();
        User admin = null;
        while (rs.next()){
            admin = new User();
            admin.setId(rs.getInt(1));
            admin.setSex(rs.getInt(2));
            admin.setEmail(rs.getString(3));
        }
        JDBCUtil.release(null,prepa,null);
        return admin;
    }
}
