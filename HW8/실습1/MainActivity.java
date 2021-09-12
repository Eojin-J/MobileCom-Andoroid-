package com.example.mobile_hw8_ex1;

import android.Manifest;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    View dialogView;

    DatePicker dp;
    EditText editDiary;
    Button btnWrite;
    String fileName;

    Switch mSwitch;

    private final int PERMISSION_REQUEST_STORAGE = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setTitle("간단 일기장");

        dp = (DatePicker) findViewById(R.id.datePicker1);
        editDiary = (EditText) findViewById(R.id.editDiary);
        btnWrite = (Button) findViewById(R.id.btnWrite);

        mSwitch = (Switch) findViewById(R.id.switch1);
        mSwitch.setChecked(false);
        mSwitch.setChecked(sharedPreferencesLoad());

        if(Build.VERSION.SDK_INT >=26) checkPermission();

        mSwitch.setOnClickListener(new CompoundButton.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                final EditText editText = new EditText(MainActivity.this);

                final SharedPreferences settings = getSharedPreferences("password", MODE_PRIVATE);
                final SharedPreferences settings2 = getSharedPreferences("check", MODE_PRIVATE);

                String checked = settings.getString("password", "");

                final SharedPreferences.Editor editor = settings.edit();
                final SharedPreferences.Editor editor2 = settings2.edit();

                if(!mSwitch.isChecked()){
                    builder.setTitle("비밀번호 확인");
                    builder.setMessage("잠금을 해제하시겠습니까? (기본 값: 0000)");
                    builder.setView(editText);
                    btnWrite.setEnabled(true);

                    String str = readDiary(fileName);

                    builder.setPositiveButton("OK",(dialog, which) ->{
                        String checkPassword = settings.getString("password", "0000");

                        if(checkPassword.equals(editText.getText().toString())){
                            Toast.makeText(getApplicationContext(), "잠금이 해제 되었습니다.", Toast.LENGTH_SHORT).show();
                            mSwitch.setChecked(false);

                            editDiary.setText(str);

                            editor2.putString("check", "false");
                            editor2.commit();
                        }
                        else{
                            Toast.makeText(getApplicationContext(), "비밀번호가 틀립니다.", Toast.LENGTH_SHORT).show();
                            mSwitch.setChecked(true);
                            editor2.putString("check", "true");
                            editor2.commit();
                        }
                    });
                    builder.setNegativeButton("Cancel", (dialog, which) -> {
                       Toast.makeText(getApplicationContext(), "취소", Toast.LENGTH_SHORT).show();

                       editor2.putString("check", "true");
                       editor2.commit();
                       mSwitch.setChecked(true);
                    });
                    builder.show();
                }
                else{
                    builder.setTitle("암호 변경");
                    builder.setMessage("암호를 변경하시겠습니까?");
                    builder.setView(editText);

                    builder.setPositiveButton("OK", (dialog, which) -> {
                        editor.putString("password", editText.getText().toString());
                        editor2.putString("check", "true");

                        editDiary.setText(" ");

                        Toast.makeText(getApplicationContext(), "변경 완료", Toast.LENGTH_SHORT).show();
                        editor.commit();
                        editor2.commit();
                    });
                    builder.setNegativeButton("Cancel", (dialog, which) -> {
                        Toast.makeText(getApplicationContext(), "잠금 취소 및 비밀번호 변경 안 함", Toast.LENGTH_SHORT).show();

                        mSwitch.setChecked(false);
                        editor2.putString("check", "false");
                        editor2.commit();
                    });

                    builder.show();
                }

            }
        }
    );


        Calendar cal = Calendar.getInstance();
        int cYear = cal.get(Calendar.YEAR);
        int cMonth = cal.get(Calendar.MONTH);
        int cDay = cal.get(Calendar.DAY_OF_MONTH);

        fileName = Integer.toString(cYear) + "_" + Integer.toString(cMonth +1) + "_"+
                Integer.toString(cDay) +".txt";


        dp.init(cYear, cMonth, cDay, new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                fileName = Integer.toString(year) + "_" + Integer.toString(monthOfYear +1) + "_"+
                        Integer.toString(dayOfMonth) +".txt";
                String str = readDiary(fileName);


                if(mSwitch.isChecked()){
                    btnWrite.setEnabled(false);
                    editDiary.setText(" ");
                    editDiary.setHint("잠김");
                }
                else{
                    btnWrite.setEnabled(true);
                    editDiary.setText(str);
                }
            }
        });

        btnWrite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if(mSwitch.isChecked()){
                        editDiary.setText(" ");
                        editDiary.setHint("잠김");
                        Toast.makeText(getApplicationContext(), "ReadOnly mode", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    FileOutputStream outFs = openFileOutput(fileName, MODE_PRIVATE);
                    String str = editDiary.getText().toString();

                    outFs.write(str.getBytes());
                    outFs.close();
                    Toast.makeText(getApplicationContext(), fileName + "이 저장됨", Toast.LENGTH_SHORT).show();
                }catch (IOException e ){
                    Toast.makeText(getApplicationContext(), "error: "+e.toString(), Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private void checkPermission() {

        if(checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)
            != PackageManager.PERMISSION_GRANTED|| checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE)!=PackageManager.PERMISSION_GRANTED){
            if(shouldShowRequestPermissionRationale(Manifest.permission.WRITE_EXTERNAL_STORAGE)){
                Toast.makeText(this, "Read/Write external storage", Toast.LENGTH_SHORT).show();
            }
        }

        requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE},
                PERMISSION_REQUEST_STORAGE);
    }

    public void onRequestPermissionsResult(int requestCode, String[] permission, int[] grantResults){
        switch (requestCode) {
            case PERMISSION_REQUEST_STORAGE:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED && grantResults[1] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(getApplicationContext(), "permission granted", Toast.LENGTH_SHORT).show();
                } else {
                    Log.d("jjj", "Permission always deny");
                }
                break;
        }
    }

    private boolean sharedPreferencesLoad(){
        SharedPreferences settings2 = getSharedPreferences("check", MODE_PRIVATE);
        SharedPreferences.Editor editor2 = settings2.edit();
        editor2.commit();
        if(settings2.getString("check", "").equals("true"))
            return true;
        else
            return false;
    }

    String readDiary(String fName){
        String diaryStr = null;
        FileInputStream inFs;
        try{
            inFs = openFileInput(fName);
            byte[] txt = new byte[500];
            inFs.read(txt);
            inFs.close();
            diaryStr = (new String(txt)).trim();
            btnWrite.setText("수정하기");
        }catch (IOException e){
            editDiary.setHint("일기 없음");
            btnWrite.setText("새로 저장");
        }
        return diaryStr;
    }
}
