/**
 * @program: User
 * * @description: 登录空值 异常
 * * @author:cro
 * * @create: 2019-05-09 18:31
 **/

package com.szm.chat.exp;

public class LogInNullException extends Exception{
    public LogInNullException() {
        super();
    }

    public LogInNullException(String message) {
        super(message);
    }
}
