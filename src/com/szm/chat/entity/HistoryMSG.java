/**
 * @program: User
 * * @description: 历史消息 实体
 * * @author:cro
 * * @create: 2019-05-06 17:02
 **/

package com.szm.chat.entity;

import java.sql.Timestamp;

public class HistoryMSG extends Message{
    //历史消息记录id
    private int hmsgid;
    private String namefrom;
    private String nameto;
    private String msg;
    private Timestamp datetime;
    /*自动增加历史记录id*/

    public HistoryMSG(int sentid, int getid, String msg, Timestamp sentTime, String namefrom, String nameto, String msg1, Timestamp datetime) {
        super(sentid, getid, msg, sentTime);
        this.namefrom = namefrom;
        this.nameto = nameto;
        this.msg = msg1;
        this.datetime = datetime;
    }

    public HistoryMSG() {

    }

    public int getHmsgid() {
        return hmsgid;
    }

    public void setHmsgid(int hmsgid) {
        this.hmsgid = hmsgid;
    }

    public String getNamefrom() {
        return namefrom;
    }

    public void setNamefrom(String namefrom) {
        this.namefrom = namefrom;
    }

    public String getNameto() {
        return nameto;
    }

    public void setNameto(String nameto) {
        this.nameto = nameto;
    }

    @Override
    public String getMsg() {
        return msg;
    }

    @Override
    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Timestamp getDatetime() {
        return datetime;
    }

    public void setDatetime(Timestamp datetime) {
        this.datetime = datetime;
    }

    @Override
    public String toString() {
        return "HistoryMSG{" +
                "hmsgid=" + hmsgid +
                ", namefrom='" + namefrom + '\'' +
                ", nameto='" + nameto + '\'' +
                ", msg='" + msg + '\'' +
                ", datetime=" + datetime +
                '}';
    }
}
