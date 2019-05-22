/**
 * @program: User
 * * @description: 用户 业务
 * * @author:cro
 * * @create: 2019-05-06 17:04
 **/

package com.szm.chat.service;

import com.szm.chat.dao.IUserDao;
import com.szm.chat.dao.UserDao;
import com.szm.chat.entity.User;
import com.szm.chat.util.JDBCUtil;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserService implements IUserService{

    @Override
    public void register(User user) throws SQLException {
        Connection con=null;
        try {
            con= JDBCUtil.getConnection();
            con.setAutoCommit(false);
            IUserDao userDao=new UserDao();
            userDao.setConnection(con);
            userDao.addUser(user);
            con.commit();
        } catch (SQLException e) {
            con.rollback();
            e.printStackTrace();
        }finally {
            JDBCUtil.release(con,null,null);
        }

    }


    @Override
    public User login(String name,String passwordinput) throws SQLException {
        Connection con=null;
        User user=null;
        try {
            con= JDBCUtil.getConnection();
            con.setAutoCommit(false);
            IUserDao userDao=new UserDao();
            userDao.setConnection(con);
            user=userDao.getPassword(name,passwordinput);
            con.commit();

        } catch (SQLException e) {
            con.rollback();
            e.printStackTrace();
        }finally {
            JDBCUtil.release(con,null,null);
        }
        return user;
    }

    @Override
    public User userInfo(String name) throws SQLException {
        Connection con=null;
        User user=new User();
        try {
            con= JDBCUtil.getConnection();
            con.setAutoCommit(false);
            IUserDao userDao=new UserDao();
            userDao.setConnection(con);
            user=userDao.selectUser(name);
            con.commit();
        } catch (SQLException e) {
            con.rollback();
            e.printStackTrace();
        }finally {
            JDBCUtil.release(con,null,null);
        }
        return user;
    }

    @Override
    public void changeUserInfo(User user) throws SQLException {
        Connection con=null;
        try {
            con= JDBCUtil.getConnection();
            con.setAutoCommit(false);
            IUserDao userDao=new UserDao();
            userDao.setConnection(con);
            userDao.updateUser(user);
            con.commit();
        } catch (SQLException e) {
            con.rollback();
            e.printStackTrace();
        }finally {
            JDBCUtil.release(con,null,null);
        }
    }

    @Override
    public List<User> showAllUser() throws SQLException {
        Connection con=null;
        List<User> list=new ArrayList<>();
        try {
            con= JDBCUtil.getConnection();
            con.setAutoCommit(false);
            IUserDao userDao=new UserDao();
            userDao.setConnection(con);
            list=userDao.selectAllUser();
            con.commit();
        } catch (SQLException e) {
            con.rollback();
            e.printStackTrace();
        }finally {
            JDBCUtil.release(con,null,null);
        }
        return list;
    }
}
