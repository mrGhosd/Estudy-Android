package com.example.vsokoltsov.estudy.models.authorization;

import com.example.vsokoltsov.estudy.models.User;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by vsokoltsov on 23.03.16.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class CurrentUser {
    @JsonProperty("current_user")
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
