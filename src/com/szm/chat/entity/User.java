/**
 * @program: User
 * * @description: 用户 实体
 * * @author:cro
 * * @create: 2019-05-06 16:57
 **/

package com.szm.chat.entity;

public class User {
    //用户id
    private int id;
    //用户名
    private String username;
    //密码
    private String password;
    //昵称
    private String nickname;
    //邮箱
    private String email;
    //性别
    private int sex;
    //权限
    private int power;

    //构造方法
    public User(String username, String  password, String nickname, String email, int sex) {
        this.username = username;
        this.password = password;
        this.nickname = nickname;
        this.email = email;
        this.sex = sex;

    }

    //无参构造
    public User(){}


    //访问器
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String  password) {
        this.password = password;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", nickname='" + nickname + '\'' +
                ", email='" + email + '\'' +
                ", sex=" + sex +
                ", power=" + power +
                '}';
    }
}
