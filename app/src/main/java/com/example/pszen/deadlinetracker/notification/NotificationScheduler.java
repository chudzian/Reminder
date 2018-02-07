package com.example.pszen.deadlinetracker.notification;

import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.media.AudioAttributes;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;
import android.widget.Toast;

import com.example.pszen.deadlinetracker.R;
import com.example.pszen.deadlinetracker.view.CreateActivity;
import com.example.pszen.deadlinetracker.view.DetailActivity;

import java.util.Calendar;

/**
 * Created by pszen on 23.01.2018.
 */

public class NotificationScheduler {

    public static void setReminder(Context context, long timeInMilis,String message, String description, int pictogram ,int nId){
        //Tymczasowo
        Calendar cal = Calendar.getInstance();

        Intent intent = new Intent(context, AlarmReceiver.class);
        intent.putExtra("message",message);
        intent.putExtra("description",description);
        intent.putExtra("dateAndTime",timeInMilis);
        intent.putExtra("pictogram",pictogram);
        intent.putExtra("nId",nId);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(
                context, nId, intent, 0);
        AlarmManager am = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        am.set(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis()+5000,pendingIntent); // ustawianie czasu przypomnienia
        Toast.makeText(context,"Notification set", Toast.LENGTH_LONG).show();
    }


    @RequiresApi(api = Build.VERSION_CODES.O)
    private static void showNotification(Context context, String message, String description,Long dateAndTime,int pictogram, int nId){
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.createNotificationChannel(myNotificationChanel(context));
        Uri alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        String CHANNEL_ID = "my_channel_01";
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(context,CHANNEL_ID)
                .setSmallIcon(pictogram)
                .setSound(alarmSound)
                .setAutoCancel(true)
                .setContentTitle(message)
                .setContentText(description);
        Intent resultIntent = new Intent(context, DetailActivity.class);
        resultIntent.putExtra("message",message);
        resultIntent.putExtra("description",description);
        resultIntent.putExtra("dateAndTime",dateAndTime);

        TaskStackBuilder stackBuilder = TaskStackBuilder.create(context);

        stackBuilder.addParentStack(DetailActivity.class);
        stackBuilder.addNextIntent(resultIntent);
        PendingIntent resultPendingIntent = stackBuilder.getPendingIntent(nId,PendingIntent.FLAG_UPDATE_CURRENT);
        mBuilder.setContentIntent(resultPendingIntent);

        notificationManager.notify(nId,mBuilder.build());
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private static NotificationChannel myNotificationChanel(Context context){
        String id = "my_channel_01";
        CharSequence name = context.getString(R.string.channel_name);
        String description = context.getString(R.string.channel_description);
        int importance = NotificationManager.IMPORTANCE_HIGH;
        NotificationChannel mChannel = new NotificationChannel(id,name,importance);
        mChannel.setDescription(description);
        mChannel.enableLights(true);
        mChannel.setLightColor(Color.RED);
        mChannel.enableVibration(true);
        mChannel.setVibrationPattern(new long[]{100,200,300,400,500,400,300,200,400});
        return mChannel;
    }

    public static void cancelNotification(Context context, int notificationId){
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        Intent myIntent = new Intent(context.getApplicationContext(),
                AlarmReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(
                context.getApplicationContext(), notificationId, myIntent,     PendingIntent.FLAG_UPDATE_CURRENT);

        alarmManager.cancel(pendingIntent);
    }
}
