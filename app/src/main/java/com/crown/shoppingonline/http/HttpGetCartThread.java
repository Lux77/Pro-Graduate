package com.crown.shoppingonline.http;

import com.crown.shoppingonline.bean.Order;
import com.crown.shoppingonline.bean.json.GetCartResult;
import com.crown.shoppingonline.utils.Config;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

/**
 * Created by Crown on 2016/4/26.
 */
public class HttpGetCartThread extends Thread {

    List<Order> orders;
    private int mUserId;

    public List<Order> getmList() {
        return orders;
    }

    public HttpGetCartThread(int id) {
        mUserId = id;
    }

    public List<Order> doGet() {
        String url = Config.URL_GET_CART + "?userId=" + mUserId;
        try {
            URL httpUrl = new URL(url);
            HttpURLConnection conn = (HttpURLConnection) httpUrl.openConnection();
            conn.setRequestMethod("GET");
            conn.setReadTimeout(5* 1000);
            BufferedReader bf = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String str = null;
            StringBuffer jsonSb = new StringBuffer();
            while ((str = bf.readLine()) != null) {
                jsonSb.append(str);
            }
            String json = jsonSb.toString();
            GetCartResult gcr = new Gson().fromJson(json, GetCartResult.class);
            if(gcr.getResultCode() == 1) {
                orders = gcr.getOrders();
            }
            else {
                orders = null;
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void run() {
        orders = doGet();
    }
}
