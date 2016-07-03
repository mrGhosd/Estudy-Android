package com.example.vsokoltsov.estudy.models.authorization;

/**
 * Created by vsokoltsov on 03.07.16.
 */
public class SignUpUserField extends SignInRequest {
    private String passwordConfirmation;

    public SignUpUserField(String email, String password) {
        super(email, password);
    }

    public SignUpUserField(String email, String password, String passwordConfirmation) {
        super(email, password);
        this.passwordConfirmation = passwordConfirmation;
    }

    public String getPasswordConfirmation() {
        return passwordConfirmation;
    }

    public void setPasswordConfirmation(String passwordConfirmation) {
        this.passwordConfirmation = passwordConfirmation;
    }
}
