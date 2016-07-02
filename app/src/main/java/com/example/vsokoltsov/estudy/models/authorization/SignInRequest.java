package com.example.vsokoltsov.estudy.models.authorization;

/**
 * Created by vsokoltsov on 14.03.16.
 */
public class SignInRequest {
    private String email;
    private String password;
    private Authorization authorization = new Authorization();

    public SignInRequest(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Authorization getAuthorization() {
        return authorization;
    }
}
