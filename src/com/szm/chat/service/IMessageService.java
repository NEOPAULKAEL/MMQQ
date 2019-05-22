/**
 * @program: User
 * * @description: 消息 接口
 * * @author:cro
 * * @create: 2019-05-06 17:16
 **/

package com.szm.chat.service;

import com.szm.chat.entity.HistoryMSG;
import com.szm.chat.entity.Message;

import java.sql.SQLException;

public interface IMessageService {
    //发消息
    void sentMessage(Message message) throws SQLException;
    //接收消息,根据sent_id
    Message takeMessage(int sent_id) throws SQLException;
}
