package com.example.pszen.deadlinetracker.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.pszen.deadlinetracker.R;
import com.example.pszen.deadlinetracker.adapter.CustomAdapter;
import com.example.pszen.deadlinetracker.data.DatabaseHandler;
import com.example.pszen.deadlinetracker.model.ListItem;
import com.example.pszen.deadlinetracker.model.Reminder;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private List<Reminder> reminderList;
    private RecyclerView.Adapter adapter;
    DatabaseHandler db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FloatingActionButton floatingActionButton = (FloatingActionButton) findViewById(R.id.floatingActionButton);
        recyclerView = (RecyclerView) findViewById(R.id.main_recycler_view);
        recyclerView.setHasFixedSize(true);
        //every item has a fixed size
        recyclerView.setLayoutManager(new
                LinearLayoutManager(this));


        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), CreateActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

        db = new DatabaseHandler(this);
        reminderList = db.getAllReminders();

        adapter = new CustomAdapter(this, reminderList);
        recyclerView.setAdapter(adapter);
    }


}
