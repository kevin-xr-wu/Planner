package com.example.kevinwu.planner;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.view.SupportActionModeWrapper;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.andexert.calendarlistview.library.DayPickerView;
import com.andexert.calendarlistview.library.SimpleMonthAdapter;


public class Calendar extends Activity implements com.andexert.calendarlistview.library.DatePickerController {

    public DayPickerView dayPickerView;
    private final static String TAG = "CALENDAR";
    public final static String DATE = "date";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);

        dayPickerView = (DayPickerView) findViewById(R.id.pickerView);
        dayPickerView.setController(this);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId())
        {
            case android.R.id.home:
                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public int getMaxYear()
    {
        return 2050;
    }

    @Override
    public void onDayOfMonthSelected(int year, int month, int day)
    {
        Intent intent = new Intent(this, MainActivity.class);
        String date = "0" + (month+1) + "/"+ day + "/" + year;
        intent.putExtra(DATE, date);
        startActivity(intent);
    }

    @Override
    public void onDateRangeSelected(SimpleMonthAdapter.SelectedDays<SimpleMonthAdapter.CalendarDay> selectedDays)
    {

        Log.e("Date range selected", selectedDays.getFirst().toString() + " --> " + selectedDays.getLast().toString());
    }
}