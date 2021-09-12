package com.example.mobile_hw6_ex2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.RatingBar;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    SeekBar seekBar;
    RatingBar ratingBar;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        seekBar = (SeekBar) findViewById(R.id.seekBar);
        ratingBar = (RatingBar) findViewById(R.id.ratingBar);
        textView = (TextView) findViewById(R.id.textView);

        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                textView.setText(new String().format("rating : %2.1f, from user : %b", rating, fromUser));
                if(rating == 0.5)
                    seekBar.setProgress(10);
                else if(rating == 1.0)
                    seekBar.setProgress(20);
                else if(rating == 1.5)
                    seekBar.setProgress(30);
                else if(rating == 2.0)
                    seekBar.setProgress(40);
                else if(rating == 2.5)
                    seekBar.setProgress(50);
                else if(rating == 3.0)
                    seekBar.setProgress(60);
                else if(rating == 3.5)
                    seekBar.setProgress(70);
                else if(rating == 4.0)
                    seekBar.setProgress(80);
                else if(rating == 4.5)
                    seekBar.setProgress(90);
                else if(rating == 5.0)
                    seekBar.setProgress(100);
            }
        });

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                textView.setText(new String().format("SeekBar Progress : %d, from user : %b", progress, fromUser));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }
}