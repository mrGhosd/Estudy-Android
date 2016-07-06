package com.example.vsokoltsov.estudy.views;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.Toolbar;

import com.example.vsokoltsov.estudy.R;
import com.example.vsokoltsov.estudy.util.MaterialProgressBar;
import com.example.vsokoltsov.estudy.views.base.ApplicationBaseActivity;
import com.example.vsokoltsov.estudy.views.navigation.NavigationDrawer;
import com.example.vsokoltsov.estudy.views.users.UsersListFragment;


public class MainActivity extends ApplicationBaseActivity {
    private NavigationDrawer mNavigationDrawerFragment;
    private DrawerLayout drawerLayout;
    private MaterialProgressBar progressBar;
    private android.support.v4.app.FragmentManager fragmentManager;
    private UsersListFragment usersListFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.users_list_activity);
        setToolbar();
        showProgress(R.string.loader_wait);
        setUpUI();
    }

    private void setUpUI() {

//        rv = (RecyclerView) findViewById(R.id.usersList);
//        adapter = new UsersListAdapter(users, this);
//        rv.setAdapter(adapter);
//        rv.setHasFixedSize(true);
//        LinearLayoutManager llm = new LinearLayoutManager(this);
//        rv.setLayoutManager(llm);
        fragmentManager = getSupportFragmentManager();
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawerLayout != null) {
            basicUIForPhone();
        }
        else {
            basicUIForTablet();
        }
    }

    private void basicUIForPhone() {
        mNavigationDrawerFragment = (NavigationDrawer) fragmentManager.findFragmentById(R.id.navigation_drawer);
        mNavigationDrawerFragment.setUp(R.id.navigation_drawer, drawerLayout);
        android.support.v4.app.FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        usersListFragment = new UsersListFragment();
        fragmentTransaction.add(R.id.main_content, usersListFragment);
        fragmentTransaction.commit();
    }

    private void basicUIForTablet() {
        mNavigationDrawerFragment = (NavigationDrawer) fragmentManager.findFragmentById(R.id.navigation_drawer);
        mNavigationDrawerFragment.setDrawerLayout(null);
        Fragment frg = fragmentManager.findFragmentById(R.id.container);
        if (frg == null) {
            android.support.v4.app.FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            usersListFragment = new UsersListFragment();
            fragmentTransaction.add(R.id.container, usersListFragment);
            fragmentTransaction.commit();
        }
    }

    private void setToolbar() {
        Toolbar mActionBarToolbar = (Toolbar) findViewById(R.id.toolbar_actionbar);
        setSupportActionBar(mActionBarToolbar);
        progressBar = (MaterialProgressBar) findViewById(R.id.header_progress_bar);
    }

    public void stopProgress() {
        dismissProgress();
    }
}

