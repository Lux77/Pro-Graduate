package com.crown.shoppingonline.ui.viewfragment;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.crown.shoppingonline.R;
import com.crown.shoppingonline.http.HttpLoginThread;
import com.crown.shoppingonline.ui.customview.VpSimpleFragment;
import com.crown.shoppingonline.utils.Config;
import com.crown.shoppingonline.utils.LogHelper;

public class LoginFragment extends VpSimpleFragment {

    EditText emailEt;
    EditText passwdEt;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //View view = inflater.inflate(R.layout.fragment_login, container, false);
        View view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_login, null);
        emailEt = (EditText) view.findViewById(R.id.email);
        passwdEt = (EditText) view.findViewById(R.id.password);
//        final String email = emailEt.getText().toString();
//        final String password = passwdEt.getText().toString();

        view.findViewById(R.id.login_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailEt.getText().toString();
                String password = passwdEt.getText().toString();
                LogHelper.e("FINAL : ", email + "  " + password);
                new HttpLoginThread(getActivity(), Config.URL_LOGIN, email, password)
                        .start();
            }
        });

        return view;
    }

}
