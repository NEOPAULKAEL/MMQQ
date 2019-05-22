/**
 * @program: User
 * * @description: 登陆后进入到聊天室中 客户端
 * * @author:cro
 * * @create: 2019-05-07 23:40
 **/

package com.szm.chat.thread;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class ChatClient implements Runnable{
    private BufferedReader in;
    private PrintWriter out;
    private String username;
    private String towho;
    private String flag;
    private String message;
    private String msg;
    //getter setter
    public String getTowho() {
        return towho;
    }

    public void setTowho(String towho) {
        this.towho = towho;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String flag,String towho,String message,String username) {
        msg=flag+"#"+username+"#"+towho+"#"+message;
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
    public void privateChat(String flag,String towho,String message){
        this.flag=flag;
        this.towho=towho;
        this.message=message;
    }

    /**
     * 发送消息
     */
    public synchronized void sendMSG(){
        out.println(msg);
        out.flush();
        System.out.println("I said:"+msg);

    }
    public void getUserInfo(){
        out.println("thisuserinfo");
        out.flush();
    }
    public void refreshOnlineuser(){
        out.println("refresh");
        out.flush();
    }
    public void requestHistoryMsg(String name1,String name2){
        out.println("history"+"#"+name1+"#"+name2);
        out.flush();
    }
    public void sendnewUserInfo(String newInfo){
        out.println(newInfo);
        out.flush();

    }

    @Override
    public void run() {
        System.out.println("send thread");
        while (true){
            if (msg!=null && msg.length()!=0){
                sendMSG();
            }
        }
    }


}
