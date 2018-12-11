package com.example.lab203_62.mobilefinal.Aids;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

public class NotificationAide {
    private String TAG = "NotificationAid";

    private static NotificationAide instance;

    private NotificationAide(){

    }

    public static NotificationAide getInstance(){

        if (instance == null){
            instance = new NotificationAide();
        }

        return instance;

    }

    public void pushNotification(Context context, String message){
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
        Log.d(TAG, "SHOW A NOTIFICATION, " + message);
    }
}
