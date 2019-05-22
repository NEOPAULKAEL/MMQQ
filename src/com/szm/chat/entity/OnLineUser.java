/**
 * @program: User
 * * @description: 在线用户 实体
 * * @author:cro
 * * @create: 2019-05-06 16:58
 **/

package com.szm.chat.entity;

import com.szm.chat.dao.IOnLineUserDao;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;

public class OnLineUser extends User {
    /*用户序号-表中序号-对应表中id*/
    private int tableid;

    /*用户id-对应表中userid*/
    private int id;

    //登录用户
    private String username;
    //登录ip
    private int ip;
    //登陆时间
    private Timestamp time;
    //登录状态
    private Boolean state;

    /*表中序号自动增加*/
    private int autoAddTableid(){
        tableid=++tableid;
        return tableid;
    }

    //构造函数
    public OnLineUser(int id, String username, int ip, Timestamp time, Boolean state) {
        this.tableid = autoAddTableid();
        this.id = id;
        this.username = username;
        this.ip = ip;
        this.time = time;
        this.state = state;
    }

    //无参构造
    public OnLineUser(){}

    /**
     * 访问器设定
     */
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getIp() {
        return ip;
    }

    public void setIp(int ip) {
        this.ip = ip;
    }

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    public Boolean getState() {
        return state;
    }

    public void setState(Boolean state) {
        this.state = state;
    }

    public int getTableid() {
        return tableid;
    }
    
}
