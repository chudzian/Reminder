package com.example.pszen.deadlinetracker.view;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;

import com.example.pszen.deadlinetracker.R;
import com.example.pszen.deadlinetracker.data.DatabaseHandler;
import com.example.pszen.deadlinetracker.model.DatePickerFragment;
import com.example.pszen.deadlinetracker.model.Reminder;
import com.example.pszen.deadlinetracker.model.TimePickerFragment;
import com.example.pszen.deadlinetracker.notification.NotificationScheduler;
import com.example.pszen.deadlinetracker.adapter.CustomSpinnerAdapter;
import com.example.pszen.deadlinetracker.util.Util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class CreateActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener,View.OnClickListener,AdapterView.OnItemSelectedListener{
    private Button button;
    private Context context;
    private EditText message;
    private EditText description;
    private EditText date;
    private EditText time;
    private Spinner spinner;
    DatabaseHandler db;
    Calendar notificationCalendar;
    String timeSetString;
    String dateSetString;
    String messageString;
    String descriptionString;
    SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
    int notificationID;
    int pictogram;
    SharedPreferences sharedPref;
    SharedPreferences.Editor editor;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);

        message = (EditText) findViewById(R.id.create_message_editText) ;
        description = (EditText) findViewById(R.id.create_description_editText);
        date = (EditText) findViewById(R.id.create_date_editText);
        time = (EditText) findViewById(R.id.create_time_editText);
        spinner = (Spinner) findViewById(R.id.create_spinner);

        CustomSpinnerAdapter customSpinnerAdapter = new CustomSpinnerAdapter(getApplicationContext(), Util.pictograms);
        spinner.setAdapter(customSpinnerAdapter);
        spinner.setOnItemSelectedListener(this);

        db = new DatabaseHandler(this);
        notificationCalendar = Calendar.getInstance();

        sharedPref = getSharedPreferences("notification",Context.MODE_PRIVATE);
        editor = sharedPref.edit();
        notificationID = sharedPref.getInt("notificationID", 0);


        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogFragment datePicker = new DatePickerFragment();
                datePicker.show(getSupportFragmentManager(),"date picker");
            }
        });

        time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogFragment timePicker = new TimePickerFragment();
                timePicker.show(getSupportFragmentManager(),"time picker");
            }
        });



        button = (Button) findViewById(R.id.create_button);
        button.setOnClickListener(this);
    }

    @Override
    public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
        notificationCalendar.set(Calendar.YEAR,year);
        notificationCalendar.set(Calendar.MONTH,month);
        notificationCalendar.set(Calendar.DAY_OF_MONTH,dayOfMonth);
        dateSetString = DateFormat.getDateInstance(DateFormat.LONG).format(notificationCalendar.getTime());

        date.setText(dateSetString);

    }


    @Override
    public void onTimeSet(TimePicker timePicker, int hour, int minute) {
        notificationCalendar.set(Calendar.HOUR_OF_DAY,hour);
        notificationCalendar.set(Calendar.MINUTE,minute);
        notificationCalendar.set(Calendar.SECOND,0);

        timeSetString = sdf.format(notificationCalendar.getTime());

        time.setText(timeSetString);
    }

    public void newReminder(){

        Reminder reminder = new Reminder(
                String.valueOf(message.getText()),
                notificationCalendar.getTimeInMillis(),
                String.valueOf(description.getText()),
                notificationID,
                pictogram);
        db.addReminder(reminder);


    }

    @Override
    public void onClick(View view) {
        newReminder();
        setReminder();
    }


    public void setReminder(){
        InputMethodManager inputManager = (InputMethodManager)
                getSystemService(Context.INPUT_METHOD_SERVICE);

        inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),
                InputMethodManager.HIDE_NOT_ALWAYS);

        Intent intent = new Intent(this, MainActivity.class);
        NotificationScheduler.setReminder(this,
                notificationCalendar.getTimeInMillis(),
                String.valueOf(message.getText()),
                String.valueOf(description.getText()),
                pictogram,
                notificationID);
        notificationID++;
        editor.putInt("notificationID",notificationID);
        editor.apply();
        startActivity(intent);
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        Log.d("OnItemSelected: ", String.valueOf(adapterView.getItemAtPosition(i)));
        pictogram = (int) adapterView.getItemAtPosition(i);
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
        Log.d("OnNothingSelected: ", "OK");
    }


//    public void startAlert() {
//        String messageString = String.valueOf(message.getText());
//        String descriptionString = String.valueOf(description.getText());
//
//        Intent intent = new Intent(this, AlarmReceiver.class);
//        intent.putExtra("message",String.valueOf(messageString));
//        intent.putExtra("description",String.valueOf(descriptionString));
//
//        PendingIntent pendingIntent = PendingIntent.getBroadcast(
//                this.getApplicationContext(), 0, intent, 0);
//        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
//        alarmManager.set(AlarmManager.RTC_WAKEUP, notificationCalendar.getTimeInMillis(), pendingIntent);
//        Toast.makeText(this, "Alarm set for " + dateSetString+ ".",Toast.LENGTH_LONG).show();
//    }

//    public static void setReminder(Context context,Class<?> cls, Calendar calendar, int nId){
//        ComponentName receiver = new ComponentName(context,cls);
//        PackageManager pm = context.getPackageManager();
//        Calendar cal = Calendar.getInstance();
//
//        pm.setComponentEnabledSetting(receiver,
//                PackageManager.COMPONENT_ENABLED_STATE_ENABLED,
//                PackageManager.DONT_KILL_APP);
//
//        Intent intent1 = new Intent(context,cls);
//        PendingIntent pendingIntent = PendingIntent.getBroadcast(context,
//                0,
//                intent1,
//                PendingIntent.FLAG_UPDATE_CURRENT);
//        AlarmManager am = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
//        am.set(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis()*5000,pendingIntent);
//        Toast.makeText(context,"Notification set", Toast.LENGTH_LONG).show();
//    }





}
