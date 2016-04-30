package com.crown.shoppingonline.ui.viewfragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.crown.shoppingonline.R;
import com.crown.shoppingonline.http.GetCartTask;
import com.crown.shoppingonline.utils.UserSharedPreferences;

/**
 * Created by Crown on 2016/4/3.
 */
public class CartFragment extends Fragment {

    private ListView mListView;
    private LinearLayout mEmptyLayout;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_cart, null);

        mListView = (ListView) view.findViewById(R.id.cart_list_view);
        mEmptyLayout = (LinearLayout) view.findViewById(R.id.empty_ly);

        new GetCartTask(getActivity(), mListView)
                .execute(""+ UserSharedPreferences.getUserPreferences(getActivity()).getUserId());
        mListView.setEmptyView(mEmptyLayout);
        return view;
    }
}
