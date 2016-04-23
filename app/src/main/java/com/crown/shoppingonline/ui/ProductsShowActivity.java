package com.crown.shoppingonline.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.crown.shoppingonline.R;
import com.crown.shoppingonline.bean.Product;
import com.crown.shoppingonline.bean.json.ProductResult;
import com.crown.shoppingonline.http.ImageLoader;
import com.crown.shoppingonline.utils.Config;

import java.util.List;

public class ProductsShowActivity extends AppCompatActivity {

    private List<Product> products;
    private ListView searchList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products_show);

        initData();
        searchList = (ListView) findViewById(R.id.search_info_show);

        searchList.setAdapter(new SearchResultAdapter());
    }

    public void initData() {
        Intent intent = ProductsShowActivity.this.getIntent();
        Bundle bundle = intent.getBundleExtra("product_list");
        ProductResult pr = bundle.getParcelable("products");
        products = pr.getProducts();
    }

    private class SearchResultAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return products.size();
        }

        @Override
        public Object getItem(int position) {
            return products.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder viewHolder;
            if(convertView == null) {
                viewHolder = new ViewHolder();
                convertView = getLayoutInflater().from(ProductsShowActivity.this).inflate(R.layout.search_list_item, null);
                viewHolder.proImg = (ImageView) convertView.findViewById(R.id.show_pro_img);
                viewHolder.proDesc = (TextView) convertView.findViewById(R.id.pro_desc);
                viewHolder.proPrice = (TextView) convertView.findViewById(R.id.pro_price);
                convertView.setTag(viewHolder);
            }
            else {
                viewHolder = (ViewHolder) convertView.getTag();
            }

            String url = null;
            Product product = products.get(position);
            if((url = product.getpImgName()) != null) {
                url = Config.URL_IMG + "/" + url;
                new ImageLoader(viewHolder.proImg).execute(url);
            }
            viewHolder.proDesc.setText(product.getpName());
            viewHolder.proPrice.setText("￥:" + product.getpPrice());
            return convertView;
        }

        class ViewHolder {
            ImageView proImg;
            TextView proDesc;
            TextView proPrice;
        }
    }
}
