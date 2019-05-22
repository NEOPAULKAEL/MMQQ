package com.szm.chat.thread;
import com.szm.chat.entity.HistoryMSG;
import com.szm.chat.entity.Message;
import com.szm.chat.entity.OnLineUser;
import com.szm.chat.entity.User;
import com.szm.chat.service.*;
import com.szm.chat.util.UnpackString;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.*;

/** 服务端
 * @author SZM
 */
public class Server{
    private static ServerSocket serverSocket;
    private static Vector<Socket> connections;
    private static Vector<ClientProc> clients;

    public static void main(String[] args) throws IOException {
        //开启服务端，端口号6000
        serverSocket=new ServerSocket(6000);
        System.out.println("服务端已开启,开始监听");
        // 为多个客户端服务,让服务器接收的客户端请求，就相当于有N个服务器,就可以与n个用户端通信
        while (true){
            // 接受客户端的连接请求,并返回一个套接字
            Socket serversocket=serverSocket.accept();
            System.out.println("客户端接入");
            //判断能否登录，登录成功-开启线程
            //创建客户端频道
            ClientProc cp= new ClientProc(serversocket);
            Thread thread=new Thread(cp);
            thread.start();
            addConnection(serversocket,cp);
        }
    }

    /**
     * 发送给所有人的消息
     * @param s 需要发送的消息
     */
    public static void sendAll(String s){
        if (connections!=null){
            for (Socket sk:connections){
                try {
                    PrintWriter pw=new PrintWriter(sk.getOutputStream());
                    pw.println(s);
                    pw.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    /**
     * 发送私聊消息
     * @param name 接收者姓名
     * @param inmsg 发出的消息
     * @return 发送成功或失败
     * @throws IOException IO异常
     */
    public static boolean privateChat(String name,String inmsg) throws IOException, SQLException {
        if (clients!=null){
            for (ClientProc cp:clients){
                if (cp.getUsername().equals(name)){
                    PrintWriter pw=new PrintWriter(cp.getS().getOutputStream());
                    pw.println(inmsg);
                    pw.flush();
                    addMSGToTable(inmsg,name);
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 群发消息
     * @param name 接收消息的人用户名存放在一个数组中
     * @param inmsg 接收的消息
     * @return 返回是否发送成功
     * @throws IOException IO异常
     */
    public static boolean publicChat(String[] name,String inmsg) throws IOException, SQLException {

        if (clients!=null){
            for (String eachname:name){
                System.out.println(eachname);
                for (ClientProc cp:clients){
                    if (cp.getUsername().equals(eachname)){
                        PrintWriter pw=new PrintWriter(cp.getS().getOutputStream());
                        pw.println(inmsg);
                        pw.flush();
                        addMSGToTable(inmsg,eachname);
                    }
                }
            }
            return true;
        }
        return false;

    }

    /**
     * 记录消息到T_HISTORY & T_MESSAGE
     * @param inmsg 接收到的消息
     * @throws SQLException 数据库异常
     */
    private static void addMSGToTable(String inmsg,String getUsername) throws SQLException {
        UnpackString unpack=new UnpackString(inmsg);
        /*插入到history和msg表中*/
        /*int sentid, int getid, String msg, Timestamp sentTime*/
        IMessageService messageService=new MessageService();
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        IUserService userService=new UserService();
        User sentuser=userService.userInfo(unpack.getSendUsername());
        User getuser=userService.userInfo(getUsername);
        Message message1=new Message(sentuser.getId(),getuser.getId(),unpack.getMessage(),timestamp);
        messageService.sentMessage(message1);
        IHistoryMSGService historyMSGService = new HistoryMSGService();
        historyMSGService.writeHistoryMSG(unpack.getSendUsername(),getUsername,message1);
    }
    /**
     * 每增加一个线程，记录sockts和对应的clienproc对象-频道
     * @param s 接收到的socket
     * @param cp 创建的线程
     */
    private static void addConnection(Socket s,ClientProc cp){
        /*如果存放的vector是空的，新建一个，之后存入*/
        if (connections==null){
            connections=new Vector<Socket>();
        }
        connections.addElement(s);

        if (clients==null){
            clients=new Vector<ClientProc>();
        }
        clients.addElement(cp);
    }

    /**
     * 删除某个socket记录和频道记录，并关闭该客户端的socket
     * @param s 服务器接收的socket
     * @param cp 频道对象
     * @throws IOException
     */
    public static void deleteConnection(Socket s,ClientProc cp) throws IOException {
        if (connections!=null){
            connections.removeElement(s);
            s.close();
        }
        if (clients!=null){
            clients.removeElement(cp);
        }
    }
}


/**
 * ClientProc
 */
class ClientProc implements Runnable{
    private Boolean runStop=true;
    private BufferedReader in;
    private PrintWriter out;
    private Socket s;
    private String username=null;
    private String password=null;

    /**
     * 构造
     * @param s 服务器socket
     * @throws IOException IO异常
     */
    public ClientProc(Socket s) throws IOException {
        this.s=s;
        in = new BufferedReader(new InputStreamReader(s.getInputStream()));
        out = new PrintWriter(s.getOutputStream());
        /*发送目前已在线用户列表*/
//        this.updateOnlineUser();
    }
    /******************getter&setter****************************/
    public BufferedReader getIn() {
        return in;
    }

    public PrintWriter getOut() {
        return out;
    }

    public Socket getS() {
        return s;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
    /******************getter&setter****************************/


    /**
     * run method
     */
    @Override
    public void run() {
        while (username==null){
            try {
                String inputSymbol = in.readLine();
                if (inputSymbol.equals("LogIn")){
                    login();
                }
                if (inputSymbol.equals("SignIn")){
                    signin();
                }
            } catch (IOException e) { }
        }
        System.out.println("登录已完成"+username);
        while (runStop){
            try {
                String inmsg = in.readLine();
                System.out.println(username+"said"+inmsg);
                /*退出事件*/
                if(inmsg.equals("refresh")){
                    /*查找在线用户*/
                    findOnlineUser();
                }
                if (inmsg.startsWith("history")){
                    String[] userinfo=inmsg.split("#");
                    findHistoryMsg(userinfo[1],userinfo[2]);
                }
                if (inmsg.startsWith("thisuserinfo")){
                    IUserService userService=new UserService();
                    User user=userService.login(username,password);
                    String nickname=user.getNickname();
                    String id=Integer.toString(user.getId());
                    String sex=Integer.toString(user.getSex());
                    String email=user.getEmail();
                    String pass=user.getPassword();
                    String infos="infos"+"#"+id+"#"+pass+"#"+sex+"#"+email+"#"+nickname;
                    out.println(infos);
                    out.flush();
                }
                /*username+"#"+id+"#"+psw+"#"+gender+"#"+email+"#"+nickname;*/
                if (inmsg.startsWith("changeuserinfo")){
                    String[] infos=inmsg.split("#");
                    User user=new User();
                    user.setUsername(infos[1]);
                    user.setId(Integer.parseInt(infos[2]));
                    user.setPassword(infos[3]);
                    user.setSex(Integer.parseInt(infos[4]));
                    user.setEmail(infos[5]);
                    user.setNickname(infos[6]);
                    IUserService userService=new UserService();
                    userService.changeUserInfo(user);
                }
                if (inmsg!=null && (inmsg.startsWith("private") || inmsg.startsWith("public"))){
                    UnpackString unpack=new UnpackString(inmsg);
                    if (unpack.getFlag().equals("private")){
                        Boolean send=Server.privateChat(unpack.getGetUsername(),inmsg);
                        System.out.println(send);
                        if (send){
                            out.println(inmsg);
                            out.flush();
                        }else {
                            unpack.setMessage("failed");
                            out.println(unpack.getMsg());
                            out.flush();
                        }
                    }
                    if (unpack.getFlag().equals("public")){
                        String[] namearr=unpack.getGetUsername().split("&");
                        Boolean send=Server.publicChat(namearr,inmsg);
                        System.out.println(send);
                        if (send){
                            out.println(inmsg);
                            out.flush();
                        }else {
                            unpack.setMessage("failed");
                            out.println(unpack.getMsg());
                            out.flush();
                        }
                    }
                }
            } catch (IOException e) {
                logout();
            } catch (SQLException e) {
                e.printStackTrace();
                /*无人在线异常*/
            }
        }
        System.out.println("线程退出");
    }

    /**
     * 处理退出事件
     */
    private void logout(){
        System.out.println(username+"退出");
        try {
            Server.deleteConnection(s,this);
            /*获取User，更改状态*/
            IOnLineUserService onLineUserService=new OnLineUserService();
            IUserService userService=new UserService();
            User user=userService.login(username,password);
            onLineUserService.userOffline(username);
            runStop=false;

        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    /**
     * 用户登录
     */
    private void login(){
        try {
            String inmsg;
            inmsg = in.readLine();
            System.out.println("获取的输入流："+inmsg);
            String[] userinfo=inmsg.split("#");
            /*输入的用户名和密码*/
            username=userinfo[0];
            password=userinfo[1];
            /*调用userservice方法查看是否存在该用户*/
            IUserService userService=new UserService();
            User user=userService.login(username,password);
            System.out.println(user);
            out=new PrintWriter(s.getOutputStream());
            if(user==null){
                System.out.println("用户名或密码错误");
                out.println("0");
                out.flush();
                username=null;
                return;
            }else {
                System.out.println("登录成功");
                /*没有输出！*/
                out.println("1");
                out.flush();
                /*登陆成功，添加到在线用户表*/
                IOnLineUserService onLineUserService=new OnLineUserService();
                onLineUserService.userOnline(user,s);
                findAllUser();
                findOnlineUser();

            }

            System.out.println(inmsg+"4");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 注册
     */
    public void signin(){
        try {
            String inmsg;
            inmsg = in.readLine();
            System.out.println("获取的输入流："+inmsg);
            String[] userinfo=inmsg.split("#");
            /*name,PSW,SEX,MAIL,NICKNAME*/
            String name=userinfo[0];
            String psw=userinfo[1];
            String gender=userinfo[2];
            int sex=Integer.parseInt(gender);
            String mail=userinfo[3];
            String nickname=userinfo[4];
            IUserService userService=new UserService();
            User user=userService.userInfo(name);
            if (user==null){
                user=new User(name,psw,nickname,mail,sex);
                userService.register(user);
                out.println("1");
                out.flush();
            }else {
                System.out.println("已有该用户名");
                out.println("0");
                out.flush();
            }
            System.out.println(user);
        } catch (IOException e) {
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 数据库中获取在线用户
     */
    public void findOnlineUser(){
        IOnLineUserService iOnLineUserService=new OnLineUserService();
        try {
            List<OnLineUser> onlineUserList=iOnLineUserService.onlineUserInfo();
            String olUser="OnlineUser";
            for (OnLineUser onLineUser:onlineUserList){
                String olUserinfo=onLineUser.getUsername()+"&"+onLineUser.getNickname();
                olUser=olUser+"#"+olUserinfo;
            }
            Server.sendAll(olUser);
        } catch (SQLException e) {
            System.out.println("无人在线");
        }
    }

    /**
     * 查询所有用户
     */
    public void findAllUser(){
        try {
            IUserService userService=new UserService();
            List<User> list=new ArrayList<>();
            list=userService.showAllUser();
            String userinfo="AllUser";
            for (User us:list){
                userinfo=userinfo+"#"+us.getUsername()+"&"+us.getNickname();
            }
            Server.sendAll(userinfo);
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    //获取历史记录
    public  void findHistoryMsg(String nameto,String namefrom){
        HistoryMSGService Hmsg=new HistoryMSGService();
        List<HistoryMSG> list;

        {
            try {
                list = Hmsg.showHistoryMSG(namefrom,nameto);
                String historyMsg="history"+"#";
                StringBuffer sb=new StringBuffer();
                sb.append(historyMsg);
                for(HistoryMSG history:list) {
                   /*int sentid, int getid, String msg, Timestamp sentTime, String namefrom, String nameto, String msg1, Timestamp datetime*/
                   String sentfrom=history.getNamefrom();
                   String sentto=history.getNameto();
                   String msg=history.getMsg();
                   Timestamp time=history.getSentTime();
                   historyMsg=sentfrom+"&"+sentto+"&"+msg+"&"+time+"#";
                   sb.append(historyMsg);
                   System.out.println(sb.toString());
                }
                out.println(sb.toString());
                out.flush();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }



}
