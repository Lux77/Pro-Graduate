package com.crown.shoppingonline.bean.json;

import com.crown.shoppingonline.bean.User;

/**
 * Created by Crown on 2016/4/21.
 */
public class LoginResult {
    private int resultCode;
    private User user;

    public int getResultCode() {
        return resultCode;
    }

    public void setResultCode(int resultCode) {
        this.resultCode = resultCode;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
