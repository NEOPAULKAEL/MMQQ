/**
 * @program: User
 * * @description: 在线用户 线程 弃用
 * * @author:cro
 * * @create: 2019-05-10 10:46
 **/
//
//package com.szm.chat.thread;
//
//import com.szm.chat.gui.TheMainInterfaceAssembly;
//
//import javax.swing.*;
//import java.awt.*;
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.PrintWriter;
//
//public class OnlineUserClient extends JList implements Runnable {
//    private BufferedReader in;
//    private PrintWriter out;
//    private String username;
//    private static String[] onlineUser;
//    private static JList<String> list2;
//    private static DefaultListModel jlistmodule;
//
//    public static JList<String> getList2() {
//        return list2;
//    }
//
//    public OnlineUserClient() {
//        jlistmodule=new DefaultListModel();
//        list2=new JList<>(jlistmodule);
//        jlistmodule.addElement(getOnlineUser());
//    }
//
//    public String[] getOnlineUser() {
//        return onlineUser;
//    }
//
//    public void setOnlineUser(String[] onlineUser) {
//        this.onlineUser = onlineUser;
//    }
//
//    /*初始化*/
//    public void init(BufferedReader in,PrintWriter out,String username) {
//
//        //获得登陆时创建的socket对应输入、输出流
//        this.in = in;
//        this.out = out;
//        this.username = username;
//        Thread th = new Thread(this);
//        th.start();
//    }
//    /*发送请求更新用户*/
//    public void refreshOnlineUser(){
//
//    }
//    /*查询所有用户*/
//    public void findAllUser(){
//
//    }
//    @Override
//    public void run() {
//        while (true){
//            synchronized (in){
//                try {
//                    out.println("refresh");
//                    out.flush();
//                    String inmsg=in.readLine();
//                    if (inmsg.startsWith("OnlineUser")){
//                        String[] infoArr=onlineUser=inmsg.split("#");
//                        Thread.sleep(1000);
//                        jlistmodule.clear();
//                        for (int i=1;i<infoArr.length;i++){
//                            String[] nickname=infoArr[i].split("&");
//                            jlistmodule.addElement(nickname[0]);
//                        }
//                    }
//
//
//                } catch (IOException e) {
//                    e.printStackTrace();
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//    }
//
//    @Override
//    public void paintComponents(Graphics g) {
//        super.paintComponents(g);
//    }
//}
