package com.example.vsokoltsov.estudy.views;

import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.example.vsokoltsov.estudy.R;
import com.example.vsokoltsov.estudy.adapters.UsersListAdapter;
import com.example.vsokoltsov.estudy.interfaces.UserApi;
import com.example.vsokoltsov.estudy.models.User;
import com.example.vsokoltsov.estudy.models.UsersList;
import com.example.vsokoltsov.estudy.util.ApiRequester;
import com.example.vsokoltsov.estudy.util.MaterialProgressBar;
import com.example.vsokoltsov.estudy.views.base.ApplicationBaseActivity;
import com.example.vsokoltsov.estudy.views.navigation.NavigationDrawer;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Retrofit;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


public class UsersListActivity extends ApplicationBaseActivity {
    private String AppHost = "http://404b76c2.ngrok.io/api/v0";
    private List<User> users = new ArrayList<User>();
    private RecyclerView rv;
    private UsersListAdapter adapter;
    private ApiRequester api = ApiRequester.getInstance();
    private NavigationDrawer mNavigationDrawerFragment;
    private DrawerLayout drawerLayout;
    private MaterialProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        api.setContext(getApplicationContext());
        setContentView(R.layout.users_list_activity);
        setToolbar();
        showProgress(R.string.loader_wait);
        setUpUI();
        loadUsersList();
    }

    private void setUpUI() {

        rv = (RecyclerView) findViewById(R.id.usersList);
        adapter = new UsersListAdapter(users, this);
        rv.setAdapter(adapter);
        rv.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        rv.setLayoutManager(llm);
        android.support.v4.app.FragmentManager fragmentManager = getSupportFragmentManager();
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mNavigationDrawerFragment = (NavigationDrawer) fragmentManager.findFragmentById(R.id.navigation_drawer);
        mNavigationDrawerFragment.setUp(R.id.navigation_drawer, drawerLayout);
    }

    private void loadUsersList() {
        Retrofit retrofit = api.getRestAdapter();
        UserApi service = retrofit.create(UserApi.class);
        service.loadUsers()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<UsersList>() {
                    @Override
                    public void onCompleted() {
                        dismissProgress();
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(UsersList usersList) {
                        setUsersList(usersList);
                    }
                });
    }

    private void setUsersList(UsersList users) {
        adapter.users = users.getUsers();
        adapter.notifyDataSetChanged();
    }

    private void handleError(Throwable error) {

    }

    private void setToolbar() {
        Toolbar mActionBarToolbar = (Toolbar) findViewById(R.id.toolbar_actionbar);
        setSupportActionBar(mActionBarToolbar);
        progressBar = (MaterialProgressBar) findViewById(R.id.header_progress_bar);
    }
}

