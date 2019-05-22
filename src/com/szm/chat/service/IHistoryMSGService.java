/**
 * @program: User
 * * @description: 历史消息 接口
 * * @author:cro
 * * @create: 2019-05-06 17:18
 **/

package com.szm.chat.service;

import com.szm.chat.entity.HistoryMSG;
import com.szm.chat.entity.Message;

import java.sql.SQLException;
import java.util.List;

public interface IHistoryMSGService {
    /*查看历史聊天记录*/
    public List<HistoryMSG> showHistoryMSG(String namefrom, String nameto) throws SQLException;
    /*记录聊天记录*/
    public void writeHistoryMSG(String sentUsername,String getUsername, Message message) throws SQLException;
}
