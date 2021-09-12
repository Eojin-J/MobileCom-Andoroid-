package com.example.mobile_hw11_ex1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    myDBHelper myHelper;
    EditText editNameText, editNumberText, editNameResult, editNumberResult;
    Button resBtn, inputBtn, modBtn, delBtn, inqBtn;
    SQLiteDatabase sqlDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editNameText = (EditText)findViewById(R.id.editNameText);
        editNumberText = (EditText)findViewById(R.id.editNumberText);
        editNameResult = (EditText)findViewById(R.id.editNameResult);
        editNumberResult = (EditText)findViewById(R.id.editNumberResult);

        resBtn = (Button)findViewById(R.id.resBtn);
        inputBtn = (Button)findViewById(R.id.inputBtn);
        modBtn = (Button)findViewById(R.id.modBtn);
        delBtn = (Button)findViewById(R.id.delBtn);
        inqBtn = (Button)findViewById(R.id.inqBtn);

        myHelper = new myDBHelper(this);


        resBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sqlDB = myHelper.getWritableDatabase();
                myHelper.onUpgrade(sqlDB, 1, 2);
                sqlDB.close();
                inqBtn.callOnClick();
            }
        });

        inputBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sqlDB = myHelper.getWritableDatabase();
                sqlDB.execSQL("INSERT INTO groupTBL VALUES ('" +editNameText.getText().toString()+"', "+editNumberText.getText().toString()+" );");
                sqlDB.close();
                inqBtn.callOnClick();
            }
        });

        inqBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sqlDB = myHelper.getReadableDatabase();
                Cursor cursor;
                cursor = sqlDB.rawQuery("SELECT * FROM groupTBL;", null);

                String strNames = "그룹 이름" + "\r\n" + " -------- " + "\r\n";
                String strNumbers = "인원" + "\r\n" + " ---------- "+"\r\n";

                while(cursor.moveToNext()){
                    strNames += cursor.getString(0) + "\r\n";
                    strNumbers += cursor.getString(1) + "\r\n";
                }

                editNameResult.setText(strNames);
                editNumberResult.setText(strNumbers);

                cursor.close();
                sqlDB.close();
            }
        });

        modBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sqlDB = myHelper.getReadableDatabase();
                Cursor cursor;
                cursor = sqlDB.rawQuery("SELECT * FROM groupTBL", null);

                while(cursor.moveToNext()){
                    if(cursor.getString(0).equals(editNameText.getText().toString())){
                        sqlDB = myHelper.getWritableDatabase();
                        sqlDB.execSQL("UPDATE groupTBL SET gName = '" + editNameText.getText().toString() + "', gNumber = "
                                + editNumberText.getText().toString() +
                                " WHERE gName = '" + editNameText.getText().toString() + "';");
                    }
                }

                cursor.close();
                sqlDB.close();
                inqBtn.callOnClick();
            }
        });

        delBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sqlDB = myHelper.getReadableDatabase();
                Cursor cursor;
                cursor  = sqlDB.rawQuery("SELECT * FROM groupTBL", null);

                while (cursor.moveToNext()){
                    if(cursor.getString(0).equals(editNameText.getText().toString())){
                        sqlDB = myHelper.getWritableDatabase();
                        sqlDB.execSQL("DELETE FROM groupTBL WHERE gName = '" + editNameText.getText().toString() + "';");
                    }
                }

                cursor.close();
                sqlDB.close();
                inqBtn.callOnClick();

            }
        });
    }

    public class myDBHelper extends SQLiteOpenHelper{
        public myDBHelper(Context context) {
            super(context, "groupDB", null, 1);
        }
        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL("CREATE TABLE groupTBL (gName CHAR(20) PRIMARY KEY, gNumber INTEGER);");
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS groupTBL");
            onCreate(db);
        }

    }
}