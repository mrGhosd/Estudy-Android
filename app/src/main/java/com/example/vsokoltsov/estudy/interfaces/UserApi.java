package com.example.vsokoltsov.estudy.interfaces;

import com.example.vsokoltsov.estudy.models.UsersList;

import retrofit.Callback;
import retrofit.http.GET;

/**
 * Created by vsokoltsov on 08.03.16.
 */
public interface UserApi {
    @GET("/users")
    void loadUsers(Callback<UsersList> response);
}
