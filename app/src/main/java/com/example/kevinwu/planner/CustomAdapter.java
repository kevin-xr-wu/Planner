package com.example.kevinwu.planner;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Kevin Wu on 2/15/2016.
 */
class CustomAdapter extends ArrayAdapter<String> {
    public CustomAdapter(Context context, String[] tasks) {
        super(context, R.layout.custom_row, tasks);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View customView = inflater.inflate(R.layout.custom_row, parent, false);

        String singleTask = getItem(position);
        TextView view = (TextView) customView.findViewById(R.id.textView);

        view.setText(singleTask);
        return customView;
    }
}
