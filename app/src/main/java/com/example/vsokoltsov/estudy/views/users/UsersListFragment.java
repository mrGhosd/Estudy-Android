package com.example.vsokoltsov.estudy.views.users;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.vsokoltsov.estudy.R;
import com.example.vsokoltsov.estudy.adapters.UsersListAdapter;
import com.example.vsokoltsov.estudy.interfaces.UserApi;
import com.example.vsokoltsov.estudy.models.User;
import com.example.vsokoltsov.estudy.models.UsersList;
import com.example.vsokoltsov.estudy.util.ApiRequester;
import com.example.vsokoltsov.estudy.views.MainActivity;

import org.solovyev.android.views.llm.LinearLayoutManager;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Retrofit;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by vsokoltsov on 07.07.16.
 */

public class UsersListFragment extends Fragment {
    private List<User> users = new ArrayList<User>();
    private RecyclerView rv;
    private UsersListAdapter adapter;
    private ApiRequester api = ApiRequester.getInstance();
    private MainActivity mainActivity;

    private View fragmentView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mainActivity = (MainActivity) getActivity();
        fragmentView = inflater.inflate(R.layout.users_list_fragment, container, false);
        rv = (RecyclerView) fragmentView.findViewById(R.id.usersList);
        adapter = new UsersListAdapter(users, getActivity());
        rv.setAdapter(adapter);
        rv.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        rv.setLayoutManager(llm);
        loadUsersList();
        return fragmentView;
    }

    private void loadUsersList() {
        Retrofit retrofit = api.getRestAdapter();
        UserApi service = retrofit.create(UserApi.class);
        service.loadUsers()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<UsersList>() {
                    @Override
                    public void onCompleted() {
                        mainActivity.stopProgress();
                    }

                    @Override
                    public void onError(Throwable e) {
                        handleError(e);
                    }

                    @Override
                    public void onNext(UsersList usersList) {
                        setUsersList(usersList);
                    }
                });
    }

    private void setUsersList(UsersList users) {
        adapter.users = users.getUsers();
        adapter.notifyDataSetChanged();
    }

    private void handleError(Throwable error) {
        mainActivity.stopProgress();
    }
}
