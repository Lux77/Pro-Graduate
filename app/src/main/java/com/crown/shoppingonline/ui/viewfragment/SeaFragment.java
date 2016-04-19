package com.crown.shoppingonline.ui.viewfragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.crown.shoppingonline.R;
import com.crown.shoppingonline.bean.SellerShow;
import com.crown.shoppingonline.ui.myadapter.ProductListAdapter;
import com.crown.shoppingonline.utils.LogHelper;

import java.util.ArrayList;
import java.util.List;

public class SeaFragment extends Fragment {

    private static final String TAG = LogHelper.makeLogTag(SeaFragment.class);

    private List<SellerShow> list = new ArrayList<SellerShow>();

    public SeaFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        initProductList();
        ProductListAdapter adapter = new ProductListAdapter(getActivity(), R.layout.product_item, list);
        View rootView = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_sea, null);
        ListView listView = (ListView) rootView.findViewById(R.id.productInfoListView);
        listView.setAdapter(adapter);
        return rootView;
    }

    private void initProductList() {
        SellerShow seller1 = new SellerShow("时尚机械键盘店", R.mipmap.listimg);
        list.add(seller1);
        SellerShow seller2 = new SellerShow("时尚机械键盘", R.mipmap.listimg);
        list.add(seller2);
        SellerShow seller3 = new SellerShow("时尚机械键", R.mipmap.listimg);
        list.add(seller3);
    }
}
