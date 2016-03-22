package com.example.vsokoltsov.estudy.views.chats;

import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;

import com.example.vsokoltsov.estudy.R;
import com.example.vsokoltsov.estudy.views.navigation.NavigationDrawer;

/**
 * Created by vsokoltsov on 13.03.16.
 */
public class ChatActivity extends ActionBarActivity {
    private NavigationDrawer mNavigationDrawerFragment;
    private DrawerLayout drawerLayout;
    private Toolbar mActionBarToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chats_activity);
        setToolbar();

        android.support.v4.app.FragmentManager fragmentManager = getSupportFragmentManager();
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mNavigationDrawerFragment = (NavigationDrawer) fragmentManager.findFragmentById(R.id.navigation_drawer);
        mNavigationDrawerFragment.setUp(R.id.navigation_drawer, drawerLayout);
    }

    private void setToolbar() {
        mActionBarToolbar = (Toolbar) findViewById(R.id.toolbar_actionbar);
        setSupportActionBar(mActionBarToolbar);
    }
}
