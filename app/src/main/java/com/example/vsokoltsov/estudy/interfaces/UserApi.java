package com.example.vsokoltsov.estudy.interfaces;

import com.example.vsokoltsov.estudy.models.UsersList;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by vsokoltsov on 08.03.16.
 */
public interface UserApi {
    @GET("users")
    Call<UsersList> loadUsers();
}
