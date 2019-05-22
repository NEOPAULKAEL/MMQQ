package com.szm.chat.gui;

import com.szm.chat.thread.Client;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

/**
 * Class 注册页面
 * @author szm
 */
public class RegisterJFrame extends JFrame implements ActionListener {

    private JTextField QOUNText;
    private JPasswordField passwordText;
    private JPasswordField confirmpasswordText;
    private JRadioButton male;
    private JRadioButton female;
    private JTextField emeilText;
    private JTextField nicknamelText;
    private JButton registerButton;
    private JButton backButton;
    private String gender="8";


    public RegisterJFrame() {

        this.setSize(300, 300);
        this.setTitle("QQ-Bate13");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setLocation(600, 450);
        /* 这边设置布局为 null*/
        JPanel panel = new JPanel();
        this.add(panel);
        panel.setLayout(null);
//        //创建欢迎标签
//        JLabel Welcomlabel = new JLabel("Welcome To QO!");
//        Welcomlabel.setBounds(100,10,100,25);
//        panel.add(Welcomlabel);
        // 创建 QOID标签
        JLabel QOIDLabel = new JLabel("用户名:");
        /* 这个方法定义了组件的位置。
         * setBounds(x, y, width, height)
         * x 和 y 指定左上角的新位置，由 width 和 height 指定新的大小。
         */
        QOIDLabel.setBounds(10,20,80,25);
        panel.add(QOIDLabel);

        /*
         * 创建文本域用于用户输入
         */
        QOUNText = new JTextField(20);
        QOUNText.setBounds(100,20,165,25);
        panel.add(QOUNText);

        // 输入密码的文本域
        JLabel passwordLabel = new JLabel("密码:");
        passwordLabel.setBounds(10,50,80,25);
        panel.add(passwordLabel);

        /*
         *这个类似用于输入的文本域
         * 但是输入的信息会以点号代替，用于包含密码的安全性
         */
        passwordText = new JPasswordField(20);
        passwordText.setBounds(100,50,165,25);
        panel.add(passwordText);

        // 输入确认密码的文本域
        JLabel confirmpasswordLabel = new JLabel("确认密码:");
        confirmpasswordLabel.setBounds(10,80,80,25);
        panel.add(confirmpasswordLabel);

        /*
         *这个类似用于输入的文本域
         * 但是输入的信息会以点号代替，用于包含密码的安全性
         */
        confirmpasswordText = new JPasswordField(20);
        confirmpasswordText.setBounds(100,80,165,25);
        panel.add(confirmpasswordText);

        //创建性别单选按钮
        JLabel sexLabel = new JLabel("性别:");
        sexLabel.setBounds(10,110,80,25);
        panel.add(sexLabel);
        //创建单选按钮组
        ButtonGroup bg=new ButtonGroup();
        //创建单选按钮
        male=new JRadioButton("男");
        female=new JRadioButton("女");
        male.setBounds(80,110,80,25);
        female.setBounds(160,110,80,25);
        male.addActionListener(this);
        female.addActionListener(this);
        //把单选按钮添加到组中
        bg.add(male);
        bg.add(female);
        //把按钮添加到面板中
        panel.add(male);
        panel.add(female);
        //创建邮箱
        JLabel emailLabel = new JLabel("邮箱:");
       emailLabel.setBounds(10,140,80,25);
        panel.add(emailLabel);
        //邮箱的输入域
        emeilText = new JTextField(20);
        emeilText.setBounds(100,140,165,25);
        panel.add(emeilText);
        //创建昵称
        JLabel nicknameLabel = new JLabel("昵称:");
        nicknameLabel.setBounds(10,170,80,25);
        panel.add(nicknameLabel);
        //昵称的输入域
        nicknamelText = new JTextField(20);
        nicknamelText.setBounds(100,170,165,25);
        panel.add(nicknamelText);

        // 创建注册按钮
        registerButton = new JButton("注册");
        registerButton.setBounds(10, 200, 80, 25);
        panel.add(registerButton);
        registerButton.addActionListener(this);
        // 创建返回按钮
        backButton = new JButton("返回");
        backButton.setBounds(200, 200, 80, 25);
        panel.add( backButton);
        backButton.addActionListener(this);


    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==backButton){
            this.setVisible(false);
            new LoginInterfaceAssembly().setVisible(true);
        }
        if (e.getSource()==male){
            this.gender="1";
        }
        if (e.getSource()==female){
            this.gender="0";
        }
        if (e.getSource()==registerButton){
            try {
                String username=QOUNText.getText();
                if (username.length()==0){
                    JOptionPane.showMessageDialog(this,"请输入一个名字","提示",JOptionPane.INFORMATION_MESSAGE);
                    return;
                }
                String psw=String.valueOf(passwordText.getPassword());
                if (psw.length()==0){
                    JOptionPane.showMessageDialog(this,"请输入一个密码","提示",JOptionPane.INFORMATION_MESSAGE);
                    return;
                }
                String cpsw=String.valueOf(confirmpasswordText.getPassword());
                if (!cpsw.equals(psw)){
                    JOptionPane.showMessageDialog(this,"两次输入的密码不同","提示",JOptionPane.INFORMATION_MESSAGE);
                    return;
                }
                if (gender.equals("8")){
                    JOptionPane.showMessageDialog(this,"请选择性别","提示",JOptionPane.INFORMATION_MESSAGE);
                    return;
                }
                String email=emeilText.getText();
                String nickname=nicknamelText.getText();
                if (nickname.length()==0){
                    JOptionPane.showMessageDialog(this,"请输入一个昵称","提示",JOptionPane.INFORMATION_MESSAGE);
                    return;
                }
                Client client=new Client();
                Boolean flag=client.register(username,psw,gender,email,nickname);
                if (flag){
                    JOptionPane.showMessageDialog(registerButton, "注册成功", "成功", JOptionPane.ERROR_MESSAGE);
                }else {
                    JOptionPane.showMessageDialog(registerButton, "注册失败,用户名已存在", "失败", JOptionPane.ERROR_MESSAGE);
                }

            } catch (IOException ex) {
                JOptionPane.showMessageDialog(registerButton, "注册失败", "失败", JOptionPane.ERROR_MESSAGE);
            }


//            if (QOIDText.getText()==null || passwordText.getText()==null || confirmpasswordText.getText()==null && nicknamelText.getText()==null && gender==8){
//
//            }

        }
    }
//    public static void main(String[] args) {
//        new RegisterJFrame().setVisible(true);
//    }
}
