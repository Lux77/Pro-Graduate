package com.crown.shoppingonline.bean;

/**
 * Created by Crown on 2016/4/14.
 */
public class User {
    private int userId;
    private String username;
    private String password;
    private String email;
    private int userState;//默认为1 表示该用户是活动的

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getUserState() {
        return userState;
    }

    public void setUserState(int userState) {
        this.userState = userState;
    }

    @Override
    public String toString() {
        return "[id : " + userId + "  username : " + username + "  email : " + email + "]";
    }
}
