package com.crown.shoppingonline.dao.daoImpl;

import android.content.Context;

import com.crown.shoppingonline.bean.User;
import com.crown.shoppingonline.dao.UserDao;
import com.crown.shoppingonline.utils.UserSharedPreferences;

/**
 * Created by Crown on 2016/4/15.
 */
public class UserDaoImpl implements UserDao {

    private Context mContext;

    public UserDaoImpl(Context context) {
        mContext = context;
    }

    public User isLogin() {
        return UserSharedPreferences.getUserPreferences(mContext);
    }
}
