/**
 * @program: User
 * * @description: 新消息提醒
 * * @author:cro
 * * @create: 2019-05-13 11:11
 **/

package com.szm.chat.gui;

import com.szm.chat.thread.Client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Timestamp;

public class NotificationBoard extends JFrame implements ActionListener {

    private JLabel jlb1;
    private JLabel jlb2;
    private JButton jbt1;
    private String sendfrom;
    private String sendto;
    private String msg;
    private Timestamp d;
    private Client client;

    public JLabel getJlb1() {
        return jlb1;
    }

    //构造
    /**sendfrom-当前用户 sendto-发送消息来得用户*/
    public NotificationBoard(String sendfrom, String sendto, String msg, Timestamp d, Client client){
        this.client=client;
        this.d=d;
        this.msg=msg;
        this.sendfrom=sendfrom;
        this.sendto=sendto;
        //主窗体
        this.setTitle("New Message");
        //主窗体设置大小
        this.setSize(250,200);
        //主窗体设置位置
        this.setLocation(1500,850);
        //主窗体中的组件设置为绝对定位
        this.setLayout(null);
        //窗体大小不可变化
        this.setResizable(false);
        //关闭窗体的时候，退出程序
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        //文字组件
        jlb1 = new JLabel(sendfrom+"您有一条来自");
        jlb1.setBounds(0,20,300,30);
        jlb2 = new JLabel(sendto+"的新消息！");
        jlb2.setBounds(0,50,300,30);
        Font font = new Font("宋体",Font.BOLD,15);
        jlb1.setFont(font);
        jlb2.setFont(font);
        jbt1 = new JButton("点击查看");
        jbt1.setBounds(75,100,100,40);
        jbt1.addActionListener(this);
        this.add(jlb1);
        this.add(jlb2);
        this.add(jbt1);

        //让窗体变得可见，放在最后
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==jbt1){
            new PersonChat(sendto,sendfrom,msg,d,client);
            setVisible(false);
            dispose();
        }
    }

//    public static void main(String[] args) {
//        new NotificationBoard("11","22","hh");
//    }
}
