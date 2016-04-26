package com.crown.shoppingonline.ui.viewfragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.crown.shoppingonline.R;
import com.crown.shoppingonline.bean.Order;
import com.crown.shoppingonline.utils.LogHelper;

import java.util.List;

/**
 * Created by Crown on 2016/4/3.
 */
public class CartFragment extends Fragment {

    //TODO: delete
//    private ImageView deleteImg;
    private ListView mListView;
    //private LinearLayout mEmptyLayout;

    private List<Order> mOrders = null;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        LogHelper.e("CreateView", "createView");
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_cart, null);

        initView(view);
        //initData();
        //mListView.setAdapter(new CartListAdapter());
        LogHelper.e("order == null ?", ""+mOrders==null);
//        if (mOrders == null)
           // mListView.setEmptyView(mEmptyLayout);
        mListView.setEmptyView(view.findViewById(R.id.empty_img));
        return null;
    }

    private void initView(View view) {
        LogHelper.e("initView", "initView");
        mListView = (ListView) view.findViewById(R.id.cart_list_view);
        //mEmptyLayout = (LinearLayout) view.findViewById(R.id.empty_ly);
    }

//    private void initData() {
//        LogHelper.e("DATA", "init data!");
//        HttpGetCartThread thread = new HttpGetCartThread(
//                UserSharedPreferences.getUserPreferences(getActivity()).getUserId());
//        thread.start();
//        mOrders = thread.getmList();
//    }
//
//    private class CartListAdapter extends BaseAdapter {
//
//        @Override
//        public int getCount() {
//            return mOrders.size();
//        }
//
//        @Override
//        public Object getItem(int position) {
//            return mOrders.get(position);
//        }
//
//        @Override
//        public long getItemId(int position) {
//            return position;
//        }
//
//        @Override
//        public View getView(int position, View convertView, ViewGroup parent) {
//            ViewHolder holder;
//            if (convertView == null) {
//                holder = new ViewHolder();
//                convertView = LayoutInflater.from(getActivity()).inflate(R.layout.cart_item, null);
//                holder.mShopName = (TextView) convertView.findViewById(R.id.shop_name);
//                holder.mDesc = (TextView) convertView.findViewById(R.id.product_desc);
//                holder.mPrice = (TextView) convertView.findViewById(R.id.product_pri);
//                holder.mProCnt = (TextView) convertView.findViewById(R.id.product_cnt);
//                holder.mProImg = (ImageView) convertView.findViewById(R.id.product_img);
//                convertView.setTag(holder);
//            }
//            else {
//               holder = (ViewHolder) convertView.getTag();
//            }
//            Order order = mOrders.get(position);
//            holder.mShopName.setText(order.getShopName());
//            holder.mDesc.setText(order.getProductDescription());
//            holder.mPrice.setText("" + order.getProductPrice());
//            holder.mProCnt.setText("" + order.getProductCount());
//
//            String url = null;
//            if((url = order.getProductImgName()) != null) {
//                url = Config.URL_IMG + "/" + url;
//                new ImageLoader(holder.mProImg).execute(url);
//            }
//            return convertView;
//        }
//    }
//
//    private class ViewHolder {
//        ImageView mProImg;
//        TextView mShopName;
//        TextView mDesc;
//        TextView mPrice;
//        TextView mProCnt;
//    }
}
