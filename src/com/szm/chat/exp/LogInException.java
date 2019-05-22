/**
 * @program: User
 * * @description: 登录异常
 * * @author:cro
 * * @create: 2019-05-09 10:39
 **/

package com.szm.chat.exp;

public class LogInException extends Exception{
    public LogInException() {
        super();
    }

    public LogInException(String message) {
        super(message);
    }
}
