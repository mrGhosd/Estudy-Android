package com.example.vsokoltsov.estudy.models;

import android.graphics.Bitmap;

/**
 * Created by vsokoltsov on 13.03.16.
 */
public class NavigationItem {
    private int image;
    private String title;
    private User user;
    private Bitmap userImageBitmap;

    public NavigationItem(int image, String title){
        this.image = image;
        this.title = title;
    }

    public NavigationItem(User user) {
        this.user = user;
    }

    public Boolean isUserCell() {
        return this.user != null;
    }

    private void setUserImageBitMap(Bitmap bm) {
        this.userImageBitmap = bm;
    }

    public Bitmap getUserImageBitmap() {
        return this.getUserImageBitmap();
    }

    public User getUser() {
        return this.user;
    }

    public int getImage(){
        return this.image;
    }
    public String getTitle(){
        return this.title;
    }
}
