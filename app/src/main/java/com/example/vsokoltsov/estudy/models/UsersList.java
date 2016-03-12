package com.example.vsokoltsov.estudy.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Created by vsokoltsov on 08.03.16.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class UsersList {
    @JsonProperty("users")
    private List<User> _users;

    public List<User> getUsers() {
        return _users;
    }

    public void setUsers(List<User> users) {
        this._users = users;
    }
}
