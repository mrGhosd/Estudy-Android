package com.example.vsokoltsov.estudy.models;

import java.util.List;

/**
 * Created by vsokoltsov on 08.03.16.
 */
public class UsersList {
    private List<User> users;

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public UsersList(List<User> users) {
        this.users = users;
    }
}
