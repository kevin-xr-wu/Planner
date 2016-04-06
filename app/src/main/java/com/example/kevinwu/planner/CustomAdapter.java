package com.example.kevinwu.planner;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

/**
 * Created by Kevin Wu on 2/15/2016.
 */
class CustomAdapter extends ArrayAdapter <TaskInfo> {

    public CustomAdapter(Context context, TaskInfo[] tasks) {
        super(context, R.layout.custom_row, tasks);
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View customView = inflater.inflate(R.layout.custom_row, parent, false);

        TaskInfo singleTask = getItem(position);

        TextView view = (TextView) customView.findViewById(R.id.textView);
        view.setText(singleTask.getTask());

        CheckBox checkbox = (CheckBox) customView.findViewById(R.id.checkBox);
        checkbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                TaskInfo checkTask = getItem(position);// taskInfo.setChecked(isChecked);
                checkTask.setIsChecked(isChecked);
            }
        });

        checkbox.setChecked(singleTask.getIsChecked());

        return customView;
    }

}
