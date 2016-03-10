package com.example.vsokoltsov.estudy.adapters;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.vsokoltsov.estudy.R;
import com.example.vsokoltsov.estudy.models.User;
import com.example.vsokoltsov.estudy.util.ApiRequester;
import com.example.vsokoltsov.estudy.view_holders.UserViewHolder;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vsokoltsov on 09.03.16.
 */
public class UsersListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    public List<User> users = new ArrayList<User>();
    private Activity activity;

    public UsersListAdapter(List<User> users, Activity activity) {
        this.users = users;
        this.activity = activity;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_list_row, parent, false);
        UserViewHolder uvh = new UserViewHolder(v, this);
        return uvh;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        UserViewHolder userHolder = (UserViewHolder) holder;
        User user = users.get(position);
        Drawable emptyUser = activity.getResources().getDrawable(R.drawable.empty_user);

        userHolder.setUser(user);
        userHolder.userEmail.setText(user.getEmail());

        if (user.getImage() != null) {
            String fullURL = ApiRequester.getInstance().fullResourceURL(user.getImage().getUrl());
            Picasso.with(this.activity.getApplicationContext())
                    .load(fullURL)
                    .placeholder(emptyUser)
                    .into(userHolder.userAvatar);
        }
        else {
            userHolder.userAvatar.setImageDrawable(emptyUser);
        }
    }

    @Override
    public int getItemCount() {
        return users.size();
    }
}
