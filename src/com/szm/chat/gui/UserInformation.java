package com.szm.chat.gui;

import javax.swing.*;

public class UserInformation extends JFrame {
    private static JLabel LogOnUserID;
    private static JLabel LogOnUserSetSex;
    private static JLabel LogOnUserEmail;
    private static JLabel LogUserID;
    private static JLabel LogUserSetSex;
    private static JLabel LogUserEamail;

    public static void main(String[] args) {
        new  UserInformation();
    }

    public UserInformation(){
        this.setTitle(null);
        this.setSize(300,180);
        this.setLocation(400,200);
        this.setLayout(null);

        JPanel jp1 = new JPanel();
        jp1.setBounds(0, 10, 300, 30);
//        jp1.setBackground(Color.RED);
        JPanel jp2 = new JPanel();
        jp2.setBounds(0, 60, 300, 30);
//        jp2.setBackground(Color.white);
        JPanel jp3 = new JPanel();
        jp3.setBounds(0, 110, 300, 30);
//        jp3.setBackground(Color.BLUE);

        this.setUndecorated(true);

        LogOnUserID = new JLabel("用户ID：");
        jp1.add(LogOnUserID);
        LogOnUserSetSex = new JLabel("性别：");
        jp2.add(LogOnUserSetSex);
        LogOnUserEmail = new JLabel("邮箱：");
        jp3.add(LogOnUserEmail);

        LogUserID = new JLabel("sdasda");
        jp1.add(LogUserID);
        LogUserSetSex = new JLabel("safdg");
        jp2.add(LogUserSetSex);
        LogUserEamail = new JLabel("gher");
        jp3.add(LogUserEamail);


        this.add(jp1);
        this.add(jp2);
        this.add(jp3);
        this.setVisible(true);
    }
}