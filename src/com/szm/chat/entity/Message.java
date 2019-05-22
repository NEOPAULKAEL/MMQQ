
/**
 * @program: User
 * * @description: 消息 实体
 * * @author:cro
 * * @create: 2019-05-06 16:58
 **/

        package com.szm.chat.entity;

import java.sql.Timestamp;

public class Message {
    //短信id
    private static int msgid=10000;
    //短信发送者
    private int sentid;
    //短信接收者
    private int getid;
    /*信息*/
    private String msg;
    /*发送时间*/
    private Timestamp sentTime;
    /*auto add msgid*/
    private int autoAddMsgid(){
        msgid=++msgid;
        return msgid;
    }
    //构造
    public Message(int sentid, int getid, String msg, Timestamp sentTime) {
        this.msgid = msgid;
        this.sentid = sentid;
        this.getid = getid;
        this.msg = msg;
        this.sentTime = sentTime;
    }
    //无参构造
    public Message() {
    }

    //getter setter
    public int getId() {
        return msgid;
    }

    public void setId(int id) {
        this.msgid = id;
    }

    public int getsentid() {
        return sentid;
    }

    public void setsentid(int sentid) {
        this.sentid = sentid;
    }

    public int getgetid() {
        return getid;
    }

    public void setgetid(int getid) {
        this.getid = getid;
    }

    public String  getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Timestamp getSentTime() {
        return sentTime;
    }

    public void setSentTime(Timestamp sentTime) {
        this.sentTime = sentTime;
    }

    @Override
    public String toString() {
        return "Message{" +
                "msgid=" + msgid +
                ", sentid=" + sentid +
                ", getid=" + getid +
                ", msg='" + msg + '\'' +
                ", sentTime=" + sentTime +
                '}';
    }
}
