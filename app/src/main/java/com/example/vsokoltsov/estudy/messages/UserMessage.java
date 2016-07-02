package com.example.vsokoltsov.estudy.messages;

import com.example.vsokoltsov.estudy.models.User;

/**
 * Created by vsokoltsov on 02.07.16.
 */
public class UserMessage {
    public String operationName;
    public User user;

    public UserMessage(String name, User user){
        this.operationName = name;
        this.user = user;
    }
}
