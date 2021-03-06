package com.example.vsokoltsov.estudy.views.authorization;

import android.os.Bundle;
import android.support.v4.app.FragmentTabHost;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.Toolbar;

import com.example.vsokoltsov.estudy.R;
import com.example.vsokoltsov.estudy.adapters.ViewPagerAdapter;
import com.example.vsokoltsov.estudy.util.SlidingTabLayout;
import com.example.vsokoltsov.estudy.views.base.ApplicationBaseActivity;
import com.example.vsokoltsov.estudy.views.navigation.NavigationDrawer;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vsokoltsov on 13.03.16.
 */
public class AuthorizationActivity extends ApplicationBaseActivity {
    //Common fields
    private NavigationDrawer mNavigationDrawerFragment;
    private DrawerLayout drawerLayout;
    private Toolbar mActionBarToolbar;
    private android.support.v4.app.FragmentManager fragmentManager;
    private String action;
    public AuthorizationBaseFragment authorizationBaseFragment;

    private FragmentTabHost mTabHost;

    //Sliding tab elements
    private ViewPager pager;
    private ViewPagerAdapter adapter;
    private SlidingTabLayout tabs;
    private CharSequence Titles[];
    private List<String> titles = new ArrayList<String>();
    private int Numboftabs =2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.authorization_activity);
        fragmentManager = getSupportFragmentManager();
        setToolbar();
        setLeftNavigationBar();
        defineCurrentTab();
        Bundle arguments = new Bundle();
        arguments.putString("action", action);
        android.support.v4.app.FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        authorizationBaseFragment = new AuthorizationBaseFragment();
        authorizationBaseFragment.setArguments(arguments);
        fragmentTransaction.add(R.id.main_content, authorizationBaseFragment);
        fragmentTransaction.commit();
    }

    private void defineCurrentTab() {
        Bundle extras = getIntent().getExtras();
        if(extras != null) {
            action = (String) extras.getString("action");
        }
    }

    private void setLeftNavigationBar() {
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
