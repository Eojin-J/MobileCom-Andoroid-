package com.example.mobile_hw4_test1;

import android.graphics.Matrix;
import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    CheckBox check1, check2;
    RadioGroup rGroup1, rGroup2;
    RadioButton a, b, c;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        check1 = (CheckBox)findViewById(R.id.twice);
        check2 = (CheckBox)findViewById(R.id.bts);

        check1.setOnCheckedChangeListener(mCheckListener);
        check2.setOnCheckedChangeListener(mCheckListener);

        rGroup1 = (RadioGroup) findViewById(R.id.rGroup1);
        rGroup2 = (RadioGroup) findViewById(R.id.rGroup2);

        a = (RadioButton) findViewById(R.id.a);
        b = (RadioButton) findViewById(R.id.b);
        c = (RadioButton) findViewById(R.id.c);

        imageView = (ImageView) findViewById(R.id.imageView);

        rGroup1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(check1.isChecked()){
                    if(checkedId == R.id.a){
                        imageView.setImageResource(R.drawable.da);
                    }
                    if(checkedId == R.id.b){
                        imageView.setImageResource(R.drawable.sa);
                    }
                    if(checkedId == R.id.c) {
                        imageView.setImageResource(R.drawable.ch);
                    }
                }

                if(check2.isChecked()){
                    if(checkedId == R.id.a){
                        imageView.setImageResource(R.drawable.jung);
                    }
                    if(checkedId == R.id.b){
                        imageView.setImageResource(R.drawable.ji);
                    }
                    if(checkedId == R.id.c){
                        imageView.setImageResource(R.drawable.v);
                    }
                }

            }
        });

        rGroup2.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                    if(checkedId == R.id.matrix){
                        imageView.setScaleType(ImageView.ScaleType.MATRIX);
                        Matrix matrix = imageView.getImageMatrix();
                        float scale = 2.0f;
                        matrix.setScale(scale, scale);
                        imageView.setImageMatrix(matrix);
                    }
                    if(checkedId == R.id.fitXY){
                        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                    }
                    if(checkedId == R.id.fitCenter) {
                        imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
                    }
                    if(checkedId == R.id.center) {
                        imageView.setScaleType(ImageView.ScaleType.CENTER);
                    }
            }

        });
    }

    CompoundButton.OnCheckedChangeListener mCheckListener = new CompoundButton.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

            if(buttonView.getId() == R.id.twice){
               if(isChecked){
                   check2.setChecked(false);
                   rGroup1.clearCheck();
                   imageView.setImageResource(0);
                   a.setText("다현");
                   b.setText("사나");
                   c.setText("채영");
               }
               else{
                   rGroup1.clearCheck();
                   imageView.setImageResource(0);
                   a.setText("A");
                   b.setText("B");
                   c.setText("C");
               }
           }

            if(buttonView.getId() == R.id.bts){
                if(isChecked){
                    check1.setChecked(false);
                    rGroup1.clearCheck();
                    imageView.setImageResource(0);
                    a.setText("정국");
                    b.setText("지민");
                    c.setText("뷔");
                }
                else{
                    rGroup1.clearCheck();
                    imageView.setImageResource(0);
                    a.setText("A");
                    b.setText("B");
                    c.setText("C");
                }
            }

        }
    };

}