package com.example.vsokoltsov.estudy.views.course;

import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.example.vsokoltsov.estudy.R;
import com.example.vsokoltsov.estudy.views.base.ApplicationBaseActivity;
import com.example.vsokoltsov.estudy.views.navigation.NavigationDrawer;

/**
 * Created by vsokoltsov on 10.07.16.
 */
public class CoursesListActivity extends ApplicationBaseActivity {
    //Common fields
    private NavigationDrawer mNavigationDrawerFragment;
    private DrawerLayout drawerLayout;
    private Toolbar mActionBarToolbar;
    private android.support.v4.app.FragmentManager fragmentManager;
    private CoursesListFragment coursesListFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.course_list_activity);
        fragmentManager = getSupportFragmentManager();
        setToolbar();

        showProgress(R.string.loader_wait);
        setLeftNavigationBar();
        android.support.v4.app.FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        coursesListFragment = new CoursesListFragment();
        fragmentTransaction.add(R.id.main_content, coursesListFragment);
        fragmentTransaction.commit();
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        MenuInflater inflater = getMenuInflater();
//        inflater.inflate(R.menu.courses_list_menu, menu);
////        getMenuInflater().inflate(R.menu.courses_list_menu, menu);
//        return true;
//    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
//        switch (item.getItemId()) {
//            case R.id.menuitem1:
//                Toast.makeText(this, "Menu Item 1 selected", Toast.LENGTH_SHORT).show();
//                break;
//            case R.id.menuitem2:
//                Toast.makeText(this, "Menu item 2 selected", Toast.LENGTH_SHORT).show();
//                break;
//        }
        return true;
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
