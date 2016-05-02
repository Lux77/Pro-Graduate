package com.crown.shoppingonline.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by Crown on 2016/5/2.
 */
public class NetworkState {

    private Context mContext;

    public NetworkState(Context context) {
        mContext = context;
    }

    public boolean getNetworkState() {
        ConnectivityManager manager = (ConnectivityManager) mContext.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = manager.getActiveNetworkInfo();
        if(info!=null && info.isConnected()){
            return true;
        }else{
            return false;
        }
    }
}
