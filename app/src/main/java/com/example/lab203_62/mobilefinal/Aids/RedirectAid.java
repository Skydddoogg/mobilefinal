package com.example.lab203_62.mobilefinal.Aids;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.lab203_62.mobilefinal.R;

public class RedirectAid {

    private String TAG = "RedirectAid";

    private static RedirectAid instance;

    private RedirectAid(){

    }

    public static RedirectAid getInstance(){

        if (instance == null){
            instance = new RedirectAid();
        }

        return instance;

    }

    public void redirect(AppCompatActivity activity, Fragment destination, String message){

        activity.getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.main_view, destination)
                .addToBackStack(message)
                .commit();

        Log.d(TAG, "GO TO " + message);

    }

}
