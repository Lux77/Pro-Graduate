package com.crown.shoppingonline.ui.myadapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.crown.shoppingonline.R;
import com.crown.shoppingonline.bean.SellerShow;

import java.util.List;

/**
 * Created by Crown on 2016/4/7.
 */
public class ProductListAdapter extends ArrayAdapter<SellerShow> {

    private int resourceId;

    public ProductListAdapter(Context context, int resource, List<SellerShow> objects) {
        super(context, resource, objects);
        resourceId = resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        SellerShow sellerShow = getItem(position);
        View view;
        ViewHolder viewHolder;
        if(convertView == null) {
            view = LayoutInflater.from(getContext()).inflate(resourceId, null);
            viewHolder = new ViewHolder();
            viewHolder.shopTitle = (TextView) view.findViewById(R.id.shopTitle);
            viewHolder.productImg = (ImageView) view.findViewById(R.id.productImg);
            view.setTag(viewHolder);
        }
        else {
            view = convertView;
            viewHolder = (ViewHolder) view.getTag();
        }
        viewHolder.shopTitle.setText(sellerShow.getShopTitle());
        viewHolder.productImg.setImageResource(sellerShow.getImgId());
        return view;
    }

    class ViewHolder {
        TextView shopTitle;
        ImageView productImg;
    }
}
