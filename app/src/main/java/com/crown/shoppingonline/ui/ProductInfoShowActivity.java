package com.crown.shoppingonline.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.widget.TextView;

import com.crown.shoppingonline.R;
import com.crown.shoppingonline.bean.Product;

public class ProductInfoShowActivity extends AppCompatActivity {

    private Product product;

    private TextView descriptionTv;  //商品描述
    private TextView priceTv;        //价格
    private TextView discountTv;     //折扣
    private TextView areaTv;         //地点

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_product_info_show);

        product = getProduct();

        descriptionTv = (TextView) findViewById(R.id.product_description);
        priceTv = (TextView) findViewById(R.id.product_price);
        discountTv = (TextView) findViewById(R.id.product_discount);
        areaTv = (TextView) findViewById(R.id.product_area);

        descriptionTv.setText(product.getpDiscription());
        priceTv.setText("￥:" + product.getpPrice());
        discountTv.setText("" + product.getpDiscount());
        areaTv.setText("" + product.getpArea());
    }

    private Product getProduct() {
        Intent intent = this.getIntent();
        return intent.getParcelableExtra("product");
    }
}
