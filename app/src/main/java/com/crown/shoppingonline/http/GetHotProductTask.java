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
import com.crown.shoppingonline.bean.HotProduct;
import com.crown.shoppingonline.bean.json.HotProductResult;
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
public class GetHotProductTask extends AsyncTask<Void, Void, List<HotProduct>> {

    private Context mContext;
    private ListView mHotListView;
    private List<HotProduct> mHotProducts;

    public GetHotProductTask(Context context, ListView listView) {
        mContext = context;
        mHotListView = listView;
    }

    @Override
    protected List<HotProduct> doInBackground(Void... params) {
        String url = Config.URL_GET_Hot;
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
            HotProductResult hpr = new Gson().fromJson(json, HotProductResult.class);

            return hpr.getHotProducts();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(final List<HotProduct> hotProducts) {
        mHotProducts = hotProducts;
//        Message msg = Message.obtain();
//        msg.what = 1;
//        msg.obj = mHotProducts;
//        handler.sendMessage(msg);
        mHotListView.setAdapter(new BaseAdapter() {
            @Override
            public int getCount() {
                return mHotProducts.size();
//                return 0;
            }

            @Override
            public Object getItem(int position) {
                return mHotProducts.get(position);
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
                    convertView = LayoutInflater.from(mContext).inflate(R.layout.hot_pro_item, null);
                    holder.mShopName = (TextView) convertView.findViewById(R.id.hot_shop_name);
                    holder.mComment = (TextView) convertView.findViewById(R.id.hot_comment);
                    holder.mProImg = (ImageView) convertView.findViewById(R.id.hot_img);
                    convertView.setTag(holder);
                }
                else {
                    holder = (ViewHolder) convertView.getTag();
                }
                HotProduct hotProduct = mHotProducts.get(position);

                holder.mShopName.setText(hotProduct.getShop().getShopName());
                holder.mComment.setText(hotProduct.getProComment());

                String url;
                if((url = hotProduct.getProduct().getpImgName()) != null) {
                    url = Config.URL_IMG + "/" + url;
                    new ImageLoader(holder.mProImg).execute(url);
                }
                return convertView;
            }

            class ViewHolder {
                ImageView mProImg;
                TextView mShopName;
                TextView mComment;
            }
        });
    }
}
