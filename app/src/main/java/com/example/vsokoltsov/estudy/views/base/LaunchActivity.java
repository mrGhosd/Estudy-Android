package com.example.vsokoltsov.estudy.views.base;

import android.content.Intent;
import android.os.Bundle;
import android.os.Debug;
import android.support.v7.app.ActionBarActivity;

import com.example.vsokoltsov.estudy.R;
import com.example.vsokoltsov.estudy.interfaces.UserApi;
import com.example.vsokoltsov.estudy.models.authorization.AuthorizationService;
import com.example.vsokoltsov.estudy.models.authorization.CurrentUser;
import com.example.vsokoltsov.estudy.util.ApiRequester;
import com.example.vsokoltsov.estudy.views.UsersListActivity;

import retrofit2.Retrofit;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by vsokoltsov on 03.07.16.
 */
public class LaunchActivity extends ActionBarActivity{
    private ApiRequester api = ApiRequester.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        api.setContext(getApplicationContext());
        setContentView(R.layout.launch_layout);
        currentUserRequest();
    }

    private void currentUserRequest() {
        Retrofit retrofit = ApiRequester.getInstance().getRestAdapter();
        UserApi service = retrofit.create(UserApi.class);
        service.getCurrentUser()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<CurrentUser>() {
                    @Override
                    public void onCompleted() {
                        transitionToUsersList();
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        transitionToUsersList();
                    }

                    @Override
                    public void onNext(CurrentUser user) {
                        currentUserReceived(user);
                    }
                });
    }

    private void currentUserReceived(CurrentUser user) {
        AuthorizationService auth = AuthorizationService.getInstance();
        Debug.waitForDebugger();
        auth.setCurrentUser(user.getUser());
    }

    private void transitionToUsersList() {
        Intent usersActivity = new Intent(this, UsersListActivity.class);
        startActivity(usersActivity);
        overridePendingTransition(R.anim.pull_in_right, R.anim.push_out_left);
    }
}
