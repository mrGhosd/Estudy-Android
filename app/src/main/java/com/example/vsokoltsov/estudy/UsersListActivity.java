package com.example.vsokoltsov.estudy;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.vsokoltsov.estudy.interfaces.UserApi;
import com.example.vsokoltsov.estudy.models.UsersList;
import com.squareup.okhttp.OkHttpClient;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.OkClient;
import retrofit.client.Response;


public class UsersListActivity extends AppCompatActivity  {
    private String AppHost = "http://018c78d8.ngrok.io/api/v0";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.users_list_activity);

        RestAdapter restAdapter = new RestAdapter.Builder().setLogLevel(RestAdapter.LogLevel.FULL).setEndpoint(AppHost)
                .setClient(new OkClient(new OkHttpClient())).build();
        UserApi service = restAdapter.create(UserApi.class);
        service.loadUsers(new Callback<UsersList>() {
            @Override
            public void success(UsersList usersList, Response response) {
                System.out.println(response.getBody());
            }

            @Override
            public void failure(RetrofitError error) {

            }
        });
    }
}
