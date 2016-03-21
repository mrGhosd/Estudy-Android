package com.example.vsokoltsov.estudy.interfaces;

import com.example.vsokoltsov.estudy.models.UsersList;
import com.example.vsokoltsov.estudy.models.authorization.SignInRequest;
import com.example.vsokoltsov.estudy.models.authorization.Token;

import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by vsokoltsov on 08.03.16.
 */
public interface UserApi {
    @GET("users")
    Observable<UsersList> loadUsers();

    @POST("sessions")
    Observable<Token> signIn(@Body SignInRequest user);
}
