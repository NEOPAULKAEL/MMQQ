package com.szm.chat.gui;



import com.szm.chat.entity.HistoryMSG;
import com.szm.chat.service.HistoryMSGService;
import com.szm.chat.thread.Client;

import javax.swing.*;
import java.sql.SQLException;
import java.util.List;

public class HistoryMessage_JFrame extends JFrame{

    //消息输出框
    private static JTextArea Iinfo;

    public HistoryMessage_JFrame(Client client,String[] historyMsg){
        //设置JFrame宽高
        this.setSize(500, 500);
        //设置JFrame的标题
        this.setTitle("聊天记录");
        //设置关闭退出程序
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        //设置不可调整窗口大小
        this.setResizable(false);
        //设置打开位置
        this.setLocation(600, 300);
        this.setLayout(null);


        //添加聊天信息输出面板
        JPanel outPanel = new JPanel();
        outPanel.setBounds(0,0,500,500);
        this.add(outPanel);

        //添加消息输出
        Iinfo = new JTextArea(28,43);
        Iinfo.setEditable(false);
        //传入文本域对象
        JScrollPane aJ=new JScrollPane(Iinfo);
        outPanel.add(aJ);

        //添加历史消息记录
        for (int i=1;i<historyMsg.length;i++){
            String[] splitStr=historyMsg[i].split("&");
            Iinfo.append("时间："+splitStr[3]+"\n");
            Iinfo.append(splitStr[0]+" 对 "+splitStr[1]+" 说 ："+"\n");
            Iinfo.append(splitStr[2]+'\n');
            Iinfo.append("-------------------分隔符-------------------\n");
        }

        this.setVisible(true);



    }
//    public static void main(String[] args) {
//        Client client=new Client();
//        String[] a={"1"};
//        new HistoryMessage_JFrame(client,a).setVisible(true);
//    }

}
