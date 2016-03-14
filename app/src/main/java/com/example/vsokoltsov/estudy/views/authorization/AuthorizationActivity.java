package com.example.vsokoltsov.estudy.views.authorization;

import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;

import com.example.vsokoltsov.estudy.R;
import com.example.vsokoltsov.estudy.views.navigation.NavigationDrawer;

/**
 * Created by vsokoltsov on 13.03.16.
 */
public class AuthorizationActivity extends ActionBarActivity {
    private NavigationDrawer mNavigationDrawerFragment;
    private DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.authorization_activity);

        android.support.v4.app.FragmentManager fragmentManager = getSupportFragmentManager();
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mNavigationDrawerFragment = (NavigationDrawer) fragmentManager.findFragmentById(R.id.navigation_drawer);
        mNavigationDrawerFragment.setUp(R.id.navigation_drawer, drawerLayout);

        android.support.v4.app.FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        SignInFragment fragment = new SignInFragment();
        fragmentTransaction.add(R.id.auth_fragment, fragment);
        fragmentTransaction.commit();
    }
}
