package com.example.mobile_cal_test2;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private Button button_1;
    private Button button_2;
    private Button button_3;
    private Button button_plus;
    private Button button_4;
    private Button button_5;
    private Button button_6;
    private Button button_mul;
    private Button button_7;
    private Button button_8;
    private Button button_9;
    private Button button_div;
    private Button button_0;
    private Button button_eq;
    private Button button_cl;
    private Button button_sub;

    private TextView text;
    private double first=0;
    private  String  last ="", valueStr;
    private int where=0;
    private double value = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toast.makeText(getApplicationContext(),"Calculator",Toast.LENGTH_SHORT).show();

        button_1=(Button)findViewById(R.id.button_1);
        button_2=(Button)findViewById(R.id.button_2);
        button_3=(Button)findViewById(R.id.button_3);
        button_plus=(Button)findViewById(R.id.button_plus);
        button_4=(Button)findViewById(R.id.button_4);
        button_5=(Button)findViewById(R.id.button_5);
        button_6=(Button)findViewById(R.id.button_6);
        button_mul=(Button)findViewById(R.id.button_mul);
        button_7=(Button)findViewById(R.id.button_7);
        button_8=(Button)findViewById(R.id.button_8);
        button_9=(Button)findViewById(R.id.button_9);
        button_div=(Button)findViewById(R.id.button_div);
        button_0=(Button)findViewById(R.id.button_0);
        button_eq=(Button)findViewById(R.id.button_eq);
        button_cl=(Button)findViewById(R.id.button_cl);
        button_sub=(Button)findViewById(R.id.button_sub);

        text=(TextView) findViewById(R.id.text1);

        View.OnClickListener cl = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if (v == button_1) {
                        text.setText(text.getText().toString() + 1);
                        if (first != 0) {
                            last = last + "1";
                        }

                    } else if (v == button_2) {
                        text.setText(text.getText().toString() + 2);
                        if (first != 0) {
                            last = last + "2";
                        }
                    } else if (v == button_3) {
                        text.setText(text.getText().toString() + 3);
                        if (first != 0) {
                            last = last + "3";
                        }
                    } else if (v == button_plus) {
                        first = Integer.valueOf(text.getText().toString().trim());
                        //first = a;
                        text.setText(first + "+");
                        where = 1;
                    } else if (v == button_4) {
                        text.setText(text.getText().toString() + 4);
                        if (first != 0) {
                            last = last + "4";
                        }
                    } else if (v == button_5) {
                        text.setText(text.getText().toString() + 5);
                        if (first != 0) {
                            last = last + "5";
                        }
                    } else if (v == button_6) {
                        text.setText(text.getText().toString() + 6);
                        if (first != 0) {
                            last = last + "6";
                        }
                    } else if (v == button_mul) {
                        first = Double.valueOf(text.getText().toString().trim());
                        text.setText(first + "x");
                        where = 2;
                    } else if (v == button_7) {
                        text.setText(text.getText().toString() + 7);
                        if (first != 0) {
                            last = last + "7";
                        }
                    } else if (v == button_8) {
                        text.setText(text.getText().toString() + 8);
                        if (first != 0) {
                            last = last + "8";
                        }
                    } else if (v == button_9) {
                        text.setText(text.getText().toString() + 9);
                        if (first != 0) {
                            last = last + "9";
                        }
                    } else if (v == button_div) {
                        first = Double.valueOf(text.getText().toString().trim());
                        text.setText(first + "÷");
                        where = 3;
                    } else if (v == button_0) {
                        text.setText(text.getText().toString() + 0);
                        if (first != 0) {
                            last = last + "0";
                        }
                    } else if (v == button_eq) {
                        if (where == 1) {
                            value = first + Double.valueOf(last);
                            valueStr = Double.toString(value);
                            text.setText(text.getText().toString() + "=" + valueStr);
                        } else if (where == 2) {
                            value = first * Double.valueOf(last);
                            valueStr = Double.toString(value);
                            text.setText(text.getText().toString() + "=" + valueStr);
                        } else if (where == 3) {
                            value = first / Double.valueOf(last);
                            valueStr = Double.toString(value);
                            text.setText(text.getText().toString() + "=" + valueStr);
                        } else if (where == 4) {
                            value = first - Double.valueOf(last);
                            valueStr = Double.toString(value);
                            text.setText(text.getText().toString() + "=" + valueStr);
                        }
                    } else if (v == button_cl) {
                        text.setText("");
                    } else if (v == button_sub) {
                        first = Double.valueOf(text.getText().toString().trim());
                        text.setText(first + "-");
                        where = 4;
                    }
                } catch(Exception e) {
                    Toast.makeText(getApplicationContext(),"ERROR",Toast.LENGTH_SHORT).show();
                }
            }

        };
        button_1.setOnClickListener(cl);
        button_2.setOnClickListener(cl);
        button_3.setOnClickListener(cl);
        button_plus.setOnClickListener(cl);
        button_4.setOnClickListener(cl);
        button_5.setOnClickListener(cl);
        button_6.setOnClickListener(cl);
        button_mul.setOnClickListener(cl);
        button_7.setOnClickListener(cl);
        button_8.setOnClickListener(cl);
        button_9.setOnClickListener(cl);
        button_div.setOnClickListener(cl);
        button_0.setOnClickListener(cl);
        button_eq.setOnClickListener(cl);
        button_cl.setOnClickListener(cl);
        button_sub.setOnClickListener(cl);
    }
}