package com.example.pszen.deadlinetracker.util;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.Log;

import com.example.pszen.deadlinetracker.R;
import com.example.pszen.deadlinetracker.model.Reminder;

import java.io.ByteArrayOutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by pszen on 15.01.2018.
 */

public class Util {
    //database version
    public static final int DATABASE_VERSION = 5;
    public static final String DATABASE_NAME = "reminderDB";
    public static final String TABLE_NAME = "reminders";

    // contacts table column names
    public static final String KEY_ID = "id";
    public static final String KEY_MESSAGE = "name";
    public static final String KEY_DATEANDTIME = "dateAndTime";
    public static final String KEY_DESCRIPTION = "description";
    public static final String KEY_NOTIFICATION_ID = "notificationId";
    public static final String KEY_PICTOGRAM = "pictogram";


    public static final int[] pictograms = {R.drawable.important,
            R.drawable.birthday,
            R.drawable.documents,
            R.drawable.school};








}
