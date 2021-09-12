package com.example.mobile_hw12_ex2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.os.Bundle;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    EditText editBattery;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("배터리 상태 체크");

        editBattery = (EditText) findViewById(R.id.editBattery);
    }

    protected void onPause(){
        super.onPause();
        unregisterReceiver(broadcastReceiver);
    }

    protected void onResume(){
        super.onResume();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(Intent.ACTION_BATTERY_CHANGED);
        registerReceiver(broadcastReceiver, intentFilter);
    }
    BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();

            if(action.equals(Intent.ACTION_BATTERY_CHANGED)){
                int remain = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, 0);
                editBattery.setText("현재 충전량 : " + remain + "\n");

                int plug = intent.getIntExtra(BatteryManager.EXTRA_PLUGGED, 0);
                switch (plug){
                    case 0:
                        editBattery.append("전원 연결 : 안 됨");
                        break;
                    case BatteryManager.BATTERY_PLUGGED_AC:
                        editBattery.append("전원 연결 : 어댑터 연결");
                        break;
                    case BatteryManager.BATTERY_PLUGGED_USB:
                        editBattery.append("전원 연결 : USB 연결");
                        break;
                }
            }
        }
    };
}