package com.example.vsokoltsov.estudy.views.authorization;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.vsokoltsov.estudy.R;
import com.example.vsokoltsov.estudy.interfaces.UserApi;
import com.example.vsokoltsov.estudy.models.authorization.SignInRequest;
import com.example.vsokoltsov.estudy.models.authorization.Token;
import com.example.vsokoltsov.estudy.services.ErrorResponse;
import com.example.vsokoltsov.estudy.util.ApiRequester;
import com.example.vsokoltsov.estudy.util.RetrofitException;
import com.example.vsokoltsov.estudy.views.base.ApplicationBaseActivity;

import java.io.IOException;

import retrofit2.Retrofit;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by vsokoltsov on 14.03.16.
 */
public class SignInFragment extends Fragment implements Button.OnClickListener {
    private EditText emailField;
    private EditText passwordField;
    private Button signInButton;
    private View fragmentView;
    ApplicationBaseActivity activity;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        activity = (ApplicationBaseActivity) getActivity();

        fragmentView = inflater.inflate(R.layout.sign_in_fragment, container, false);
        emailField = (EditText) fragmentView.findViewById(R.id.emailField);
        passwordField= (EditText) fragmentView.findViewById(R.id.passwordField);
        Button signInButton = (Button) fragmentView.findViewById(R.id.signInButton);
        signInButton.setOnClickListener(this);
        return fragmentView;
    }

    @Override
    public void onClick(View view) {
        activity.showProgress(R.string.sign_in_action);
        SignInRequest user = new SignInRequest(emailField.getText().toString(),
                passwordField.getText().toString());
        Retrofit retrofit = ApiRequester.getInstance().getRestAdapter();
        UserApi service = retrofit.create(UserApi.class);
        service.signIn(user)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Token>() {
                    @Override
                    public void onCompleted() {
                        activity.dismissProgress();
                        Log.d("tag", "request completed");
                    }

                    @Override
                    public void onError(Throwable e) {
                        try {
                            handleErrors(e);
                        } catch (IOException e1) {
                            e1.printStackTrace();
                        }
                    }

                    @Override
                    public void onNext(Token token) {
                        successAuth(token);
                    }
                });
    }


    private void  successAuth(Token token) {
        ApiRequester.getInstance().setToken(token.getToken());
        activity.currentUserRequest();
    }

    private void handleErrors(Throwable t) throws IOException {
        RetrofitException error = (RetrofitException) t;
        ErrorResponse errors = error.getErrorBodyAs(ErrorResponse.class);
        emailField.setError(errors.getFullErrorMessage("email"));
        passwordField.setError(errors.getFullErrorMessage("password"));

        activity.dismissProgress();
    }
}
