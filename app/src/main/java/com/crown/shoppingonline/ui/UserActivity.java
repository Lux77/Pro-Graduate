package com.crown.shoppingonline.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import com.crown.shoppingonline.R;
import com.crown.shoppingonline.ui.customview.ViewPagerIndictor;
import com.crown.shoppingonline.ui.customview.VpSimpleFragment;
import com.crown.shoppingonline.ui.viewfragment.LoginFragment;
import com.crown.shoppingonline.ui.viewfragment.RegisterFragment;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class UserActivity extends FragmentActivity {

    private ViewPager mViewPager;
    private ViewPagerIndictor mIndicator;
//    private List<String> mTitles = Arrays.asList("登录", "注册", "登录3", "注册4", "登录5", "注册6");
    private List<String> mTitles = Arrays.asList("登录", "注册");
    private List<VpSimpleFragment> mContents = new ArrayList<>();
    private FragmentPagerAdapter mAdapter;

    private Fragment loginFragment;
    private Fragment registerFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_register);

        initViews();
        initDatas();

        mIndicator.setVisibleTabCount(2);
        mIndicator.setTabItemTitles(mTitles);

        mViewPager.setAdapter(mAdapter);
        mIndicator.setViewPager(mViewPager, 0);
//        mIndicator.setOnPageChangedListener(new ViewPagerIndictor.PageOnChangedListener() {
//            @Override
//            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
//
//            }
//
//            @Override
//            public void onPageSelected(int position) {
//
//            }
//
//            @Override
//            public void onPageScrollStateChanged(int state) {
//
//            }
//        });
    }

    private void initViews() {
        mViewPager = (ViewPager) findViewById(R.id.view_pager_lr);
        mIndicator = (ViewPagerIndictor) findViewById(R.id.view_pager_indicator);
    }

    private void initDatas() {
        for(String title : mTitles) {
            VpSimpleFragment fragment = VpSimpleFragment.newInstance(title);
            mContents.add(fragment);
        }

        mAdapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                switch (position) {
                    case 0:
                        if(loginFragment == null) {
                            loginFragment = new LoginFragment();
                        }
                        return loginFragment;
                    case 1:
                        if(registerFragment == null) {
                            registerFragment = new RegisterFragment();
                        }
                        return registerFragment;
                    default:
                        return mContents.get(position);
                }
            }

            @Override
            public int getCount() {
                return mContents.size();
            }
        };
    }

}
