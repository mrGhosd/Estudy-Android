package com.example.vsokoltsov.estudy.models.authorization;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by vsokoltsov on 14.03.16.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Token {
    @JsonProperty("token")
    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
