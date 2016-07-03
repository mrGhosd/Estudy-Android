package com.example.vsokoltsov.estudy.models.authorization;

/**
 * Created by vsokoltsov on 03.07.16.
 */
public class SignUpRequest {
    private SignUpUserField user;

    public SignUpRequest(String email, String password, String passwordConfirmation) {
        this.user = new SignUpUserField(email, password, passwordConfirmation);
    }

    public SignUpUserField getUser() {
        return user;
    }

    public void setUser(SignUpUserField user) {
        this.user = user;
    }
}
