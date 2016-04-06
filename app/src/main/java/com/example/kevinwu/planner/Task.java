package com.example.kevinwu.planner;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

public class Task extends AppCompatActivity {

    private final static String TAG = "Task";

    public final static String EXTRA_MESSAGE = "com.kevinwu.Planner.MESSAGE";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task);
    }

    public void sendBackTask(View view){
        Intent intent = new Intent();
        EditText editText = (EditText) findViewById(R.id.newTask);
        String task = editText.getText().toString();
        intent.putExtra(EXTRA_MESSAGE, task);
        setResult(MainActivity.RESULT_OK, intent);
        finish();
    }

}
