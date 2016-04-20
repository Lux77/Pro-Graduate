package com.crown.shoppingonline.ui.viewfragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.crown.shoppingonline.R;
import com.crown.shoppingonline.http.HttpRegisterThread;
import com.crown.shoppingonline.ui.customview.VpSimpleFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class RegisterFragment extends VpSimpleFragment {

    EditText nameEt;
    EditText emailEt;
    EditText passwordEt;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_register, container, false);

        nameEt = (EditText) view.findViewById(R.id.r_username);
        emailEt = (EditText) view.findViewById(R.id.r_email);
        passwordEt = (EditText) view.findViewById(R.id.r_password);
        view.findViewById(R.id.register_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = nameEt.getText().toString();
                String email = emailEt.getText().toString();
                String password = passwordEt.getText().toString();
                new HttpRegisterThread(getActivity(), username, email, password)
                        .start();
            }
        });

        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }
}
