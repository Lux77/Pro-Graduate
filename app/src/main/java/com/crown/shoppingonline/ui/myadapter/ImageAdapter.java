package com.crown.shoppingonline.ui.myadapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.crown.shoppingonline.R;

import java.util.List;

/**
 * Created by Crown on 2016/4/12.
 */
public class ImageAdapter extends BaseAdapter {

    private Context mContext;
    private List<Integer> mList;

    private LayoutInflater inflater;

    public ImageAdapter(Context c, List<Integer> list) {
        mContext = c;
        mList = list;
        inflater = LayoutInflater.from(mContext);
    }
    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        View view;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            view = inflater.inflate(R.layout.grid_item, null);
            viewHolder.itemImgView = (ImageView) view.findViewById(R.id.item_img);
            view.setTag(viewHolder);
        } else {
            view = convertView;
            viewHolder = (ViewHolder) view.getTag();
        }
        viewHolder.itemImgView.setImageResource(mList.get(position));
        return view;
    }

    class ViewHolder {
        ImageView itemImgView;
    }
}
