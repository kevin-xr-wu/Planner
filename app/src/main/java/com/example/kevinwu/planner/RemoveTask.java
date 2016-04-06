package com.example.kevinwu.planner;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class RemoveTask extends AppCompatActivity {

    public final static String EXTRA_STRING = "removeTask";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remove_task);
    }

    public void removeTask(View view){
        Intent intent = new Intent();
        EditText editText = (EditText) findViewById(R.id.deleteInput);
        String remove = editText.getText().toString();
        intent.putExtra(EXTRA_STRING, remove);
        setResult(MainActivity.RESULT_OK, intent);
        finish();
    }

}
