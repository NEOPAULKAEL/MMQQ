/**
 * @program: User
 * * @description: 在线用户 业务 实现类
 * * @author:cro
 * * @create: 2019-05-06 17:06
 * * @author:Vector
 * * @version: 1.1
 **/

package com.szm.chat.service;

import com.szm.chat.dao.IOnLineUserDao;
import com.szm.chat.dao.OnLineUserDao;
import com.szm.chat.entity.OnLineUser;
import com.szm.chat.entity.User;
import com.szm.chat.util.JDBCUtil;

import java.net.Socket;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**在线用户继承用户类，实现在线用户接口*/
public class OnLineUserService extends UserService implements IOnLineUserService{

    @Override
    /**查询在线用户*/
    public List<OnLineUser> onlineUserInfo() throws SQLException {
        Connection con = null;
        List list=null;
        try{
            con = JDBCUtil.getConnection();
            con.setAutoCommit(false);
            IOnLineUserDao onLineUserDao = new OnLineUserDao();
            onLineUserDao.setConnection(con);
            list = onLineUserDao.selectOnlineUser();
            con.commit();
        }catch (SQLException e){
            con.rollback();
            e.printStackTrace();
            System.out.println("目前无人在线");
        }finally {
            JDBCUtil.release(con,null,null);
        }
        return list;
    }

    /**用户上线，写入表中*/
    @Override
    public void userOnline(User user, Socket s) throws SQLException {
        Connection con = null;
        try{
            con = JDBCUtil.getConnection();
            con.setAutoCommit(false);
            IOnLineUserDao onLineUserDao = new OnLineUserDao();
            onLineUserDao.setConnection(con);
            onLineUserDao.insertOnlineUser(user,s);
            con.commit();
        }catch (SQLException e){
            con.rollback();
            e.printStackTrace();
        }finally {
            JDBCUtil.release(con,null,null);
        }
    }
    /**用户下线，修改表中state值*/
    @Override
    public void userOffline(String username) throws SQLException {
        Connection con = null;
        try{
            con = JDBCUtil.getConnection();
            con.setAutoCommit(false);
            IOnLineUserDao onLineUserDao = new OnLineUserDao();
            onLineUserDao.setConnection(con);
            onLineUserDao.updateState(username);
            con.commit();
        }catch (SQLException e){
            con.rollback();
            e.printStackTrace();
//            Caused by: oracle.net.ns.NetException: Listener refused the connection with the following error:
//            ORA-12519, TNS:no appropriate service handler found
        }finally {
            JDBCUtil.release(con,null,null);
        }
    }
}
