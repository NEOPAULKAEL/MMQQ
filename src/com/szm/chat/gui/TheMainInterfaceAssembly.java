package com.szm.chat.gui;

import com.szm.chat.thread.Client;
//import com.szm.chat.thread.OnlineUserClient;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.PrintWriter;

/**
 * 主界面具体组件、布局
 * @author Vector_Wu
 * @version 1.0
 */

public class TheMainInterfaceAssembly extends JFrame implements ActionListener {


    private static String TheMainUserName;
    private static String TheMainAllUserName;
    private static String TheMainOLUserName;
    private static JButton allSendjbt;
    private static JButton informationjbt;
    private static JButton refreshbt;
    private static JList<String> list1;
    private static JList<String> list2;
    private UserInformation userinformation;
    private String selected;
    private DefaultListModel listModel;
    private Client client;
    private BufferedReader in;
    private PrintWriter out;
    private String username;
    private String[] onlineusers;

    public TheMainInterfaceAssembly(String username,BufferedReader in,PrintWriter out,Client client){
        this.username=username;
        this.client=client;
        client.getGetMSGClient().setClient(client);
        this.out=out;
        this.in=in;
        this.setTitle("MMQQ");
        this.setSize(400,800);
        this.setLocation(800,200);
        //绝对定位
        this.setLayout(null);
        //大小不可变

        //标题和签名栏
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //显示当前用户名
        JLabel jLabeluserName = new JLabel(username);
        jLabeluserName.setBounds(0,-20,200,100);
        Font font = new Font("楷体",Font.BOLD,35);
        jLabeluserName.setFont(font);
        JLabel jTextFieldsign = new JLabel("  "+"这个家伙很懒，什么都没留下");
        jTextFieldsign.setBounds(0,50,200,30);
        this.add(jLabeluserName);
        this.add(jTextFieldsign);


        //中部用户模块
        JPanel onlineJP = new JPanel();
        JPanel alluserJP = new JPanel();

        //所有好友列表
        list1 = new JList<String>();
        list1.setPreferredSize(new Dimension(380,600));
        list1.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);

        /*信息录入接口*/
        list1.setListData(client.getAllUser());

        list1.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                int[] indices = list1.getSelectedIndices();
                ListModel<String>listModel = list1.getModel();
                for(int index : indices){
                    System.out.println("选中：" + index +" = " + listModel.getElementAt(index));
                }
                System.out.println();
            }
        });

        alluserJP.add(list1);

        //在线好友列表

        listModel=new DefaultListModel();
        list2=new JList<>(listModel);
        list2.setPreferredSize(new Dimension(380,600));
        list2.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        onlineusers=client.getGetMSGClient().updateOnlineUser();
        for (int i=1;i<onlineusers.length;i++){
            String[] username2=onlineusers[i].split("&");
            if (!username2[0].equals(username)){
                listModel.addElement(username2[0]);
            }
        }

        list2.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                int[] indices = list2.getSelectedIndices();
                ListModel<String>listModel = list2.getModel();
                for(int index : indices){
                    System.out.println("选中：" + index +" = " + listModel.getElementAt(index));
                    selected=listModel.getElementAt(index);
                }
                System.out.println();
            }
        });

        //鼠标双击打开私聊
        list2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int clickTimes = e.getClickCount();
                if(clickTimes == 2){
                    new PersonChat(username,selected,client);
                }
            }
        });


        onlineJP.add(list2);



        //选项卡标签
        JTabbedPane jtp = new JTabbedPane();
        jtp.setBounds(0,100,400,600);

        jtp.add(onlineJP);
        jtp.add(alluserJP);

        jtp.setTitleAt(0,"在线用户");
        jtp.setTitleAt(1,"所有用户");
        this.add(jtp);



        //先择按钮
        allSendjbt = new JButton("选择群发");
        informationjbt = new JButton("编辑信息");
        refreshbt = new JButton("更新在线用户");
        allSendjbt.setBounds(20,705,110,30);
        informationjbt.setBounds(125,705,110,30);
        refreshbt.setBounds(230,705,130,30);

        allSendjbt.addActionListener(this);
        informationjbt.addActionListener(this);
        refreshbt.addActionListener(this);

        this.add(allSendjbt);
        this.add(informationjbt);
        this.add(refreshbt);

        this.setResizable(false);
        this.setVisible(true);

        //按钮监听

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //打开群聊窗口
        if(e.getSource() == allSendjbt){
            new GroupChat(onlineusers,username,client);
        }
        if(e.getSource() == informationjbt){
            client.getChatClient().getUserInfo();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
            String[] infos=client.getGetMSGClient().getInfos();
            for (int i=0;i<infos.length;i++){
                System.out.println(infos[i]);
            }
            new UpdateJFrame(username,client,infos);
        }
        if (e.getSource()==refreshbt){
            client.getChatClient().refreshOnlineuser();
            listModel.clear();
            onlineusers=client.getGetMSGClient().updateOnlineUser();
            for (int i=1;i<onlineusers.length;i++){
                String[] username2=onlineusers[i].split("&");
                if (!username2[0].equals(username)){
                    listModel.addElement(username2[0]);
                }
            }

        }
    }



}