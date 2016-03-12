package com.example.vsokoltsov.estudy.views;

import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.vsokoltsov.estudy.R;
import com.example.vsokoltsov.estudy.adapters.UsersListAdapter;
import com.example.vsokoltsov.estudy.interfaces.UserApi;
import com.example.vsokoltsov.estudy.models.User;
import com.example.vsokoltsov.estudy.models.UsersList;
import com.example.vsokoltsov.estudy.util.ApiRequester;
import com.example.vsokoltsov.estudy.views.navigation.NavigationDrawer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;


public class UsersListActivity extends ActionBarActivity {
    private String AppHost = "http://404b76c2.ngrok.io/api/v0";
    private List<User> users = new ArrayList<User>();
    private RecyclerView rv;
    private UsersListAdapter adapter;
    private ApiRequester api = ApiRequester.getInstance();
    private NavigationDrawer mNavigationDrawerFragment;
    private DrawerLayout drawerLayout;

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
        android.support.v4.app.FragmentManager fragmentManager = getSupportFragmentManager();
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mNavigationDrawerFragment = (NavigationDrawer) fragmentManager.findFragmentById(R.id.navigation_drawer);
        mNavigationDrawerFragment.setUp(R.id.navigation_drawer, drawerLayout);
    }

    private void loadUsersList() {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        // set your desired log level
        OkHttpClient client = new OkHttpClient();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient httpClientWLogging = new OkHttpClient.Builder().addInterceptor(new Interceptor() {
            @Override
            public okhttp3.Response intercept(Chain chain) throws IOException {
                String locale = Locale.getDefault().getLanguage();
                Request request = chain.request().newBuilder()
                        .addHeader("locale", locale).build();
                return chain.proceed(request);
            }
        }).build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiRequester.API_ADDRESS)
                .addConverterFactory(JacksonConverterFactory.create())
                .client(httpClientWLogging)
                .build();
        UserApi service = retrofit.create(UserApi.class);
        service.loadUsers().enqueue(new Callback<UsersList>() {
            @Override
            public void onResponse(Call<UsersList> call, Response<UsersList> response) {
                adapter.users = response.body().getUsers();
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<UsersList> call, Throwable t) {
                Log.e("RESP", t.getMessage());
            }
        });
    }
}

