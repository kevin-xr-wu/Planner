package com.example.kevinwu.planner;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MainActivity extends AppCompatActivity {

    public ArrayList<TaskInfo> taskList = new ArrayList<>();
    static final String STATE_KEY = "taskListKey";
    public static final int DELETE_REQUEST = 2;
    public static String MY_PREFS_NAME;
    public static final String PREF_INITIALIZED = "init";
    public static String TASKS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String dateOutput;
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String dateText = extras.getString(Calendar.DATE);
            DateFormat inputFormat = new SimpleDateFormat("MM/dd/yyyy");
            Date date = new Date();
            try {
                date = inputFormat.parse(dateText);
            } catch (ParseException e) {
                e.printStackTrace();
            }

            dateOutput = new SimpleDateFormat("EEEE, MMMM dd").format(date);
        } else {
            dateOutput = new SimpleDateFormat("EEEE, MMMM dd").format(new Date());
        }

        Button button = (Button) findViewById(R.id.calendarButton);
        button.setText(dateOutput);

        ListAdapter listAdapter;
        ListView listView;

        TASKS = dateOutput;
        MY_PREFS_NAME = dateOutput;

//        //displaying sharedPref data
        SharedPreferences sharedPref = getSharedPreferences(MY_PREFS_NAME, Context.MODE_PRIVATE);
        boolean isInitialized = sharedPref.getBoolean(PREF_INITIALIZED, false);
        if (isInitialized) {
            JSONArray jsonArray = new JSONArray();
            String data = sharedPref.getString(TASKS, null);
            try {
                jsonArray = new JSONArray(data);
            } catch (JSONException e) {
                e.printStackTrace();
            }

            for (int i = 0; i < jsonArray.length(); i++) {
                try {
                    TaskInfo taskInfo = new TaskInfo(jsonArray.getJSONObject(i));
                    taskList.add(taskInfo);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            TaskInfo[] taskArray = new TaskInfo[taskList.size()];
            taskArray = taskList.toArray(taskArray);

            listAdapter = new CustomAdapter(this, taskArray);
            listView = (ListView) findViewById(R.id.list);
            listView.setAdapter(listAdapter);

       }

        if (savedInstanceState != null) {
            taskList = savedInstanceState.getParcelableArrayList(STATE_KEY);
            TaskInfo[] mTasks = new TaskInfo[taskList.size()];
            mTasks = taskList.toArray(mTasks);
            listAdapter = new CustomAdapter(this, mTasks);
            listView = (ListView) findViewById(R.id.list);
            listView.setAdapter(listAdapter);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        //shared preferences, saves after the listview adapter is set
        SharedPreferences prefs = getSharedPreferences(MY_PREFS_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();

        editor.putBoolean(PREF_INITIALIZED, true);

        JSONArray jsonArray = new JSONArray();
        for (int i = 0; i < taskList.size(); i++) {
            jsonArray.put(taskList.get(i).getJSONObject());
        }
        String jsonTask = jsonArray.toString();
        editor.putString(TASKS, jsonTask);
        editor.apply();

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelableArrayList(STATE_KEY, taskList);
    }

    public void createTask(View view) {
        Intent intent = new Intent(this, Task.class);
        startActivityForResult(intent, 1);
    }

    public void deleteTask(View view) {
        Intent intent2 = new Intent(this, RemoveTask.class);
        startActivityForResult(intent2, DELETE_REQUEST);
    }

    public void toCalendar(View view) {
        Intent intent = new Intent(this, Calendar.class);
        startActivity(intent);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        TaskInfo[] mTasks = new TaskInfo[taskList.size() + 1];
        if (resultCode == RESULT_OK && requestCode == 1 && data != null) {

            String task = data.getStringExtra(Task.EXTRA_MESSAGE);
            TaskInfo info = new TaskInfo();
            info.setTask(task);
            info.setIsChecked(false);
            taskList.add(info);

            mTasks = taskList.toArray(mTasks);

            ListAdapter listAdapter = new CustomAdapter(this, mTasks);
            ListView listView = (ListView) findViewById(R.id.list);
            listView.setAdapter(listAdapter);

        }

        if (resultCode == RESULT_OK && requestCode == DELETE_REQUEST && data != null) {

            String toBeRemoved = data.getStringExtra(RemoveTask.EXTRA_STRING);
            for (int i = 0; i < taskList.size(); i++) {
                if (toBeRemoved.equals(taskList.get(i).getTask())) {
                    taskList.remove(i);
                }
            }
            TaskInfo[] myTasks = new TaskInfo[taskList.size()];
            myTasks = taskList.toArray(myTasks);

            ListAdapter listAdapter = new CustomAdapter(this, myTasks);
            ListView listView = (ListView) findViewById(R.id.list);
            listView.setAdapter(listAdapter);
        }

    }

}
