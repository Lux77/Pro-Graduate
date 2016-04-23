package com.crown.shoppingonline.http;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.crown.shoppingonline.bean.Product;
import com.crown.shoppingonline.bean.json.ProductResult;
import com.crown.shoppingonline.ui.ProductsShowActivity;
import com.crown.shoppingonline.utils.Config;
import com.crown.shoppingonline.utils.LogHelper;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

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
        LogHelper.e("marshall", "invoking...");
        mUrl = Config.URL_PRODUCT_SEARCH + "?keyword=" + mKeyword;
        LogHelper.e("Marsh : ", mUrl);
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
            String json = jsonSb.toString();
            LogHelper.e("json", json);
            ProductResult pr = new Gson().fromJson(json, ProductResult.class);
            List<Product> products = pr.getProducts();
            if(pr.getResultCode() == 1) {
                Intent intent = new Intent(mContext, ProductsShowActivity.class);
                Bundle bundle = new Bundle();
                bundle.putParcelable("products", pr);
                intent.putExtra("product_list", bundle);
                mContext.startActivity(intent);
            }
            else {
                mContext.startActivity(new Intent(mContext, ProductsShowActivity.class));
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
