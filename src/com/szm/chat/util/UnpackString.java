/**
 * @program: User
 * * @description: 字符串解析 工具类
 * * @author:cro
 * * @create: 2019-05-09 09:11
 **/

package com.szm.chat.util;

/**
 * 输入流字符串解码
 */
public class UnpackString {
    /*接收到的socket输入流内容-字符串*/
    private String inmsg;
    /*对输入流解码*/
    private String[] msg;
    /*"private"+"#"+username+"#"+towho+"#"+message;*/
    /*私聊 公开聊天 标志*/
    private String flag;
    /*发送者用户名*/
    private String sendUsername;
    /*接收者用户名*/
    private String getUsername;
    /*消息*/
    private String message;

    public UnpackString(String inmsg) {
        this.inmsg=inmsg;
        this.msg=inmsg.split("#");
        this.flag=msg[0];
        this.sendUsername=msg[1];
        this.getUsername=msg[2];
        this.message=msg[3];
    }

    public String getInmsg() {
        return inmsg;
    }

    public void setInmsg(String inmsg) {
        this.inmsg = inmsg;
    }

    public String[] getMsg() {
        return msg;
    }

    public void setMsg(String[] msg) {
        this.msg = msg;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getSendUsername() {
        return sendUsername;
    }

    public void setSendUsername(String sendUsername) {
        this.sendUsername = sendUsername;
    }

    public String getGetUsername() {
        return getUsername;
    }

    public void setGetUsername(String getUsername) {
        this.getUsername = getUsername;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
