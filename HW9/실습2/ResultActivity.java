package com.example.mobile_hw9_ex2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;

import androidx.appcompat.app.AppCompatActivity;

public class ResultActivity extends AppCompatActivity {
    RatingBar ratingBar1, ratingBar2, ratingBar3;
    Button backButton;
    int count1, count2, count3;

    protected void onCreate(Bundle saveInstanceState){
        super.onCreate(saveInstanceState);
        setContentView(R.layout.activity_result);
        Intent intent1 = getIntent();

        count1=intent1.getIntExtra("count1", 0);
        count2=intent1.getIntExtra("count2", 0);
        count3=intent1.getIntExtra("count3", 0);

        ratingBar1=(RatingBar)findViewById(R.id.ratingBar1);
        ratingBar2=(RatingBar)findViewById(R.id.ratingBar2);
        ratingBar3=(RatingBar)findViewById(R.id.ratingBar3);

        backButton = (Button)findViewById(R.id.backButton);

        ratingBar1.setRating(count1);
        ratingBar2.setRating(count2);
        ratingBar3.setRating(count3);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(ResultActivity.this,MainActivity.class);
                intent2.putExtra("ratingBar1", ratingBar1.getRating());
                intent2.putExtra("ratingBar2", ratingBar2.getRating());
                intent2.putExtra("ratingBar3", ratingBar3.getRating());

                setResult(RESULT_OK,intent2);
                finish();
            }
        });
    }
}
