package com.example.pszen.deadlinetracker.notification;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.Log;

/**
 * Created by pszen on 22.01.2018.
 */

public class AlarmReceiver extends BroadcastReceiver {
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d("TAG", "onReceive: ");
        String message = intent.getStringExtra("message");
        String description = intent.getStringExtra("description");
        Long dateAndTime = intent.getLongExtra("dateAndTime",0);
        int nId = intent.getIntExtra("nId",0);
        int pictogram = intent.getIntExtra("pictogram",0);

        NotificationScheduler.showNotification(context,message,description,dateAndTime,pictogram,nId);


    }
}
