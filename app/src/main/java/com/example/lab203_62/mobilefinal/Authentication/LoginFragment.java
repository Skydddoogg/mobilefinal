package com.example.lab203_62.mobilefinal.Authentication;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.lab203_62.mobilefinal.Aids.DBAide;
import com.example.lab203_62.mobilefinal.Aids.NotificationAide;
import com.example.lab203_62.mobilefinal.Aids.RedirectAid;
import com.example.lab203_62.mobilefinal.HomeFragment;
import com.example.lab203_62.mobilefinal.R;

import java.util.ArrayList;

import static android.content.Context.MODE_PRIVATE;

public class LoginFragment extends Fragment {

    private String TAG = "LoginFragment";

    private EditText userId, password;
    private TextView registerBtn;
    private Button loginBtn;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_login, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initViews();
        initRegisterButton();
        initLoginButton();

        SharedPreferences userDetails = getContext().getSharedPreferences("userdetails", MODE_PRIVATE);
        String userId = userDetails.getString("userId", "");

        if (userId != null){
            RedirectAid.getInstance().redirect((AppCompatActivity) getActivity(), new HomeFragment(), "HomeFragment");
        }
    }

    void initViews(){
        userId = getView().findViewById(R.id.login_user_id);
        password = getView().findViewById(R.id.login_password);

        registerBtn = getView().findViewById(R.id.login_register_btn);
        loginBtn = getView().findViewById(R.id.login_login_btn);
    }

    void initRegisterButton(){
        try {
            registerBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    RedirectAid
                            .getInstance()
                            .redirect((AppCompatActivity) getActivity(), new RegisterFragment(), "RegisterFragment");
                }
            });
        } catch (Exception e){
            Log.d(TAG, e.getLocalizedMessage());
        }
    }

    void initLoginButton(){
        try{
            loginBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    String userIdStr = userId.getText().toString();
                    String passwordStr = password.getText().toString();

                    if (userIdStr.isEmpty() || passwordStr.isEmpty()){
                        NotificationAide.getInstance().pushNotification(getContext(), "Please fill out this form");
                    } else {
                        ArrayList userDetail = DBAide.getInstance(getContext()).getPassword(userIdStr);
                        if (passwordStr.equals(userDetail.get(1))){

                            String name = (String) userDetail.get(0);

                            SharedPreferences userDetails = getContext().getSharedPreferences("userdetails", MODE_PRIVATE);
                            SharedPreferences.Editor edit = userDetails.edit();
                            edit.putString("userId", userIdStr.trim());
                            edit.putString("name", name.trim());
                            edit.apply();

                            Bundle bundle = new Bundle();
                            bundle.putString("userId", userIdStr);
                            bundle.putString("name", (String) userDetail.get(0));

                            HomeFragment homeFragment = new HomeFragment();
                            homeFragment.setArguments(bundle);

                            RedirectAid.getInstance().redirect((AppCompatActivity) getActivity(), homeFragment, "HomeFragment");
                        } else {
                            NotificationAide.getInstance().pushNotification(getContext(), "Invalid user or password");
                        }
                    }
                }
            });
        } catch (Exception e){
            Log.d(TAG, e.getLocalizedMessage());
        }
    }
}
