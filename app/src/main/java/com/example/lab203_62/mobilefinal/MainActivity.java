package com.example.lab203_62.mobilefinal;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.lab203_62.mobilefinal.Aids.RedirectAid;
import com.example.lab203_62.mobilefinal.Authentication.LoginFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {

            RedirectAid
                    .getInstance()
                    .redirect(this, new LoginFragment(), "LoginFragment");

        }

    }
}
