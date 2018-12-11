package com.example.lab203_62.mobilefinal.Authentication;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.lab203_62.mobilefinal.Aids.DBAide;
import com.example.lab203_62.mobilefinal.Aids.NotificationAide;
import com.example.lab203_62.mobilefinal.Aids.RedirectAid;
import com.example.lab203_62.mobilefinal.R;

import java.util.ArrayList;

public class RegisterFragment extends Fragment {

    private EditText userId, password, name, age;
    private Button registerBtn;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_register, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initViews();
        initRegisterButton();
    }

    void initViews(){
        userId = getView().findViewById(R.id.register_user_id);
        password = getView().findViewById(R.id.register_password);
        name = getView().findViewById(R.id.register_name);
        age = getView().findViewById(R.id.register_age);

        registerBtn = getView().findViewById(R.id.register_register_btn);
    }

    void initRegisterButton(){
        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userIdStr = userId.getText().toString();
                String passwordStr = password.getText().toString();
                String nameStr = name.getText().toString();
                String ageStr = age.getText().toString();

                if (checkConstraints(userIdStr, passwordStr, nameStr, ageStr)){
                    User user = new User(userIdStr, passwordStr, nameStr, ageStr);
                    DBAide.getInstance(getContext()).add(user);
                    NotificationAide.getInstance().pushNotification(getContext(), "Registered");

                    RedirectAid.getInstance().redirect((AppCompatActivity) getActivity(), new LoginFragment(), "LoginFragment");
                } else {
                    NotificationAide.getInstance().pushNotification(getContext(), "Please correctly fill in the blanks");
                }
            }
        });
    }

    boolean checkConstraints(String inUserId, String inPassword, String inName, String inAge){
        boolean status = true;

        if ((inUserId.length() < 6) || (inUserId.length() > 12)){
            status = false;
        } else if (inName.split(" ").length != 2){
            status = false;
        } else if ((Integer.parseInt(inAge) < 10) || (Integer.parseInt(inAge) > 80)){
            status = false;
        } else if (inPassword.length() <= 6){
            status = false;
        } else {

        }

        return status;
    }
}
