/**
 * @program: User
 * * @description: 测试
 * * @author:cro
 * * @create: 2019-05-06 23:16
 **/

package com.szm.chat.gui;

import com.szm.chat.entity.Message;
import com.szm.chat.entity.User;
import com.szm.chat.service.IMessageService;
import com.szm.chat.service.IUserService;
import com.szm.chat.service.MessageService;
import com.szm.chat.service.UserService;

import java.sql.SQLException;
import java.sql.Timestamp;

import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.date;

public class Test {
    public static void main(String[] args) throws SQLException {
//        IUserService userService=new UserService();
        /*创建用户*/
//        User user=new User(2,"ceshi","12345","nickname","mail",1,1);
//        userService.register(user);
//        /*获取密码*/
//        System.out.println(userService.login(1));
//        /*获取用户信息*/
//        System.out.println(userService.userInfo(2));
//        /*更改用户信息*/
//        User user=new User(2,"ceshi","45678","nickname","mail",1,1);
//        userService.changeUserInfo(user);
//
        IMessageService messageService=new MessageService();

//        Timestamp d = new Timestamp(System.currentTimeMillis());
//        System.out.println(d);
//        Message message=new Message(1,2,"nihao", d);
//        //发送消息
//        messageService.sentMessage(message);
//        Message msg=messageService.takeMessage(2);
//        System.out.println(msg);
    }
}
