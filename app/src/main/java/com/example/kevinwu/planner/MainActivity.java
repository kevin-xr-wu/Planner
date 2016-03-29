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

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

//ideas
//save checkbox states - hard
//calendar onDaySelect should create a new activity, everything should be saved tho. - medium if read?
// done!

public class MainActivity extends AppCompatActivity{

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
        if(extras != null) {
            String dateText = extras.getString(Calendar.DATE);
            DateFormat inputFormat = new SimpleDateFormat("MM/dd/yyyy");
            Date date = new Date();
            try {
                date = inputFormat.parse(dateText);
            } catch (ParseException e) {
                e.printStackTrace();
            }

            dateOutput = new SimpleDateFormat("EEEE, MMMM dd").format(date);
        }
        else{
            dateOutput = new SimpleDateFormat("EEEE, MMMM dd").format(new Date());
        }
        Button button = (Button) findViewById(R.id.calendarButton);
        button.setText(dateOutput);
        ListAdapter listAdapter;
        ListView listView;

        TASKS = dateOutput;
        MY_PREFS_NAME = dateOutput;

        //displaying sharedPref data
        SharedPreferences sharedPref = getSharedPreferences(MY_PREFS_NAME, Context.MODE_PRIVATE);
        boolean isInitialized = sharedPref.getBoolean(PREF_INITIALIZED , false);
        if(isInitialized)
        {
            Set<String> set = sharedPref.getStringSet(TASKS, null);
            String[] taskArray = new String[set.size()];
            set.toArray(taskArray);

            for(int i = 0; i < taskArray.length; i++)
            {
                TaskInfo info = new TaskInfo();
                info.setTask(taskArray[i]);
                info.setIsChecked(false);
                taskList.add(info);
            }

            listAdapter = new CustomAdapter(this, taskArray);
            listView = (ListView) findViewById(R.id.list);
            listView.setAdapter(listAdapter);
        }

        if(savedInstanceState != null)
        {
            taskList = savedInstanceState.getParcelableArrayList(STATE_KEY);
            String[] mTasks = new String[taskList.size()];
            for(int i = 0; i < taskList.size(); i++)
            {
                mTasks[i] = taskList.get(i).getTask();
            }
            listAdapter = new CustomAdapter(this, mTasks);
            listView = (ListView) findViewById(R.id.list);
            listView.setAdapter(listAdapter);
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelableArrayList(STATE_KEY, taskList);
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        return super.onCreateOptionsMenu(menu);
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        switch(item.getItemId())
//        {
//            case android.R.id.home:
//                NavUtils.navigateUpFromSameTask(this);
//                return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }

    public void createTask(View view) {
        Intent intent = new Intent(this, Task.class);
        startActivityForResult(intent, 1);
    }

    public void deleteTask(View view) {
        Intent intent2 = new Intent (this, RemoveTask.class);
        startActivityForResult(intent2, DELETE_REQUEST);
    }

    public void toCalendar(View view){
        Intent intent = new Intent(this, Calendar.class);
        startActivity(intent);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        String[] mTasks = new String[taskList.size() + 1];
        if(resultCode == RESULT_OK && requestCode == 1 && data != null) {

            String task = data.getStringExtra(Task.EXTRA_MESSAGE);
            TaskInfo info = new TaskInfo();
            info.setTask(task);
            info.setIsChecked(false);
            taskList.add(info);

            for(int i = 0; i < taskList.size(); i++)
            {
                mTasks[i] = taskList.get(i).getTask();
            }

            ListAdapter listAdapter = new CustomAdapter(this, mTasks);
            ListView listView = (ListView) findViewById(R.id.list);
            listView.setAdapter(listAdapter);

        }

        if(resultCode == RESULT_OK && requestCode == DELETE_REQUEST && data != null) {

            String remove = data.getStringExtra(RemoveTask.EXTRA_STRING);
            for(int i = 0; i < taskList.size(); i++)
            {
                if(remove.equals(taskList.get(i).getTask()))
                {
                    taskList.remove(i);
                }
            }

            for(int i = 0; i < taskList.size(); i++)
            {
                mTasks[i] = taskList.get(i).getTask();
            }

            ListAdapter listAdapter = new CustomAdapter(this, mTasks);
            ListView listView = (ListView) findViewById(R.id.list);
            listView.setAdapter(listAdapter);
        }

        //shared preferences, saves after the listview adapter is set
        SharedPreferences prefs = getSharedPreferences(MY_PREFS_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();

        editor.putBoolean(PREF_INITIALIZED, true);

        Set<String> set = new HashSet<>();
        for(int i = 0; i < taskList.size(); i++)
        {
            set.add(taskList.get(i).getTask());
        }
        editor.putStringSet(TASKS, set);
        editor.apply();

//        editor.clear();
//        editor.commit();

    }

}
