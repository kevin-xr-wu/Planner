package com.example.kevinwu.planner;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Task extends AppCompatActivity {

    private final static String TAG = "Task";

    public final static String EXTRA_MESSAGE = "com.kevinwu.Planner.MESSAGE";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task);

     //  getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Log.i(TAG, "task create");
    }

    public void sendBackTask(View view){
        Log.i(TAG, "sendBack");
        Intent intent = new Intent();
        EditText editText = (EditText) findViewById(R.id.newTask);
        String task = editText.getText().toString();
        intent.putExtra(EXTRA_MESSAGE, task);
        setResult(MainActivity.RESULT_OK, intent);
        finish();
    }

}
