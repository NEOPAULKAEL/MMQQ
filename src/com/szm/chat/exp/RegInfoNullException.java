/**
 * @program: User
 * * @description: 注册信息不完全
 * * @author:cro
 * * @create: 2019-05-09 17:26
 **/

package com.szm.chat.exp;

public class RegInfoNullException extends Exception{
    public RegInfoNullException() {
        super();
    }

    public RegInfoNullException(String message) {
        super(message);
    }
}

