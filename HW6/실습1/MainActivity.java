package com.example.mobile_hw6_ex1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.CalendarView;
import android.widget.Chronometer;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    Chronometer chronometer;
    CalendarView calendarView;
    TextView textView;

    private SimpleDateFormat mFormat = new SimpleDateFormat("yyyy / M / d");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        chronometer = (Chronometer) findViewById(R.id.chronometer);
        calendarView = (CalendarView) findViewById(R.id.calendarView);
        textView = (TextView) findViewById(R.id.textView);

        Date date = new Date();
        textView.setText(mFormat.format(date));

        CalendarView.OnDateChangeListener mDateChangeListener = new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int day) {
                textView.setText(new String().format("%d / %d / %d", year, month+1, day));
            }
        };
        calendarView.setOnDateChangeListener(mDateChangeListener);
    }

    public  void chronoStartClick(View v){
        chronometer.setBase(SystemClock.elapsedRealtime());
        chronometer.setTextColor(Color.rgb(0,0,0));

        chronometer.start();
    }

    public  void chronoStopClick(View v){
        chronometer.stop();
        chronometer.setTextColor(Color.rgb(255,0,0));
    }

}