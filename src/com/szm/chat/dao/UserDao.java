/**
 * @program: User
 * * @description: 用户 数据操作对象 实现类
 * * @author:cro
 * * @create: 2019-05-06 17:39
 **/

package com.szm.chat.dao;

import com.szm.chat.entity.User;
import com.szm.chat.util.JDBCUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

//T_user字段 id name password nickname mail sex power

public class UserDao implements IUserDao {
    Connection con=null;
    //设置连接
    @Override
    public void setConnection(Connection con){
        this.con=con;
    }
    @Override
    //添加用户
    public void addUser(User user) throws SQLException {
        String sql="insert into T_USER values(SEQ_4.NEXTVAL,?,?,?,?,?,?)";
        PreparedStatement pstmt=con.prepareStatement(sql);
        pstmt.setString(1,user.getUsername());
        pstmt.setString(2,user.getPassword());
        pstmt.setString(3,user.getNickname());
        pstmt.setString(4,user.getEmail());
        pstmt.setInt(5,user.getSex());
        pstmt.setInt(6,user.getPower());
        pstmt.executeUpdate();
        JDBCUtil.release(null,pstmt,null);
    }



    @Override
    //获取用户登录密码
    public User getPassword(String name,String password) throws SQLException {
        String sql="select id,name,password,nickname,mail,sex,power from T_USER where name=? and password=?";
        PreparedStatement pstmt=con.prepareStatement(sql);
        pstmt.setString(1,name);
        pstmt.setString(2,password);
        ResultSet rs=pstmt.executeQuery();
        User user=null;
        while (rs.next()){
            user=new User();
            user.setId(rs.getInt(1));
            user.setUsername(rs.getString(2));
            user.setPassword(rs.getString(3));
            user.setNickname(rs.getString(4));
            user.setEmail(rs.getString(5));
            user.setSex(rs.getInt(6));
            user.setPower(rs.getInt(7));
        }
        JDBCUtil.release(null,pstmt,null);
        return user;
    }


    /**
     * 根据用户名选择相应用户
     * @param name
     * @return User
     * @throws SQLException
     */
    @Override
    public User selectUser(String name) throws SQLException {
        String sql="select id,name,password,nickname,mail,sex,power from T_USER where name=?";
        PreparedStatement pstmt=con.prepareStatement(sql);
        pstmt.setString(1,name);
        ResultSet rs=pstmt.executeQuery();
        User user=null;
        while (rs.next()){
            user=new User();
            user.setId(rs.getInt(1));
            user.setUsername(rs.getString(2));
            user.setPassword(rs.getString(3));
            user.setNickname(rs.getString(4));
            user.setEmail(rs.getString(5));
            user.setSex(rs.getInt(6));
            user.setPower(rs.getInt(7));
        }
        JDBCUtil.release(null,pstmt,null);
        return user;
    }

    /**
     *
     * @param user
     * @throws SQLException
     */
    @Override
    public void updateUser(User user) throws SQLException {
        String sql="update T_USER set name=?,password=?,nickname=?,mail=?,sex=? where id=?";
        PreparedStatement pstmt=con.prepareStatement(sql);
        pstmt.setInt(6,user.getId());
        pstmt.setString(1, user.getUsername());
        pstmt.setString(2, user.getPassword());
        pstmt.setString(3,user.getNickname());
        pstmt.setString(4,user.getEmail());
        pstmt.setInt(5,user.getSex());
        int i=pstmt.executeUpdate();
        JDBCUtil.release(null,pstmt,null);



    }

    @Override
    public List<User> selectAllUser() throws SQLException {
        String sql="select id,name,password,nickname,mail,sex,power from T_USER";
        PreparedStatement pstmt=con.prepareStatement(sql);
        ResultSet rs=pstmt.executeQuery();
        User user=null;
        List<User> list=new ArrayList<>();
        while (rs.next()){
            user=new User();
            user.setId(rs.getInt(1));
            user.setUsername(rs.getString(2));
            user.setPassword(rs.getString(3));
            user.setNickname(rs.getString(4));
            user.setEmail(rs.getString(5));
            user.setSex(rs.getInt(6));
            user.setPower(rs.getInt(7));
            list.add(user);
        }
        JDBCUtil.release(null,pstmt,null);
        return list;
    }
}
