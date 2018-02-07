package com.example.pszen.deadlinetracker.util;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.inputmethod.InputMethodManager;

import com.example.pszen.deadlinetracker.data.DatabaseHandler;
import com.example.pszen.deadlinetracker.model.Reminder;
import com.example.pszen.deadlinetracker.notification.NotificationScheduler;
import com.example.pszen.deadlinetracker.view.MainActivity;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by pszen on 31.01.2018.
 */

public class Controller {

    public static void sortReminderList(List<Reminder> reminderList){
        for (int i = 0; i < reminderList.size(); i++) {
            for(int j = 1; j < reminderList.size();j++){
                if(reminderList.get(j-1).getDateAndTime()>reminderList.get(j).getDateAndTime()){
                    Reminder temp = reminderList.get(j-1);
                    reminderList.set(j-1,reminderList.get(j));
                    reminderList.set(j,temp);
                }
            }
        }
    }

    public static String dateAndTimeString(long timeInMilis){
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        Date date = new Date(timeInMilis);

        String dateString = DateFormat.getDateInstance(DateFormat.LONG).format(date);
        String timeString = sdf.format(date);

        return dateString + " " + timeString;
    }




}
