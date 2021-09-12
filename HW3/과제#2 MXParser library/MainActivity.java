package com.example.mobile_hw3_5;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import org.mariuszgromada.math.mxparser.Expression;

public class MainActivity extends AppCompatActivity{
    Button btn;
    EditText edit;
    TextView text;
    String result;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn = findViewById(R.id.btn);
        edit = findViewById(R.id.edit);
        text = findViewById(R.id.text);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                result = edit.getText().toString();
                Expression e = new Expression(result);

                String calculate = String.valueOf(e.calculate());
                calculate = calculate.replaceAll("NaN","ERROR");
                text.setText(calculate);
            }
        });

    }
}