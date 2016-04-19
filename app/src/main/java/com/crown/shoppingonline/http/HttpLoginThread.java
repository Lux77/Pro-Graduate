package com.crown.shoppingonline.http;

import android.content.Context;

import com.crown.shoppingonline.bean.User;
import com.crown.shoppingonline.utils.LogHelper;
import com.crown.shoppingonline.utils.UserSharedPreferences;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Crown on 2016/4/16.
 */
public class HttpLoginThread extends Thread {

    private static final String TAG = LogHelper.makeLogTag(HttpLoginThread.class);

    private String mUrl;
    private String mEmail;
    private String mPassword;

    private Context mContext;

    public HttpLoginThread(Context context, String url, String email, String password) {
        mContext = context;
        mUrl = url;
        mEmail = email;
        mPassword = password;
    }

    public void doGet() {
        mUrl = mUrl + "?email=" + mEmail + "&password=" + mPassword;
        try {
            URL httpUrl = new URL(mUrl);
            HttpURLConnection conn = (HttpURLConnection) httpUrl.openConnection();
            conn.setRequestMethod("GET");
            conn.setReadTimeout(5 * 1000);
            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuffer json = new StringBuffer();
            String str = null;
            while((str = reader.readLine()) != null) {
                json.append(str);
            }
            UserSharedPreferences.saveUserPreferences(mContext, json.toString());
            LogHelper.e(TAG, "------------- : " + json.toString() + " ---------------");
            User user = UserSharedPreferences.getUserPreferences(mContext);
            LogHelper.e(TAG, "---------- : " + user + " ---------------------");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        doGet();
    }
}
