package com.example.pszen.deadlinetracker.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pszen.deadlinetracker.R;
import com.example.pszen.deadlinetracker.model.ListItem;
import com.example.pszen.deadlinetracker.model.Reminder;
import com.example.pszen.deadlinetracker.view.DetailActivity;

import java.util.ArrayList;
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
        holder.dateAndTime.setText(reminder.getDateAndTime());

    }

    @Override
    public int getItemCount() {
        return reminderList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView message;
        public TextView dateAndTime;
        public TextView description;

        public ViewHolder(View view, Context ctx, ArrayList<Reminder> items) {
            super(view);
            reminderList = items;
            //get the Activity Context
            context = ctx;

            view.setOnClickListener(this);

            message = (TextView) view.findViewById(R.id.message_textView);
            dateAndTime = (TextView) view.findViewById(R.id.dateAndTime_textView);

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

            context.startActivity(intent);
        }
    }
}
