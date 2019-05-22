package com.szm.chat.gui;


import com.szm.chat.thread.Client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.sql.Timestamp;


/**
 * @author SZM
 */
public class PersonChat extends JFrame implements ActionListener {
    /**消息输出框*/
    private JTextArea messageOutPut;
    /**发送文件按钮*/
    private JButton sendText;
    /**聊天记录按钮*/
    private JButton historyMessage;
    /**消息输入框*/
    private JTextArea messageInPut;
    /**发送按钮*/
    private JButton send;
    /**退出按钮*/
    private JButton exit;
    private Client client;
    private String selected;
    private String username;


    /**构造1*/
    public PersonChat(String selected,String username,String msg,Timestamp d,Client client){
        this.client=client;
        this.username=username;
        this.selected=selected;
        //设置JFrame宽高
        this.setSize(450, 450);
        //设置JFrame的标题
        this.setTitle("和"+selected+"之间的对话-----当前用户："+username);
        //设置关闭退出程序
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        //设置不可调整窗口大小
        this.setResizable(false);
        //设置打开位置
        this.setLocation(600, 450);
        //添加面板
        JPanel panel = new JPanel();
        panel.setSize(450,450);
        this.add(panel);

        //添加消息输出
        messageOutPut = new JTextArea(10,25);
        messageOutPut.append(selected+"对你说：       "+"接收时间："+d+"\n");
        messageOutPut.append(msg+"\n");
        //传入文本域对象
        JScrollPane aJ=new JScrollPane(messageOutPut){
            @Override
            public Dimension getPreferredSize(){
                return  new Dimension(430,200);
            }
        };
        panel.add(aJ);
        //添加发送文件按钮
        sendText=new JButton("发送文件");
        sendText.setBounds(10,200,30,10);
        panel.add(sendText);
        //添加消息记录按钮
        historyMessage=new JButton("消息记录");
        historyMessage.setBounds(50,200,30,10);
        panel.add(historyMessage);
        //添加消息输入区
        messageInPut=new JTextArea(5,25){
            @Override
            public Dimension getPreferredSize(){
                return  new Dimension(430,140);
            }
        };
        panel.add(messageInPut);
        //添加发送退出按钮
        send=new JButton("发送");
        send.addActionListener(this);
        exit=new JButton("退出");
        exit.addActionListener(this);
        panel.add(send);
        panel.add(exit);
        this.setVisible(true);
    }

    //构造2
    public PersonChat(String username,String selected,Client client){
        this.username=username;
        this.selected=selected;
        this.client=client;
        //设置JFrame宽高
        this.setSize(450, 450);
        //设置JFrame的标题
        this.setTitle("你和"+selected+"之间的对话      当前用户："+username);
        //设置关闭退出程序
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        //设置不可调整窗口大小
        this.setResizable(false);
        //设置打开位置
        this.setLocation(600, 450);
        //添加面板
        JPanel panel = new JPanel();
        this.add(panel);

        //添加消息输出
        messageOutPut = new JTextArea(10,25);
        //传入文本域对象
        JScrollPane aJ=new JScrollPane(messageOutPut){
            @Override
            public Dimension getPreferredSize(){
                return  new Dimension(430,200);
            }
        };
        panel.add(aJ);
        //添加发送文件按钮
        sendText=new JButton("发送文件");
        sendText.setBounds(10,0,30,10);
        panel.add(sendText);
        //添加消息记录按钮
        historyMessage=new JButton("消息记录");
        historyMessage.setBounds(50,0,30,10);
        historyMessage.addActionListener(this);
        panel.add(historyMessage);
        //添加消息输入区
        messageInPut=new JTextArea(5,25){
            @Override
            public Dimension getPreferredSize(){
                return  new Dimension(430,140);
            }
        };
        panel.add(messageInPut);
        //添加发送退出按钮
        send=new JButton("发送");
        send.addActionListener(this);
        historyMessage.addActionListener(this);
        exit=new JButton("退出");
        exit.addActionListener(this);
        panel.add(send);
        panel.add(exit);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==send){
            String  a=messageInPut.getText();
            if (a.length()==0){
                JOptionPane.showMessageDialog(this, "不能发送空消息", "失败", JOptionPane.ERROR_MESSAGE);
            }
            Timestamp d = new Timestamp(System.currentTimeMillis());
            /*将消息发送给服务器*/
            client.getChatClient().setMsg("private",selected,a,username);
            client.getChatClient().sendMSG();
            messageOutPut.append("你说：        发送时间"+ d+"\n");
            messageOutPut.append(a+"\n");
            messageInPut.setText("");
        }
        if (e.getSource()==historyMessage){
            System.out.println("history");
            client.getChatClient().requestHistoryMsg(username,selected);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
            String[] historyMsg=client.getGetMSGClient().getHistoryMsg();
            new HistoryMessage_JFrame(client,historyMsg);
        }
        if (e.getSource()==exit){
            this.setVisible(false);
            dispose();
        }
    }

//    public static void main(String[] args) {
//        new PersonChat("11","33","hh",new Timestamp(System.currentTimeMillis()));
//    }
}
