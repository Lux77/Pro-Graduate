package com.crown.shoppingonline.http;

import android.content.Context;
import android.os.Handler;
import android.widget.Toast;

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

    private Handler handler = new Handler();

    public HttpLoginThread(Context context, String url, String email, String password) {
        mContext = context;
        mUrl = url;
        mEmail = email;
        mPassword = password;
    }

    public void doGet() {
        mUrl = mUrl + "?email=" + mEmail + "&password=" + mPassword;
        LogHelper.e("URL : ", mUrl);
        try {
            URL httpUrl = new URL(mUrl);
            HttpURLConnection conn = (HttpURLConnection) httpUrl.openConnection();
            conn.setRequestMethod("GET");
            conn.setReadTimeout(5 * 1000);
            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuffer jsonSb = new StringBuffer();
            String str = null;
            while ((str = reader.readLine()) != null) {
                jsonSb.append(str);
            }
            String json = jsonSb.toString();
            LogHelper.e("取出的数据是 ：", json);
            if(json.length() > 0) {
                UserSharedPreferences.saveUserPreferences(mContext, json);
                LogHelper.e("进错地方了", "ee");

                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(mContext, "账号或密码错误,请重试！", Toast.LENGTH_SHORT).show();
                    }
                });
            }
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
