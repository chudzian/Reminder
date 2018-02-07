package com.example.pszen.deadlinetracker.adapter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.example.pszen.deadlinetracker.R;

/**
 * Created by pszen on 26.01.2018.
 */

public class CustomSpinnerAdapter extends BaseAdapter {
    private Context mContext;
    private int[] pictograms;
    private LayoutInflater inflater;

    public CustomSpinnerAdapter(Context mContext, int[] pictograms) {
        this.mContext = mContext;
        this.pictograms = pictograms;
        inflater = (LayoutInflater.from(mContext));
    }

    @Override
    public int getCount() {
        return pictograms.length;
    }

    @Override
    public Object getItem(int i) {
        return pictograms[i];
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = inflater.inflate(R.layout.spinner_item,null);
        ImageView pictogram = view.findViewById(R.id.pictogram);
        pictogram.setImageResource(pictograms[i]);
        return view;
    }
}
