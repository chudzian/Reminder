package com.example.pszen.deadlinetracker.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.pszen.deadlinetracker.model.Reminder;
import com.example.pszen.deadlinetracker.util.Util;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by pszen on 15.01.2018.
 */

public class DatabaseHandler extends SQLiteOpenHelper {

    public DatabaseHandler(Context context) {
        super(context, Util.DATABASE_NAME, null, Util.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        Log.d("DBcreated:","true");
        String CREATE_CONTACT_TABLE = "CREATE TABLE " + Util.TABLE_NAME + "("
                + Util.KEY_ID + " INTEGER PRIMARY KEY," + Util.KEY_MESSAGE + " TEXT,"
                + Util.KEY_DATEANDTIME + " INTEGER," + Util.KEY_DESCRIPTION + " TEXT,"
                + Util.KEY_NOTIFICATION_ID + " INTEGER," + Util.KEY_PICTOGRAM + " INTEGER" + ")";

        sqLiteDatabase.execSQL(CREATE_CONTACT_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + Util.TABLE_NAME);

        onCreate(sqLiteDatabase);
    }

    /**
     * CRUD Operations - Create, Read, Update, Delete
     */

    public void addReminder(Reminder reminder){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues value = new ContentValues();
        value.put(Util.KEY_MESSAGE, reminder.getMessage());
        value.put(Util.KEY_DATEANDTIME, reminder.getDateAndTime());
        value.put(Util.KEY_DESCRIPTION, reminder.getDescription());
        value.put(Util.KEY_NOTIFICATION_ID, reminder.getNotificationId());
        value.put(Util.KEY_PICTOGRAM, reminder.getPictogram());

        db.insert(Util.TABLE_NAME,null,value);
        db.close();
    }

    public Reminder getReminder(int id){
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(Util.TABLE_NAME,
                new String[] {Util.KEY_ID,Util.KEY_MESSAGE,Util.KEY_DATEANDTIME,Util.KEY_DESCRIPTION,Util.KEY_NOTIFICATION_ID,Util.KEY_PICTOGRAM},
                Util.KEY_ID + "=?", new String[] {String.valueOf(id)},null,null,null,null);

        if (cursor != null)
            cursor.moveToFirst();

        Reminder reminder = new Reminder(
                Integer.parseInt(cursor.getString(0)),
                cursor.getString(1),
                Long.parseLong(cursor.getString(2)),
                cursor.getString(3),
                Integer.parseInt(cursor.getString(4)),
                Integer.parseInt(cursor.getString(5))
        );
        cursor.close();
        return reminder;
    }

    public List<Reminder> getAllReminders(){
        SQLiteDatabase db = this.getReadableDatabase();

        List<Reminder> reminderList = new ArrayList<>();

        String selectAll = "SELECT * FROM " + Util.TABLE_NAME;

        Cursor cursor = db.rawQuery(selectAll,null);

        if (cursor.moveToFirst()){
            do {
                Reminder reminder = new Reminder();
                reminder.setId(Integer.parseInt(cursor.getString(0)));
                reminder.setMessage(cursor.getString(1));
                reminder.setDateAndTime(Long.parseLong(cursor.getString(2)));
                reminder.setDescription(cursor.getString(3));
                reminder.setNotificationId(cursor.getInt(4));
                reminder.setPictogram(Integer.parseInt(cursor.getString(5)));

                reminderList.add(reminder);
            }while (cursor.moveToNext());
        }
        cursor.close();
        return reminderList;
    }
    public List<Reminder> getAllRemindersAscending(){
        SQLiteDatabase db = this.getReadableDatabase();
        Calendar cal = Calendar.getInstance();

        List<Reminder> reminderList = new ArrayList<>();


        Cursor cursor = db.query(Util.TABLE_NAME,null,null,null,null,null,Util.KEY_DATEANDTIME + " ASC");

        if (cursor.moveToFirst()){
            do {
                Reminder reminder = new Reminder();
                reminder.setId(Integer.parseInt(cursor.getString(0)));
                reminder.setMessage(cursor.getString(1));
                reminder.setDateAndTime(Long.parseLong(cursor.getString(2)));
                reminder.setDescription(cursor.getString(3));
                reminder.setNotificationId(cursor.getInt(4));
                reminder.setPictogram(Integer.parseInt(cursor.getString(5)));

                reminderList.add(reminder);
            }while (cursor.moveToNext());
        }
        return reminderList;
    }

    public void removeAll(){
        SQLiteDatabase db = this.getWritableDatabase();

        db.delete(Util.TABLE_NAME,null,null);
    }

    public void removeById(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(Util.TABLE_NAME,Util.KEY_ID + " = " + String.valueOf(id),null);
    }


    public Reminder getReminderByNotifId(int nId){
        SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery = "SELECT * FROM " + Util.TABLE_NAME + " WHERE " + Util.KEY_NOTIFICATION_ID + " = " + nId;
        Cursor cursor = db.rawQuery(selectQuery,null);

        Reminder reminder = new Reminder();
        if(cursor.moveToFirst()){
            reminder.setId(Integer.parseInt(cursor.getString(0)));
            reminder.setMessage(cursor.getString(1));
            reminder.setDateAndTime(Long.parseLong(cursor.getString(2)));
            reminder.setDescription(cursor.getString(3));
            reminder.setNotificationId(Integer.parseInt(cursor.getString(4)));
            reminder.setPictogram(Integer.parseInt(cursor.getString(5)));
        }
        cursor.close();
        return reminder;
    }


}
