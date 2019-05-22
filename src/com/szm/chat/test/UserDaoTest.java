package com.szm.chat.test;

import com.szm.chat.entity.User;
import com.szm.chat.service.IUserService;
import com.szm.chat.service.UserService;
import org.junit.Test;

import java.sql.SQLException;

public class UserDaoTest {

    @Test
    public void setConnection() {
    }

    @Test
    public void addUser() throws SQLException {
        IUserService userService=new UserService();
        /*创建用户*/
//        User user=new User(3,"ceshi2","12345","nickname","mail",1,1);
//        userService.register(user);
    }

    @Test
    public void getPassword() {
    }

    @Test
    public void selectUser() {
    }

    @Test
    public void updateUser() {
    }
}