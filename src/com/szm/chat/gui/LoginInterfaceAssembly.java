package com.szm.chat.gui;

import com.szm.chat.exp.LogInException;
import com.szm.chat.thread.Client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

public class LoginInterfaceAssembly extends JFrame implements ActionListener {


    private static String inputUsername;
    private static String inputPassword;
    private static JLabel JLUserName;
    private static JLabel JLUserPaw;
    private static JTextField username;
    private static JPasswordField password;
    private static JButton loginButton;
    private static JButton signButton;
    private Client client;
    private BufferedReader in;
    private PrintWriter out;

    public static void main(String[] args) {
        new LoginInterfaceAssembly();
    }

    public LoginInterfaceAssembly() {
        this.setTitle("Login");
        this.setSize(400, 300);
        this.setLocation(600, 450);


        this.setLayout(null);

        JPanel p1 = new JPanel();
        p1.setBounds(30, 60, 300, 30);
        JLUserName = new JLabel("UserName: ");
        JLUserPaw = new JLabel("PassWord: ");
        JLUserName.setBounds(65, 20, 80, 20);
        JLUserName.setFont(new Font("楷体", 0, 16));
        JLUserPaw.setBounds(65, 50, 60, 20);
        JLUserPaw.setFont(new Font("楷体", 0, 16));

        username = new JTextField(JTextField.NORTH_WEST);
        username.setBounds(130, 14, 100, 30);

        password = new JPasswordField(JPasswordField.NORTH_WEST);
        password.setBounds(130, 44, 300, 30);

        p1.add(JLUserName);
        p1.add(username);
        String userName = username.getText();


        JPanel p2 = new JPanel();
        p2.setBounds(30, 90, 300, 30);
        JPanel p3 = new JPanel();
        p3.setBounds(40, 140, 300, 30);
        loginButton = new JButton("登录");
        loginButton.addActionListener(this);
        signButton = new JButton("注册");
        signButton.addActionListener(this);
        p3.add(loginButton);
        p3.add(signButton);
        p2.add(JLUserPaw);
        p2.add(password);
        char[] passWord = password.getPassword();
        this.add(p1);
        this.add(p2);
        this.add(p3);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);

    }

    //界面跳转

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == signButton){
            this.setVisible(false);
            new RegisterJFrame().setVisible(true);

        }

        if(e.getSource() == loginButton){
            try {
                    inputUsername=username.getText();
                    if (inputUsername.length()==0){
                        JOptionPane.showMessageDialog(this,"请输入一个名字","提示",JOptionPane.INFORMATION_MESSAGE);
                        return;
                    }
                    char[] inputPasswordChar=password.getPassword();
                    inputPassword=String.valueOf(inputPasswordChar);
                if (inputUsername.length()==0){
                    JOptionPane.showMessageDialog(this,"请输入一个密码","提示",JOptionPane.INFORMATION_MESSAGE);
                    return;
                }
                    client=new Client();
                    in=client.getIn();
                    out=client.getOut();
                    client.link(inputUsername,inputPassword);
                    /*隐藏该窗口*/
                    setVisible(false);
                    new TheMainInterfaceAssembly(inputUsername,in,out,client);
                    dispose();
            }catch (IOException ex) {
                JOptionPane.showMessageDialog(loginButton, "登陆失败", "失败", JOptionPane.ERROR_MESSAGE);
            } catch (LogInException ex) {
                JOptionPane.showMessageDialog(loginButton, "登陆失败", "失败", JOptionPane.ERROR_MESSAGE);
            }

        }
    }
}
