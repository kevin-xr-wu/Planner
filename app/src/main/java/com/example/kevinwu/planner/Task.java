package com.example.kevinwu.planner;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Task extends AppCompatActivity {

    private ArrayList<String> mTasks;

    public final static String EXTRA_MESSAGE = "com.kevinwu.Planner.MESSAGE";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task);

        Bundle value = getIntent().getExtras();
        mTasks = value.getStringArrayList(MainActivity.STATE_BUNDLE_TASKS);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public void sendBackTask(){
        //Intent intent = new Intent();
        EditText editText = (EditText) findViewById(R.id.newTask);
        // String task = editText.getText().toString();
        // intent.putExtra(EXTRA_MESSAGE, task);
        mTasks.add(editText.getText().toString());
        //setResult(RESULT_OK, intent);
        finish();
    }

}
