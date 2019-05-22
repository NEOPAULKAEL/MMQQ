/**
 * @program: User
 * * @description: 登录+注册 客户端
 * * @author:cro
 * * @create: 2019-05-07 16:30
 **/

package com.szm.chat.thread;

import com.szm.chat.exp.LogInException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;


public class Client {
    private Socket client;
    /**存储服务器发的消息*/
    private BufferedReader in;
    /**发出消息*/
    private PrintWriter out;
    private String username;
    /**判断是注册还是登录*/
    private int loginOrSignin = 3;
    private String[] allUser;
    private ChatClient chatClient;
    private GetMSGClient getMSGClient;

    public GetMSGClient getGetMSGClient() {
        return getMSGClient;
    }

    public ChatClient getChatClient() {
        return chatClient;
    }

    public BufferedReader getIn() {
        return in;
    }

    public PrintWriter getOut() {
        return out;
    }

    public String[] getAllUser() {
        return allUser;
    }

    /**
     * 连接-连接到服务端-执行login（）-把当前socket的输入输出流传输到聊天（发送消息）线程
     *
     * @throws IOException IO异常
     */
    public void link(String inputUsername, String inputPassword) throws IOException, LogInException {

        String host = "127.0.0.1";
        int port = 6000;
        client = new Socket(host, port);
        LogInException logInException = new LogInException();
        Boolean loginFlag = login(inputUsername, inputPassword);
        in = new BufferedReader(new InputStreamReader(client.getInputStream()));
        out = new PrintWriter(client.getOutputStream());
        if (loginFlag) {
//            OnlineUserClient onlineUserClient=new OnlineUserClient();
//            onlineUserClient.init(in,out, username);/*初始化获取更新用户线程*/
            chatClient = new ChatClient();
            /*传入当前socket的输入输出流*/
            chatClient.init(in, out, username);
            getMSGClient=new GetMSGClient();
            getMSGClient.init(in,out,username);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } else {
            throw logInException;
        }

    }
    private String[] sendAllUser() throws IOException {
        String inmsg=in.readLine();
        allUser=inmsg.split("#");
        return allUser;
    }
    /**
     * 登录-输入用户名和密码-传输到服务端验证-服务端返回true or false
     *
     * @throws IOException IO异常
     */
    private boolean login(String inputUsername, String inputPassword) throws IOException {
        loginOrSignin = 1;
        in = new BufferedReader(new InputStreamReader(client.getInputStream()));
        out = new PrintWriter(client.getOutputStream());
//        System.out.println("输入用户名");//login中更改参数
////        username=sc.nextLine();
////        System.out.println("输入密码");//login中更改参数
////        String password=sc.nextLine();
        out.println("LogIn");
        out.flush();
        username = inputUsername;
        out.println(username + "#" + inputPassword);
        out.flush();
        if (in.readLine().equals("0")) {
            System.out.println("用户名或密码错误");
            return false;
        } else {
            System.out.println(username);
            System.out.println("登录成功");
            sendAllUser();
            return true;
        }

    }

    /**
     * 注册
     * @param name 用户名
     * @param psw 用户密码
     * @param gender 用户性别
     * @param email 电子邮箱
     * @param nickname 昵称
     * @throws IOException IO异常
     */
    public boolean register(String name, String psw, String gender, String email, String nickname) throws IOException {
        String host = "127.0.0.1";
        int port = 6000;
        client = new Socket(host, port);
        in = new BufferedReader(new InputStreamReader(client.getInputStream()));
        out = new PrintWriter(client.getOutputStream());
        /*发送注册标识*/
        out.println("SignIn");
        out.flush();
        String userinfo = name + "#" + psw + "#" + gender + "#" + email + "#" + nickname;
        out.println(userinfo);
        out.flush();
        if (in.readLine().equals("0")) {
            System.out.println("用户名重复");
            return false;
        } else {
            System.out.println("注册成功");
            return true;
        }
    }




    }
//    public static void main(String[] args) throws IOException, InterruptedException, LogInException {
//        link("user3","123");
//    }


