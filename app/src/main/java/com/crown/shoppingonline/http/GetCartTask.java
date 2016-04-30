package com.crown.shoppingonline.http;

import android.content.Context;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.crown.shoppingonline.R;
import com.crown.shoppingonline.bean.Order;
import com.crown.shoppingonline.bean.json.GetCartResult;
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
 * Created by Crown on 2016/4/26.
 */
public class GetCartTask extends AsyncTask<String, Void, List<Order>> {

    private Context mContext;
    private ListView mCartListView;
    private List<Order> mOrders;

    public GetCartTask(Context context, ListView listView) {
        mContext = context;
        mCartListView = listView;
    }

    @Override
    protected List<Order> doInBackground(String... params) {
        String url = Config.URL_GET_CART + "?userId=" + params[0];
        LogHelper.e("URL ", url);
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

            return gcr.getOrders();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(final List<Order> orders) {
        mOrders = orders;
        mCartListView.setAdapter(new BaseAdapter() {
            @Override
            public int getCount() {
                return mOrders.size();
            }

            @Override
            public Object getItem(int position) {
                return mOrders.get(position);
            }

            @Override
            public long getItemId(int position) {
                return position;
            }

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                ViewHolder holder;
                if (convertView == null) {
                    holder = new ViewHolder();
                    convertView = LayoutInflater.from(mContext).inflate(R.layout.cart_item, null);
                    holder.mShopName = (TextView) convertView.findViewById(R.id.shop_name);
                    holder.mDesc = (TextView) convertView.findViewById(R.id.product_desc);
                    holder.mPrice = (TextView) convertView.findViewById(R.id.product_pri);
                    holder.mProCnt = (TextView) convertView.findViewById(R.id.product_cnt);
                    holder.mProImg = (ImageView) convertView.findViewById(R.id.product_img);
                    convertView.setTag(holder);
                }
                else {
                    holder = (ViewHolder) convertView.getTag();
                }
                Order order = mOrders.get(position);
                holder.mShopName.setText(order.getShopName());
                holder.mDesc.setText(order.getProductDescription());
                holder.mPrice.setText("￥:" + order.getProductPrice());
                holder.mProCnt.setText("数量:" + order.getProductCount());

                String url = null;
                if((url = order.getProductImgName()) != null) {
                    url = Config.URL_IMG + "/" + url;
                    new ImageLoader(holder.mProImg).execute(url);
                }
                return convertView;
            }

            class ViewHolder {
                ImageView mProImg;
                TextView mShopName;
                TextView mDesc;
                TextView mPrice;
                TextView mProCnt;
            }
        });
    }
}
