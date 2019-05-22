package com.szm.chat.gui;

import com.szm.chat.thread.Client;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class UpdateJFrame extends JFrame implements ActionListener {

    private JTextField QOUNText;
    private JTextField passwordText;
    private JTextField idText;
    private JRadioButton male;
    private JRadioButton female;
    private JTextField emeilText;
    private JTextField nicknamelText;
    private JButton updateButton;
    private JButton backButton;
    private String gender="8";
    private String username;
    private Client client;
    private String infos[];

    public UpdateJFrame(String username,Client client,String[] infos) {
        this.username=username;
        this.client=client;
        this.infos=infos;
        this.setSize(300, 300);
        this.setTitle("修改信息");
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setResizable(false);
        this.setLocation(600, 450);
        /* 这边设置布局为 null*/
        JPanel panel = new JPanel();
        this.add(panel);
        panel.setLayout(null);

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
        QOUNText.setEditable(false);
        QOUNText.setBounds(100,20,165,25);
        QOUNText.setText(username);
        panel.add(QOUNText);

        // 输入密码的文本域
        JLabel idLabel = new JLabel("ID:");
        idLabel.setBounds(10,50,80,25);
        panel.add(idLabel);

        /*
         *这个类似用于输入的文本域
         * 但是输入的信息会以点号代替，用于包含密码的安全性
         */
        idText = new JTextField(20);
        idText.setBounds(100,50,165,25);
        idText.setEditable(false);
        idText.setText(infos[1]);
        panel.add(idText);

        // 输入密码的文本域

        JLabel passwordLabel = new JLabel("密码:");
        passwordLabel.setBounds(10,80,80,25);
        panel.add(passwordLabel);

        /*
         *这个类似用于输入的文本域
         * 但是输入的信息会以点号代替，用于包含密码的安全性
         */
        passwordText = new JTextField(20);
        passwordText.setBounds(100,80,165,25);
        passwordText.setText(infos[2]);
        panel.add(passwordText);

        //创建性别单选按钮
        String sex=infos[3];
        JLabel sexLabel = new JLabel("性别:");
        sexLabel.setBounds(10,110,80,25);
        panel.add(sexLabel);
        ButtonGroup bg=new ButtonGroup(); //创建单选按钮组
        male=new JRadioButton("男"); //创建单选按钮
        female=new JRadioButton("女");
        male.setBounds(80,110,80,25);
        female.setBounds(160,110,80,25);
        male.addActionListener(this);
        female.addActionListener(this);
        bg.add(male); //把单选按钮添加到组中
        bg.add(female);
        panel.add(male); //把按钮添加到面板中
        panel.add(female);
        if (sex.equals("1")){
            bg.setSelected(male.getModel(),true);
            gender="1";
        }else {
            bg.setSelected(female.getModel(),true);
            gender="0";
        }
        //创建邮箱
        JLabel emailLabel = new JLabel("邮箱:");
       emailLabel.setBounds(10,140,80,25);
        panel.add(emailLabel);
        //邮箱的输入域
        emeilText = new JTextField(20);
        emeilText.setBounds(100,140,165,25);
        emeilText.setText(infos[4]);
        panel.add(emeilText);
        //创建昵称
        JLabel nicknameLabel = new JLabel("昵称:");
        nicknameLabel.setBounds(10,170,80,25);
        panel.add(nicknameLabel);
        //昵称的输入域
        nicknamelText = new JTextField(20);
        nicknamelText.setBounds(100,170,165,25);
        nicknamelText.setText(infos[5]);
        panel.add(nicknamelText);

        // 创建注册按钮
        updateButton = new JButton("修改");
        updateButton.setBounds(10, 200, 80, 25);
        panel.add( updateButton);
        updateButton.addActionListener(this);
        // 创建返回按钮
        backButton = new JButton("返回");
        backButton.setBounds(200, 200, 80, 25);
        panel.add( backButton);
        backButton.addActionListener(this);
        this.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==backButton){
            this.setVisible(false);
            dispose();
        }
        if (e.getSource()==male){
            this.gender="1";
        }
        if (e.getSource()==female){
            this.gender="0";
        }
        if (e.getSource()==updateButton){
            String username=QOUNText.getText();
            String id=idText.getText();
            String psw=String.valueOf(passwordText.getText());
            if (psw.length()==0){
                JOptionPane.showMessageDialog(this,"请输入一个密码","**",JOptionPane.INFORMATION_MESSAGE);
                return;
            }
            String email=emeilText.getText();
            String nickname=nicknamelText.getText();
            if (nickname.length()==0){
                JOptionPane.showMessageDialog(this,"请输入一个昵称","**",JOptionPane.INFORMATION_MESSAGE);
                return;
            }
            String newInfo="changeuserinfo"+"#"+username+"#"+id+"#"+psw+"#"+gender+"#"+email+"#"+nickname;
            client.getChatClient().sendnewUserInfo(newInfo);
            JOptionPane.showMessageDialog(this,"已提交修改请求","**",JOptionPane.INFORMATION_MESSAGE);
            this.setVisible(false);
            dispose();


        }
    }
//    public static void main(String[] args) {
//        new UpdateJFrame().setVisible(true);
//    }
}
