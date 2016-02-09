package com.example.kevinwu.planner;

import android.content.Intent;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.io.File;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<String> tasks;
    static final String STATE_STRING_LIST = "TaskList";
    static final String STATE_BUNDLE_TASKS = "TasksBundle";
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(savedInstanceState != null)
        {
            tasks = savedInstanceState.getStringArrayList(STATE_STRING_LIST);
        }
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState){
        savedInstanceState.putStringArrayList(STATE_STRING_LIST, tasks);
    }

    public void onRestoreInstanceState(Bundle savedInstanceState){
        super.onRestoreInstanceState(savedInstanceState);
        tasks = savedInstanceState.getStringArrayList(STATE_STRING_LIST);
    }

    public void createTask(View view) {
        Intent intent = new Intent(this, Task.class);
        Bundle mBundle = new Bundle();
        mBundle.putStringArrayList(STATE_BUNDLE_TASKS, tasks);
        intent.putExtras(mBundle);
//        startActivityForResult(intent, 1);
        startActivity(intent);
    }

    public void display(){
        if(tasks.isEmpty())
        {
            return;
        }
        else
        {
            for(String task : tasks)
            {
                TextView textView = new TextView(this);
                textView.setTextSize(30);
                textView.setText(task);
                LinearLayout layout = (LinearLayout) findViewById(R.id.content);
                layout.addView(textView);
            }
        }
    }
//    public void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        String task = data.getStringExtra(Task.EXTRA_MESSAGE);
//        if (requestCode == 1) {
//            if(resultCode == RESULT_OK){
//                String display_task = task;
//                TextView textView = new TextView(this);
//                textView.setTextSize(30);
//                textView.setText(display_task);
//                LinearLayout layout = (LinearLayout) findViewById(R.id.content);
//                layout.addView(textView);
//            }
//        }
//
//    }

}
