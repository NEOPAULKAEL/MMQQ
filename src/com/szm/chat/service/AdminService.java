/**
 * @program: User
 * * @description: 管理员用户 实现类
 * * @author:cro
 * * @create: 2019-05-06 17:10
 **/

package com.szm.chat.service;

import com.szm.chat.dao.AdminDao;
import com.szm.chat.dao.IAdminDao;
import com.szm.chat.entity.User;
import com.szm.chat.util.JDBCUtil;

import java.sql.Connection;
import java.sql.SQLException;

//管理员用户-继承与实现(继承用户，实现管理员接口)
public class AdminService extends UserService implements IAdminService {

    @Override
    //删除用户
    public void deleteUsers(User user) throws SQLException {
        Connection con = null;
        try{
            con = JDBCUtil.getConnection();
            con.setAutoCommit(false);
            IAdminDao adminDao = new AdminDao();
            adminDao.setConnection(con);
            adminDao.deleteUser(user);
            con.commit();
        }catch (SQLException e){
            con.rollback();
            e.printStackTrace();
        }finally {
            JDBCUtil.release(con,null,null);
        }
    }

    @Override
    //授予权限
    public void AuthorizationManagement(User user) throws SQLException {
        Connection con = null;
        try{
            con = JDBCUtil.getConnection();
            con.setAutoCommit(false);
            IAdminDao adminDao = new AdminDao();
            adminDao.setConnection(con);
            adminDao.updateUserPower(user);
            con.commit();
        }catch (SQLException e){
            con.rollback();
            e.printStackTrace();
        }finally {
            JDBCUtil.release(con,null,null);
        }
    }

    @Override
    //查询所有用户
    public User allUserInfo(int id) throws SQLException {
        Connection con = null;
        User user = new User();
        try {
            con = JDBCUtil.getConnection();
            con.setAutoCommit(false);
            IAdminDao adminDao = new AdminDao();
            adminDao.setConnection(con);
            adminDao.selectUser(id);
            con.commit();
        }catch (SQLException e){
            con.rollback();
            e.printStackTrace();
        }finally {
            JDBCUtil.release(con,null,null);
        }
        return user;
    }
}
