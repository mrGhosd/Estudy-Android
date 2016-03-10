package com.example.vsokoltsov.estudy.views;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.vsokoltsov.estudy.R;
import com.example.vsokoltsov.estudy.adapters.UsersListAdapter;
import com.example.vsokoltsov.estudy.interfaces.UserApi;
import com.example.vsokoltsov.estudy.models.User;
import com.example.vsokoltsov.estudy.models.UsersList;
import com.example.vsokoltsov.estudy.util.ApiRequester;

import java.util.ArrayList;
import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;


public class UsersListActivity extends AppCompatActivity  {
    private String AppHost = "http://404b76c2.ngrok.io/api/v0";
    private List<User> users = new ArrayList<User>();
    private RecyclerView rv;
    private UsersListAdapter adapter;
    private ApiRequester api = ApiRequester.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.users_list_activity);

        rv = (RecyclerView) findViewById(R.id.usersList);
        adapter = new UsersListAdapter(users, this);
        rv.setAdapter(adapter);
        rv.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        rv.setLayoutManager(llm);
        loadUsersList();
    }

    private void loadUsersList() {
        UserApi service = api.getRestAdapter().create(UserApi.class);
        service.loadUsers(new Callback<UsersList>() {
            @Override
            public void success(UsersList usersList, Response response) {
                adapter.users = usersList.getUsers();
                adapter.notifyDataSetChanged();
            }

            @Override
            public void failure(RetrofitError error) {

            }
        });
    }
}
