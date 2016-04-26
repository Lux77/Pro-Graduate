package com.crown.shoppingonline.http;

import android.content.Context;
import android.os.Handler;
import android.view.Gravity;
import android.widget.Toast;

import com.crown.shoppingonline.bean.json.JsonResult;
import com.crown.shoppingonline.utils.Config;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Crown on 2016/4/25.
 */
public class HttpPutIntoCartThread extends Thread {

    private Context mContext;
    private int uid;
    private int pid;

    private Handler handler = new Handler();

    public HttpPutIntoCartThread(Context context, int userId, int productId) {
        mContext = context;
        uid = userId;
        pid = productId;
    }

    private void doPost() {
        String url = Config.URL_PUT_INTO_CART;
        try {
            URL httpUrl = new URL(url);
            HttpURLConnection conn = (HttpURLConnection) httpUrl.openConnection();
            conn.setRequestMethod("POST");
            conn.setReadTimeout(5 * 1000);


            String content = "userId="+uid+"&productId="+pid;
            OutputStream outputStream = conn.getOutputStream();
            outputStream.write(content.getBytes());

            //if(conn.getResponseCode() == 200) {}

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuffer jsonSb = new StringBuffer();
            String str = null;
            while((str = bufferedReader.readLine()) != null) {
                jsonSb.append(str);
            }
            String json = jsonSb.toString();
            JsonResult putResult = new Gson().fromJson(json, JsonResult.class);
            if(putResult.getResultCode() == 1) {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        Toast toast = Toast.makeText(mContext, "成功加入购物车,您可去购物车查看!", Toast.LENGTH_LONG);
                        toast.setGravity(Gravity.CENTER, 0, 0);
                        toast.show();
                    }
                });
            }
            else {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        Toast toast = Toast.makeText(mContext, "加入购物车失败,请重试!", Toast.LENGTH_SHORT);
                        toast.setGravity(Gravity.CENTER, 0, 0);
                        toast.show();
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
        doPost();
    }
}
