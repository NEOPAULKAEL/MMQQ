package com.szm.chat.test;

import com.szm.chat.entity.Message;
import com.szm.chat.service.IMessageService;
import com.szm.chat.service.MessageService;
import org.junit.Test;

import javax.swing.*;
import java.sql.SQLException;
import java.sql.Timestamp;

public class MessageServiceTest {

    @Test
    public void sentMessage() throws SQLException {
        IMessageService messageService=new MessageService();

        Timestamp d = new Timestamp(System.currentTimeMillis());
        System.out.println(d);
        Message message=new Message(1,2,"nihao", d);
        //发送消息
        messageService.sentMessage(message);
    }

    @Test
    public void takeMessage() {
    }

}