/**
 * @program: User
 * * @description: 接收消息 线程
 * * @author:cro
 * * @create: 2019-05-08 14:09
 **/

package com.szm.chat.thread;

import com.szm.chat.gui.NotificationBoard;
import com.szm.chat.util.UnpackString;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.Scanner;

public class GetMSGClient implements Runnable{
    private BufferedReader in;
    private PrintWriter out;
    String username;
    private String[] onlineuser;
    private String[] historyMsg;
    private Client client;
    private String[] infos;

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public String[] getHistoryMsg() {
        return historyMsg;
    }

    public String[] getOnlineuser() {
        return onlineuser;
    }

    /*初始化*/
    public void init(BufferedReader in,PrintWriter out,String username) {

        //获得登陆时创建的socket对应输入、输出流
        this.in = in;
        this.out = out;
        this.username = username;
        Thread th = new Thread(this);
        th.start();

    }
    /**
     * 接收消息
     */
    String inmsg;
    public synchronized void getMSG(){
        try {
            inmsg=in.readLine();
            System.out.println(inmsg+"getMSG");
            if (inmsg.startsWith("OnlineUser")){
                updateOnlineUser();
            }
            if (inmsg.startsWith("history")){
                updateHistoryMsg();
            }
            if (inmsg.startsWith("infos")){
                getInfos();
            }
            if (inmsg!=null && (inmsg.startsWith("private") || inmsg.startsWith("public"))){
                UnpackString unpack=new UnpackString(inmsg);
                if (unpack.getSendUsername().equals(username)){
                    if (unpack.getMessage().equals("failed")){
                        System.out.println("发送失败");
                    }else {
                        System.out.println("你对"+unpack.getGetUsername()+"说："+unpack.getMessage());
                    }
                }else {
                    System.out.println(unpack.getSendUsername()+"对你说："+unpack.getMessage());
                    Timestamp d=new Timestamp(System.currentTimeMillis());
                    new NotificationBoard(username,unpack.getSendUsername(),unpack.getMessage(),d,client);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            /*connection reset异常*/
        }

    }
    public String[] updateOnlineUser(){
        onlineuser=inmsg.split("#");
        for (int i=0;i<onlineuser.length;i++){
            System.out.println(onlineuser[i]);
        }
        System.out.println(inmsg+"save online");
        return onlineuser;
    }
    public String[] updateHistoryMsg(){
        historyMsg=inmsg.split("#");
        System.out.println(historyMsg[0]);
        return historyMsg;
    }
    public String[] getInfos(){
        infos=inmsg.split("#");
        return infos;
    }

    @Override
    /*定义发送接收消息*/
    public void run() {
        System.out.println("接收线程已加入");
        while (true){
            getMSG();
        }
    }
}
