package com.crown.shoppingonline.ui.viewfragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.crown.shoppingonline.R;
import com.crown.shoppingonline.http.GetHotProductTask;
import com.crown.shoppingonline.utils.LogHelper;

//import java.util.LinkedList;
//import java.util.List;

public class SeaFragment extends Fragment {

    private static final String TAG = LogHelper.makeLogTag(SeaFragment.class);

    private ListView mListView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_sea, null);

        mListView = (ListView) rootView.findViewById(R.id.hot_product_list_view);
        new GetHotProductTask(getActivity(), mListView).execute();
        return rootView;
    }
}
