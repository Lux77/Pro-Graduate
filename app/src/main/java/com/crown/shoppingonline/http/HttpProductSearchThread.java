package com.crown.shoppingonline.http;

import android.content.Context;

import com.crown.shoppingonline.utils.Config;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Crown on 2016/4/22.
 */
public class HttpProductSearchThread extends Thread {

    private Context mContext;

    private String mUrl;
    private String mKeyword;

    public HttpProductSearchThread(Context context, String keyword) {
        mContext = context;
        mKeyword = keyword;
    }

    public void doGet() {
        mUrl = Config.URL_PRODUCT_SEARCH + "?keyword=" + mKeyword;
        try {
            URL httpUrl = new URL(mUrl);
            HttpURLConnection conn = (HttpURLConnection) httpUrl.openConnection();
            conn.setRequestMethod("GET");
            conn.setReadTimeout(5*1000);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuffer jsonSb = new StringBuffer();
            String str = null;
            while((str = bufferedReader.readLine()) != null) {
                jsonSb.append(str);
            }

            //TODO: 解析Json数据 获取product列表


        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void run() {
        super.run();
    }
}
