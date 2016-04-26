package com.crown.shoppingonline.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import com.crown.shoppingonline.R;
import com.crown.shoppingonline.bean.Product;
import com.crown.shoppingonline.bean.User;
import com.crown.shoppingonline.http.HttpPutIntoCartThread;
import com.crown.shoppingonline.utils.UserSharedPreferences;

public class ProductInfoShowActivity extends AppCompatActivity {

    private Product product;

    private TextView descriptionTv;  //商品描述
    private TextView priceTv;        //价格
    private TextView discountTv;     //折扣
    private TextView areaTv;         //地点

    private ImageView put;           //加入购物车
    private ImageView buy;           //立即购买

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_product_info_show);

        initView();
        initData();
        productInfoShow();
        put.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                User user = UserSharedPreferences.getUserPreferences(getApplicationContext());
                int userId = user.getUserId();
                int productId = product.getpId();
                new HttpPutIntoCartThread(ProductInfoShowActivity.this, userId, productId).start();
            }
        });
    }

    private void productInfoShow() {
        descriptionTv.setText(product.getpDiscription());
        priceTv.setText("￥:" + product.getpPrice());
        discountTv.setText("" + product.getpDiscount());
        areaTv.setText("" + product.getpArea());
    }

    private void initView() {
        descriptionTv = (TextView) findViewById(R.id.product_description);
        priceTv = (TextView) findViewById(R.id.product_price);
        discountTv = (TextView) findViewById(R.id.product_discount);
        areaTv = (TextView) findViewById(R.id.product_area);
        put = (ImageView) findViewById(R.id.put_into_cart);
        buy = (ImageView) findViewById(R.id.buy_now);
    }

    private void initData() {
        product = getProduct();
    }

    private Product getProduct() {
        Intent intent = this.getIntent();
        return intent.getParcelableExtra("product");
    }
}
