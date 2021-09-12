package com.example.mobile_hw9_ex2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.net.Inet4Address;

public class MainActivity extends AppCompatActivity {

    TextView textView1, textView2, textView3;
    ImageView imageView1, imageView2, imageView3;
    Button resultButton;

    int count1, count2, count3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView1 = (TextView)findViewById(R.id.textView1);
        textView2 = (TextView)findViewById(R.id.textView2);
        textView3 = (TextView)findViewById(R.id.textView3);

        imageView1 = (ImageView)findViewById(R.id.imageView1);
        imageView2 = (ImageView)findViewById(R.id.imageView2);
        imageView3 = (ImageView)findViewById(R.id.imageView3);

        resultButton = (Button)findViewById(R.id.re_Button);

        imageView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView1.setText(String.valueOf(++count1));
            }
        });
        imageView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView2.setText(String.valueOf(++count2));
            }
        });
        imageView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView3.setText(String.valueOf(++count3));
            }
        });

        resultButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(MainActivity.this, ResultActivity.class);
                intent1.putExtra("count1", count1);
                intent1.putExtra("count2", count2);
                intent1.putExtra("count3", count3);
                startActivityForResult(intent1, 111);
                System.out.println("aaaaaaaaaaaaaaaa");
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        if(data != null){
            if(resultCode == RESULT_OK) {
                count1 = (int) data.getFloatExtra("ratingBar1", 0);
                count2 = (int) data.getFloatExtra("ratingBar2", 0);
                count3 = (int) data.getFloatExtra("ratingBar3", 0);

                textView1.setText(String.valueOf(count1));
                textView2.setText(String.valueOf(count2));
                textView3.setText(String.valueOf(count3));
            }
            else {
                Toast.makeText(getApplicationContext(), "resultCode error", Toast.LENGTH_SHORT).show();
            }
        }
    }
}