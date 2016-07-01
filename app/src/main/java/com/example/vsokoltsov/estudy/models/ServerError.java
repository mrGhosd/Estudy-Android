package com.example.vsokoltsov.estudy.models;

/**
 * Created by vsokoltsov on 02.07.16.
 */
public class ServerError {
    private Throwable error;
    private String detailMessage;

    ServerError(Throwable error) {
        this.error = error;
    }

    public Throwable getError() {
        return error;
    }

    public void setError(Throwable error) {
        this.error = error;
    }
}
