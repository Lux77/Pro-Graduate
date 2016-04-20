package com.crown.shoppingonline.http;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.view.Gravity;
import android.widget.Toast;

import com.crown.shoppingonline.bean.RegisterResult;
import com.crown.shoppingonline.ui.UserActivity;
import com.crown.shoppingonline.utils.Config;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

/**
 * Created by Crown on 2016/4/20.
 */
public class HttpRegisterThread extends Thread {

    private Context mContext;
    private Handler handler = new Handler();

    private String username;
    private String email;
    private String password;

    public HttpRegisterThread(Context context, String username, String email, String password) {
        mContext = context;
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public void doPost() {
        try {
            String url = Config.URL_REGISTER;
            URL httpUrl = new URL(url);
            HttpURLConnection conn = (HttpURLConnection) httpUrl.openConnection();
            conn.setRequestMethod("POST");
            conn.setReadTimeout(5 * 1000);
            String content = "username="+username+"&email="+email+"&password="+password;
            OutputStream outputStream = conn.getOutputStream();
            outputStream.write(content.getBytes());
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuffer jsonSb = new StringBuffer();
            String str = null;
            while((str = bufferedReader.readLine()) != null) {
                jsonSb.append(str);
            }
            String json = jsonSb.toString();
            RegisterResult regR = new Gson().fromJson(json, RegisterResult.class);
            if(regR.getResultCode() == 1) {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        Toast toast = Toast.makeText(mContext, "注册成功!", Toast.LENGTH_SHORT);
                        toast.setGravity(Gravity.CENTER, 0, 0);
                        toast.show();
                        Intent intent = new Intent(mContext, UserActivity.class);
                        mContext.startActivity(intent);
                    }
                });
            }
            else {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        Toast toast = Toast.makeText(mContext, "注册失败,请重试!", Toast.LENGTH_SHORT);
                        toast.setGravity(Gravity.CENTER, 0, 0);
                        toast.show();
                    }
                });
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        doPost();
    }
}
