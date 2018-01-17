package com.example.pszen.deadlinetracker.model;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.widget.Toast;

import com.example.pszen.deadlinetracker.R;
import com.example.pszen.deadlinetracker.view.MainActivity;

/**
 * Created by pszen on 09.01.2018.
 */

public class AlarmReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context,"ALARM", Toast.LENGTH_LONG).show();
        
    }
}
