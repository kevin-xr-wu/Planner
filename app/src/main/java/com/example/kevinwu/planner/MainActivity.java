package com.example.kevinwu.planner;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

// implement checkboxes savestate,
// implement saving the tasks and whether they are checked or not
// calendar api

public class MainActivity extends AppCompatActivity {

    public ArrayList<String> taskList = new ArrayList<>();
    static final String CHECK_KEY = "checkBoxKey";
    static final String STATE_KEY = "taskListKey";
    boolean isChecked;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(savedInstanceState != null)
        {
            taskList = savedInstanceState.getStringArrayList(STATE_KEY);
            String[] mTasks = new String[taskList.size()];
            mTasks = taskList.toArray(mTasks);
            ListAdapter listAdapter = new CustomAdapter(this, mTasks);
            ListView listView = (ListView) findViewById(R.id.list);
            listView.setAdapter(listAdapter);
        }
        //Log.i(TAG, "OnCreate");

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putStringArrayList(STATE_KEY, taskList);

    }

    public void createTask(View view) {
        Intent intent = new Intent(this, Task.class);
        //Log.i(TAG, "CreateTask");
        startActivityForResult(intent, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        //Log.i(TAG, "onActivityResult");
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK && requestCode == 1 && data != null) {

            String task = data.getStringExtra(Task.EXTRA_MESSAGE);
            taskList.add(task);
            //Log.i(TAG, task);
            String[] mTasks = new String[taskList.size()];
            mTasks = taskList.toArray(mTasks);
            //Log.i(TAG, task);
            ListAdapter listAdapter = new CustomAdapter(this, mTasks);
            ListView listView = (ListView) findViewById(R.id.list);

//        listView.setOnItemClickListener(
//                new AdapterView.OnItemClickListener() {
//                    @Override
//                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                        String food = String.valueOf(parent.getItemAtPosition(position));
//                        Toast.makeText(MainActivity.this, food, Toast.LENGTH_LONG).show();
//                    }
//
//                }
//        );

            listView.setAdapter(listAdapter);
        }
    }
}
