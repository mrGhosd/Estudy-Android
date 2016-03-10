package com.example.vsokoltsov.estudy.view_holders;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.vsokoltsov.estudy.R;
import com.example.vsokoltsov.estudy.adapters.UsersListAdapter;
import com.example.vsokoltsov.estudy.models.User;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by vsokoltsov on 09.03.16.
 */
public class UserViewHolder extends RecyclerView.ViewHolder {
    CardView cv;
    UsersListAdapter adapter;
    private User user;
    public TextView userEmail;
    public CircleImageView userAvatar;

    public UserViewHolder(View itemView, UsersListAdapter adapter) {
        super(itemView);
        this.adapter = adapter;
        userEmail = (TextView) itemView.findViewById(R.id.userEmail);
        userAvatar = (CircleImageView) itemView.findViewById(R.id.userAvatar);
    }

    public void setUser(User user) {
        this.user = user;
    }
}
