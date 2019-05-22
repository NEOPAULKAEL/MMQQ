package com.szm.chat.gui;

import com.szm.chat.dao.MessageDao;
import com.szm.chat.thread.Client;
import com.szm.chat.thread.GetMSGClient;
//import com.szm.chat.thread.OnlineUserClient;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.HashSet;

import static java.awt.Color.BLUE;

public class GroupChat extends JFrame {
    //消息输出框
    private static JTextArea messageOutPut;
    //发送文件按钮
   private static JButton sendText;
    //聊天记录按钮
    private static JButton historyMessage;
    //消息输入框
    private static JTextArea messageInPut;
    //发送按钮
    private static JButton send;
    //退出按钮
    private static JButton exit;
    //选择用户复选框
    private static JCheckBox choseUser;
    //复选框标题
    private String user;
    //复选框JTextfiled
    private static JTextField checkbox;
    private Client client;
    private JList<String> list1;
    private String[] onlineusers;
    private HashSet<String> selectedUser;
    //构造
    public GroupChat(String[] onlineusers,String username,Client client){
        this.client=client;
        this.onlineusers=onlineusers;
        //设置JFrame宽高
        this.setSize(700, 500);
        //设置JFrame的标题
        this.setTitle("群发");
        //设置关闭退出程序
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        //设置不可调整窗口大小
        this.setResizable(false);
        //设置打开位置
        this.setLocation(600, 300);
        this.setLayout(null);

        //添加聊天信息输出面板
        JPanel outPanel = new JPanel();
//        outPanel.setBackground(Color.YELLOW);
        outPanel.setBounds(5,5,470,300);
        this.add(outPanel);

        //添加聊天消息输入面板
        JPanel inPanel = new JPanel();
//        inPanel.setBackground(Color.BLUE);
        inPanel.setBounds(5,310,470,190);
        this.add(inPanel);

        //添加用户复选框面板
        JPanel userPanel = new JPanel();
        JLabel onlineUser= new JLabel("在线好友");
        userPanel.add(onlineUser);
//        userPanel.setBackground(Color.RED);
        userPanel.setBounds(480,5,100,400);
        selectedUser=new HashSet();
        //把每个在线用户添加到面板中，设置为多选框，并且提供事件监听
        for (int i=1;i<onlineusers.length;i++){
            String[] username2=onlineusers[i].split("&");
            if (!username2[0].equals(username)){
                JCheckBox checkBox=new JCheckBox(username2[0]);
                userPanel.add(checkBox);
                checkBox.addChangeListener(new ChangeListener() {
                    @Override
                    public void stateChanged(ChangeEvent e) {
                        JCheckBox checkBox = (JCheckBox) e.getSource();
                        if (checkBox.isSelected()){
                            selectedUser.add(checkBox.getText());
                        }
                    }
                });
            }
        }
        this.add(userPanel);

        //添加消息输出
        messageOutPut = new JTextArea(16,40);
        messageOutPut.setEditable(false);
        //传入文本域对象
        JScrollPane aJ=new JScrollPane(messageOutPut);
        outPanel.add(aJ);
        //添加发送文件按钮
        sendText=new JButton("发送文件");
        sendText.setBounds(10,0,15,15);
        outPanel.add(sendText);
        //添加消息记录按钮
        historyMessage=new JButton("消息记录");
        historyMessage.setBounds(50,0,30,10);
        outPanel.add(historyMessage);


        //添加消息输入区
        messageInPut=new JTextArea(8,40);
        JScrollPane inputJ=new JScrollPane( messageInPut);
        inPanel.add(inputJ);
        //添加发送退出按钮
        send=new JButton("发送");
        exit=new JButton("退出");
        inPanel.add(send);
        inPanel.add(exit);


        //添加发送监听事件
        send.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String  a=messageInPut.getText();
                Timestamp d = new Timestamp(System.currentTimeMillis());
                messageOutPut.append("你说"+d+"\n"+a+"\n");
                messageInPut.setText("");
                String towho="";
                for (String s:selectedUser){
                   System.out.println(s);
                   towho=towho+"&"+s;
                }

                /*将消息发送给服务器*/
                client.getChatClient().setMsg("public",towho,a,username);
                client.getChatClient().sendMSG();
            }
        });

        //添加退出监听事件
        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource()==exit){
                    setVisible(false);
                    //关闭当前GUI并且释放资源
                    dispose();
                }
            }

        });
        //添加消息记录按钮监听事件
        historyMessage.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                new HistoryMessage_JFrame().setVisible(true);
            }
        });
        this.setVisible(true);

        //接收消息
    }

//    public static void main(String[] args) {
//        new GroupChat().setVisible(true);
//    }
}
