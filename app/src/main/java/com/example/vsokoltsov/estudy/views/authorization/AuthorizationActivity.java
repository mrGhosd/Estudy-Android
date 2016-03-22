package com.example.vsokoltsov.estudy.views.authorization;

import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.Toolbar;

import com.example.vsokoltsov.estudy.R;
import com.example.vsokoltsov.estudy.interfaces.UserApi;
import com.example.vsokoltsov.estudy.models.authorization.AuthorizationService;
import com.example.vsokoltsov.estudy.models.authorization.CurrentUser;
import com.example.vsokoltsov.estudy.util.ApiRequester;
import com.example.vsokoltsov.estudy.views.base.ApplicationBaseActivity;
import com.example.vsokoltsov.estudy.views.navigation.NavigationDrawer;

import retrofit2.Retrofit;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by vsokoltsov on 13.03.16.
 */
public class AuthorizationActivity extends ApplicationBaseActivity {
    private NavigationDrawer mNavigationDrawerFragment;
    private DrawerLayout drawerLayout;
    private Toolbar mActionBarToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.authorization_activity);
        setToolbar();

        android.support.v4.app.FragmentManager fragmentManager = getSupportFragmentManager();
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mNavigationDrawerFragment = (NavigationDrawer) fragmentManager.findFragmentById(R.id.navigation_drawer);
        mNavigationDrawerFragment.setUp(R.id.navigation_drawer, drawerLayout);

        android.support.v4.app.FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        SignInFragment fragment = new SignInFragment();
        fragmentTransaction.add(R.id.auth_fragment, fragment);
        fragmentTransaction.commit();
    }

    private void setToolbar() {
        mActionBarToolbar = (Toolbar) findViewById(R.id.toolbar_actionbar);
        setSupportActionBar(mActionBarToolbar);
    }

    public void currentUserRequest() {
        Retrofit retrofit = ApiRequester.getInstance().getRestAdapter();
        UserApi service = retrofit.create(UserApi.class);
        service.getCurrentUser()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<CurrentUser>() {
                    @Override
                    public void onCompleted() {
                        dismissProgress();
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(CurrentUser user) {
                        currentUserReceived(user);
                    }
                });
    }

    public void currentUserReceived(CurrentUser user) {
        AuthorizationService.getInstance().setCurrentUser(user.getUser());
    }

    public void sendAuthRequest() {
        showProgress(R.string.loader_auth);
    }
}
