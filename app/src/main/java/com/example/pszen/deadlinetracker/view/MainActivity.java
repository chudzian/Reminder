package com.example.pszen.deadlinetracker.view;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.BaseTransientBottomBar;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.pszen.deadlinetracker.R;
import com.example.pszen.deadlinetracker.adapter.CustomAdapter;
import com.example.pszen.deadlinetracker.data.DatabaseHandler;
import com.example.pszen.deadlinetracker.model.Reminder;
import com.example.pszen.deadlinetracker.notification.NotificationScheduler;
import com.example.pszen.deadlinetracker.util.Controller;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private List<Reminder> reminderList;
    private RecyclerView.Adapter adapter;
    private DatabaseHandler db;
    private int tempPosition;
    private Reminder tempReminder;
    private SharedPreferences sharedPref;
    private SharedPreferences.Editor editor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FloatingActionButton floatingActionButton = (FloatingActionButton) findViewById(R.id.floatingActionButton);
        recyclerView = (RecyclerView) findViewById(R.id.main_recycler_view);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        sharedPref = getSharedPreferences("notification", Context.MODE_PRIVATE);
        editor = sharedPref.edit();
//        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),new LinearLayoutManager(recyclerView.getContext()).getOrientation());
//        recyclerView.addItemDecoration(dividerItemDecoration);

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(createHelperCallback());
        itemTouchHelper.attachToRecyclerView(recyclerView);



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
        reminderList = db.getAllRemindersAscending();

        adapter = new CustomAdapter(this, reminderList);
        recyclerView.setAdapter(adapter);
    }

    private ItemTouchHelper.Callback createHelperCallback(){
        ItemTouchHelper.SimpleCallback simpleItemTouchCallback =
                new ItemTouchHelper.SimpleCallback(0,
                ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                Log.d("ONSWIPED","OK");
                int position = viewHolder.getAdapterPosition();
                tempPosition = position;
                tempReminder = reminderList.get(position);
                reminderList.remove(position);
                db.removeById(tempReminder.getId());
                adapter.notifyItemRemoved(position);
                NotificationScheduler.cancelNotification(getApplicationContext(),tempReminder.getNotificationId());
                showUndoSnackbar();
            }
        };
        return simpleItemTouchCallback;
    }
    public void showUndoSnackbar(){
        Snackbar.make(
                findViewById(R.id.main_constraint_layout),
                getString(R.string.action_delete_item),
                Snackbar.LENGTH_LONG
        ).setAction(R.string.action_undo, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("ONCLICK:", "OK");
                int notificationID = sharedPref.getInt("notificationID",0);
                db.addReminder(tempReminder);
                reminderList.add(tempPosition,tempReminder);
                adapter.notifyItemInserted(tempPosition);
                NotificationScheduler.setReminder(
                        getApplicationContext(),
                        tempReminder.getDateAndTime(),
                        tempReminder.getMessage(),
                        tempReminder.getMessage(),
                        tempReminder.getPictogram(),
                        notificationID);
                notificationID++;
                editor.putInt("notificationID",notificationID);
                editor.apply();
            }
        }).addCallback(new BaseTransientBottomBar.BaseCallback<Snackbar>() {
            @Override
            public void onDismissed(Snackbar transientBottomBar, int event) {
                super.onDismissed(transientBottomBar, event);
                Log.d("ONDISMISSED", "OK");
            }
        }).show();
    }



}
