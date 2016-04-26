package com.crown.shoppingonline.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.crown.shoppingonline.bean.User;
import com.crown.shoppingonline.bean.json.LoginResult;
import com.google.gson.Gson;

/**
 * Created by Crown on 2016/4/15.
 */
public class UserSharedPreferences {

    private static final String TAG = LogHelper.makeLogTag(UserSharedPreferences.class);
    private static final String USER_FILE = "user_data";

    public static void saveUserPreferences(Context context, String json) {
        SharedPreferences preferences = context.getSharedPreferences(USER_FILE, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("user_json", json);
        editor.commit();
    }

    public static User getUserPreferences(Context context) {
        SharedPreferences preferences = null;
        try {
            preferences = context.getSharedPreferences(USER_FILE, 0);
            String json = preferences.getString("user_json", "");
            if(!json.equals(""))
                return (new Gson().fromJson(json, LoginResult.class)).getUser();
            return null;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void removeUserPreferences(Context context) {
        SharedPreferences preferences = context.getSharedPreferences(USER_FILE, 0);
        SharedPreferences.Editor editor = preferences.edit();
        editor.remove("user_json");
        editor.commit();
    }
}

