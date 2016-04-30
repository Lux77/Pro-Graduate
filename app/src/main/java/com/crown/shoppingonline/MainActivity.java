package com.crown.shoppingonline;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.ImageView;

import com.crown.shoppingonline.dao.UserDao;
import com.crown.shoppingonline.dao.daoImpl.UserDaoImpl;
import com.crown.shoppingonline.ui.UserActivity;
import com.crown.shoppingonline.ui.viewfragment.CartFragment;
import com.crown.shoppingonline.ui.viewfragment.HomeFragment;
import com.crown.shoppingonline.ui.viewfragment.SeaFragment;
import com.crown.shoppingonline.ui.viewfragment.UserinfoFragment;
import com.crown.shoppingonline.utils.LogHelper;

public class MainActivity extends FragmentActivity implements View.OnClickListener {

    private static final String TAG = LogHelper.makeLogTag(MainActivity.class);

    private ImageView[] navBar = new ImageView[4];
    //底部导航栏
    private int[] navBarId = {R.id.home_btn, R.id.sea_btn, R.id.cart_btn, R.id.self_btn};
    //导航栏选中时的状态
    private int[] navSelectOn = {R.mipmap.nav_home_clicked, R.mipmap.nav_sea_clicked,
             R.mipmap.nav_cart_clicked, R.mipmap.nav_account_clicked};
    //导航栏未选中时的状态
    private int[] navSelectOff = {R.mipmap.nav_home_default, R.mipmap.nav_sea_default,
             R.mipmap.nav_cart_default, R.mipmap.nav_account_default};

    private Fragment activeFragment = null; //随时保留当前可见的活动页面
    private HomeFragment homeFragment = null;
    private CartFragment cartFragment = null;
    private SeaFragment seaFragment = null;
    private UserinfoFragment userinfoFragment = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    @Override
    public void onClick(View v) {
      //  hideFragment(activeFragment); //隐藏当前活动的fragment
        switch(v.getId()) {
            case R.id.home_btn:
                navBarState(0);
                if(homeFragment == null) {
                    homeFragment = new HomeFragment();
                    activeFragment = homeFragment;
                    //addFragment(homeFragment);
                    replaceFragment(homeFragment);
                }
                else {
                    replaceFragment(homeFragment);
                }
                break;
            case R.id.sea_btn:
                navBarState(1);
                if(seaFragment == null) {
                    seaFragment = new SeaFragment();
                    activeFragment = seaFragment;
                    //addFragment(seaFragment);
                    replaceFragment(seaFragment);
                }
                else {
                    replaceFragment(seaFragment);
                }
                break;

            case R.id.cart_btn:
                navBarState(2);
                if(cartFragment == null) {
                    cartFragment = new CartFragment();
                    activeFragment = cartFragment;
                    replaceFragment(cartFragment);
                }
                else {
                    replaceFragment(cartFragment);
                }
                break;
            case R.id.self_btn:
                navBarState(3);
                UserDao userDao = new UserDaoImpl(getApplicationContext());
                if(userDao.isLogin() == null) {
                    Intent intent = new Intent(MainActivity.this, UserActivity.class);
                    startActivity(intent);
                }
                else {
                    if (userinfoFragment == null) {
                        userinfoFragment = new UserinfoFragment();
                        activeFragment = userinfoFragment;
                        replaceFragment(userinfoFragment);
                    } else {
                        replaceFragment(userinfoFragment);
                    }
                }
                break;
        }
    }

    //首次运行时调用
    public void init() {
        initNavBar();  //初始化底部导航条
        initViewShow();  //初始化 首页
    }

    //初始化底部导航栏
    public void initNavBar() {
        for(int i = 0; i < navBar.length; i++) {
            navBar[i] = (ImageView) findViewById(navBarId[i]);
            navBar[i].setOnClickListener(this);
        }
    }

    //初次加载界面时首页显示
    public void initViewShow() {
        if(homeFragment == null) {
            homeFragment = new HomeFragment();
            //addFragment(homeFragment);
            replaceFragment(homeFragment);
            activeFragment = homeFragment;  //首次加载时，可见页为 首页
        }
        else {
            showFragment(homeFragment);
        }
        navBar[0].setImageResource(navSelectOn[0]);
    }
/*
    public void addFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction()
                .add(R.id.main_show_layout, fragment).commit();
    }

    public void removeFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction()
                .remove(fragment).commit();
    }

    public void hideFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction()
                .hide(fragment).commit();
    }
*/
    //加载不同的视图
    public void showFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        //将已经实例化的Fragment隐藏
        if(activeFragment != null) {
            fragmentTransaction.hide(activeFragment);
        }
        fragmentTransaction.show(fragment);
        fragmentTransaction.commitAllowingStateLoss();
    }

    public void replaceFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.main_show_layout, fragment).commit();
    }
    public void navBarState(int navBarId) {
        for(int i = 0; i < navBar.length; i++) {
            navBar[i].setImageResource(navSelectOff[i]);
        }
        navBar[navBarId].setImageResource(navSelectOn[navBarId]);
    }
}
