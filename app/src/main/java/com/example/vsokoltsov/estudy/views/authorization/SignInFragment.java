package com.example.vsokoltsov.estudy.views.authorization;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.vsokoltsov.estudy.R;

/**
 * Created by vsokoltsov on 14.03.16.
 */
public class SignInFragment extends Fragment {
    private EditText emailField;
    private EditText passwordField;
    private Button signInButton;
    private View fragmentView;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        fragmentView = inflater.inflate(R.layout.authorization_fragment, container, false);
        emailField = (EditText) fragmentView.findViewById(R.id.emailField);
        passwordField= (EditText) fragmentView.findViewById(R.id.passwordField);
        Button signInButton = (Button) fragmentView.findViewById(R.id.signInButton);
        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                signIn(v);
            }
        });
        return fragmentView;
    }
}
