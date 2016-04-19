package com.crown.shoppingonline.ui.viewfragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.crown.shoppingonline.R;
import com.crown.shoppingonline.ui.customview.VpSimpleFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class RegisterFragment extends VpSimpleFragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_register, container, false);
    }

}
