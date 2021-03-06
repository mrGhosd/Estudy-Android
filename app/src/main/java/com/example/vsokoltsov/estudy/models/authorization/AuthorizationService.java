package com.example.vsokoltsov.estudy.models.authorization;

import com.example.vsokoltsov.estudy.models.User;

/**
 * Created by vsokoltsov on 23.03.16.
 */
public class AuthorizationService {
    private User currentUser;

    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }

    public static class SingletonHolder {
        public static final AuthorizationService HOLDER_INSTANCE = new AuthorizationService();
    }

    public static AuthorizationService getInstance() {
        return SingletonHolder.HOLDER_INSTANCE;
    }

}
