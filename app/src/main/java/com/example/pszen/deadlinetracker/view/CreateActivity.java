package com.example.pszen.deadlinetracker.view;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;

import com.example.pszen.deadlinetracker.R;
import com.example.pszen.deadlinetracker.data.DatabaseHandler;
import com.example.pszen.deadlinetracker.model.DatePickerFragment;
import com.example.pszen.deadlinetracker.model.Reminder;
import com.example.pszen.deadlinetracker.model.TimePickerFragment;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.List;

public class CreateActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener{
    private Button button;
    private EditText message;
    private EditText description;
    private EditText date;
    private EditText time;
    DatabaseHandler db;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);

        message = (EditText) findViewById(R.id.create_message_editText) ;
        description = (EditText) findViewById(R.id.create_description_editText);
        date = (EditText) findViewById(R.id.create_date_editText);
        time = (EditText) findViewById(R.id.create_time_editText);

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

        db = new DatabaseHandler(this);

        button = (Button) findViewById(R.id.create_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Log.d("Insert:", "Inserting...");
                db.addReminder(new Reminder("Urodziny","07/11/2018 9:00", "Moje urodziny"));
                db.addReminder(new Reminder("OC","02/01/2019 10:00", "Siena"));
                db.addReminder(new Reminder("Umowa PLAY","02/03/2018 14:30", ""));
                db.addReminder(new Reminder("Wymiana oleju","23/05/2018 13:00", "Mercedes"));

                Log.d("Reading: ","Reading all contacts...");
                List<Reminder> reminderList = db.getAllReminders();

                for(Reminder r: reminderList){
                    String log = "ID: " + r.getId() + " , Message" + r.getMessage() + ", Date and time" + r.getDateAndTime() + ", Description" + r.getDescription();

                    Log.d("Name: ", log);
                }
            }
        });



    }

    @Override
    public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR,year);
        cal.set(Calendar.MONTH,month);
        cal.set(Calendar.DAY_OF_MONTH,dayOfMonth);
        String currentDateString = DateFormat.getDateInstance(DateFormat.LONG).format(cal.getTime());

        date.setText(currentDateString);

    }


    @Override
    public void onTimeSet(TimePicker timePicker, int hour, int minute) {
        String currentTimeString = String.valueOf(hour)+":"+ String.valueOf(minute);

        time.setText(currentTimeString);
    }
}
