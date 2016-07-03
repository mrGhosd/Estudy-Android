package com.example.vsokoltsov.estudy.views.authorization;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.vsokoltsov.estudy.R;
import com.example.vsokoltsov.estudy.interfaces.UserApi;
import com.example.vsokoltsov.estudy.models.authorization.SignUpRequest;
import com.example.vsokoltsov.estudy.models.authorization.Token;
import com.example.vsokoltsov.estudy.services.ErrorResponse;
import com.example.vsokoltsov.estudy.util.ApiRequester;
import com.example.vsokoltsov.estudy.util.RetrofitException;

import java.io.IOException;

import retrofit2.Retrofit;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by vsokoltsov on 02.07.16.
 */
public class SignUpFragment extends Fragment implements Button.OnClickListener {
    private EditText emailField;
    private EditText passwordField;
    private EditText passwordConfirmationField;
    private Button signUpButton;
    private View fragmentView;
    AuthorizationActivity activity;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        activity = (AuthorizationActivity) getActivity();

        fragmentView = inflater.inflate(R.layout.sign_up_fragment, container, false);
        emailField = (EditText) fragmentView.findViewById(R.id.emailField);
        passwordField= (EditText) fragmentView.findViewById(R.id.passwordField);
        passwordConfirmationField = (EditText) fragmentView.findViewById(R.id.passwordConfirmationField);
        signUpButton = (Button) fragmentView.findViewById(R.id.signUpButton);
        signUpButton.setOnClickListener(this);
        return fragmentView;
    }

    @Override
    public void onClick(View view) {
        activity.sendAuthRequest();
        SignUpRequest user = new SignUpRequest(emailField.getText().toString(),
                passwordField.getText().toString(), passwordConfirmationField.getText().toString());
        Retrofit retrofit = ApiRequester.getInstance().getRestAdapter();
        UserApi service = retrofit.create(UserApi.class);
        service.signUp(user)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Token>() {
                    @Override
                    public void onCompleted() {
                        activity.stopPropgress();
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
        passwordConfirmationField.setError(errors.getFullErrorMessage("password_confirmation"));

        activity.stopPropgress();
    }
}
