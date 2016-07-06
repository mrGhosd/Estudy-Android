package com.example.vsokoltsov.estudy.views.base;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

import com.example.vsokoltsov.estudy.R;
import com.example.vsokoltsov.estudy.interfaces.UserApi;
import com.example.vsokoltsov.estudy.messages.UserMessage;
import com.example.vsokoltsov.estudy.models.authorization.AuthorizationService;
import com.example.vsokoltsov.estudy.models.authorization.CurrentUser;
import com.example.vsokoltsov.estudy.util.ApiRequester;

import org.greenrobot.eventbus.EventBus;

import retrofit2.Retrofit;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by vsokoltsov on 22.03.16.
 */
public class ApplicationBaseActivity extends ActionBarActivity {
    private ProgressDialog mProgressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public void showProgress(int msg) {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            dismissProgress();
        }

        mProgressDialog = ProgressDialog.show(this, getResources().getString(R.string.app_name), getResources().getString(msg));
    }

    public void dismissProgress() {
        if (mProgressDialog != null) {
            mProgressDialog.dismiss();
            mProgressDialog = null;
        }
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
        AuthorizationService auth = AuthorizationService.getInstance();
        auth.setCurrentUser(user.getUser());
        EventBus.getDefault().post(new UserMessage("currentUser", user.getUser()));
    }
}
