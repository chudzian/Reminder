package com.example.pszen.deadlinetracker.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.pszen.deadlinetracker.R;

import org.w3c.dom.Text;

public class DetailActivity extends AppCompatActivity {
    private TextView message;
    private TextView description;
    private TextView dateAndTime;
    private Bundle extras;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        extras = getIntent().getExtras();

        message = (TextView)findViewById(R.id.detail_message);
        description = (TextView)findViewById(R.id.detail_description);
        dateAndTime = (TextView)findViewById(R.id.detail_dateAndTime);



        if(extras!=null){
            message.setText(extras.getString("message"));
            dateAndTime.setText(extras.getString("dateAndTime"));
            description.setText(extras.getString("description"));
        }
    }
}
