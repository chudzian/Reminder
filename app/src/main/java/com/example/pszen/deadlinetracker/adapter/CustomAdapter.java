package com.example.pszen.deadlinetracker.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pszen.deadlinetracker.R;
import com.example.pszen.deadlinetracker.model.Reminder;
import com.example.pszen.deadlinetracker.util.Controller;
import com.example.pszen.deadlinetracker.util.Util;
import com.example.pszen.deadlinetracker.view.DetailActivity;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by pszen on 09.01.2018.
 */

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder> {
    private Context context;
    private List<Reminder> reminderList;


    public CustomAdapter(Context context, List reminderList) {
        this.context = context;
        this.reminderList = reminderList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_list_item, parent, false);

        return new ViewHolder(v, context, (ArrayList<Reminder>) reminderList);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Reminder reminder = reminderList.get(position);

        holder.message.setText(reminder.getMessage());
        holder.dateAndTime.setText(Controller.dateAndTimeString(reminder.getDateAndTime()));
        holder.pictogram.setImageResource(reminder.getPictogram());

    }

    @Override
    public int getItemCount() {
        return reminderList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView message;
        private TextView dateAndTime;
        private TextView description;
        private ImageView pictogram;

        public ViewHolder(View view, Context ctx, ArrayList<Reminder> items) {
            super(view);
            reminderList = items;
            //get the Activity Context
            context = ctx;

            view.setOnClickListener(this);

            message = (TextView) view.findViewById(R.id.message_textView);
            dateAndTime = (TextView) view.findViewById(R.id.dateAndTime_textView);
            pictogram = (ImageView) view.findViewById(R.id.pictogram_imageView);

        }

        @Override
        public void onClick(View v) {
            //Get int position
            int position = getAdapterPosition();
            Reminder item = reminderList.get(position);
            //  Intent intent = new Intent(context, MyActivity.class);
            Intent intent = new Intent(context, DetailActivity.class);
            intent.putExtra("message", item.getMessage());
            intent.putExtra("dateAndTime",item.getDateAndTime());
            intent.putExtra("description",item.getDescription());
            Log.d("id: ",String.valueOf(item.getId()));
            Log.d("message: ",String.valueOf(item.getMessage()));
            Log.d("dateAndTime: ",String.valueOf(item.getDateAndTime()));
            Log.d("description: ",String.valueOf(item.getDescription()));
            Log.d("notificationID: ",String.valueOf(item.getNotificationId()));
            Log.d("pictogram: ",String.valueOf(item.getPictogram()));

            context.startActivity(intent);
        }
    }
}
