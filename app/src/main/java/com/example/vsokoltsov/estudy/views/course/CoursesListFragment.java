package com.example.vsokoltsov.estudy.views.course;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.vsokoltsov.estudy.R;
import com.example.vsokoltsov.estudy.views.base.ApplicationBaseActivity;

/**
 * Created by vsokoltsov on 10.07.16.
 */
public class CoursesListFragment extends Fragment {
    private ApplicationBaseActivity activity;
    private View fragmentView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        activity = (ApplicationBaseActivity) getActivity();

        fragmentView = inflater.inflate(R.layout.authorization_base_fragment, container, false);
        return fragmentView;
    }
}
