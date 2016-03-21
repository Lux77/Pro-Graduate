package com.crown.shoppingonline;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;

import com.crown.shoppingonline.utils.LogHelper;

public class MainActivity extends FragmentActivity implements View.OnClickListener {

    private static final String TAG = LogHelper.makeLogTag(MainActivity.class);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LogHelper.i(TAG, "on create!");
        setContentView(R.layout.activity_main);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.home_btn:

                break;
            case R.id.sea_btn:

                break;
            case R.id.discover_btn:

                break;
            case R.id.cart_btn:

                break;
            case R.id.self_btn:

                break;
        }
    }
}
