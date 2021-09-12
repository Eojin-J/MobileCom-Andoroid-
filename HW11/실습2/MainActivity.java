package com.example.mobile_hw11_ex2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.IBinder;
import android.os.SystemClock;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;

import org.w3c.dom.Text;

import java.lang.reflect.Field;
import java.security.Provider;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ImageButton prevBtn, playBtn, nextBtn;
    ListView listView;
    SeekBar seekBar;
    TextView currentTime, replayTime;
    boolean playing = false;
    boolean pause = true;
    MusicService ms;
    int r;
    Field field;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        prevBtn = (ImageButton) findViewById(R.id.prevBtn);
        playBtn = (ImageButton) findViewById(R.id.playBtn);
        nextBtn = (ImageButton) findViewById(R.id.nextBtn);
        listView = (ListView) findViewById(R.id.listView);

        seekBar = (SeekBar) findViewById(R.id.seekBar);
        currentTime = (TextView) findViewById(R.id.currentTime);
        replayTime = (TextView) findViewById(R.id.replayTime);

        final List<String> data = new ArrayList<>();

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_single_choice, data);
        listView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        listView.setAdapter(adapter);
        data.add("오마이걸(OH MY GIRL) - Dun Dun Dance");
        data.add("아이유(IU) - Celebrity");
        data.add("아이유(IU) - Hold My Hand");
        adapter.notifyDataSetChanged();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Class<R.raw> rawClass = R.raw.class;
                try {
                    field = rawClass.getField("music" + position);
                    r = field.getInt(null);
                } catch (Exception e) {

                }
                Intent intent = new Intent(getApplicationContext(), MusicService.class);
                if (playing)
                    ms.stop();
                else
                    bindService(intent, connection, Context.BIND_AUTO_CREATE);
            }
        });

        prevBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ms.stop();
                int num = listView.getCheckedItemPosition();
                if (num < 1)
                    listView.setItemChecked(num = 2, true);
                else
                    listView.setItemChecked(--num, true);

                Class<R.raw> rawClass = R.raw.class;
                try {
                    field = rawClass.getField("music" + num);
                    r = field.getInt(null);
                } catch (Exception e) {

                }
                if (playing) {
                    ms.play(r);
                    makeThreadBind();
                }
            }
        });

        playBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(pause==true && playing){
                    ms.play(r);
                    makeThreadBind();
                    pause=false;
                }
                else if (pause==false && playing){
                    ms.pause();
                    playing = false;
                    pause = true;
                }

                else if(pause==true && !playing) {
                    ms.play(r);
                    makeThreadBind();
                    playing = true;
                    pause = false;
                }
                System.out.println("1111111111111111"+pause+playing);
//
//                if (playing){
//                    ms.play(r);
//                    makeThreadBind();
//                }
            }
        });

        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ms.stop();
                int num = listView.getCheckedItemPosition();
                if(num > 1)
                    listView.setItemChecked(num=0, true);
                else
                    listView.setItemChecked(++num, true);

                Class<R.raw> rawClass = R.raw.class;
                try {
                    field = rawClass.getField("music"+num);
                    r = field.getInt(null);
                }catch (Exception e){

                }
                if(playing){
                    ms.play(r);
                    makeThreadBind();
                }
            }
        });

    }

    void makeThreadBind() {
        new Thread() {
            public void run() {
                final SimpleDateFormat timeFormat = new SimpleDateFormat("mm:ss");
                final MediaPlayer mediaPlayer = ms.getMediaPlayer();

                if (mediaPlayer == null) return;
                seekBar.setMax(mediaPlayer.getDuration());
                seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                    @Override
                    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                        if (fromUser)
                            mediaPlayer.seekTo(progress);
                    }

                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar) {

                    }

                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar) {

                    }
                });

                while(mediaPlayer.isPlaying()) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            seekBar.setProgress(mediaPlayer.getCurrentPosition());
                            currentTime.setText(String.format(timeFormat.format(mediaPlayer.getCurrentPosition())));
                            replayTime.setText(String.format(timeFormat.format(mediaPlayer.getDuration())));
                        }
                    });
                    SystemClock.sleep(100);
                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (currentTime.getText().toString().equals(replayTime.getText().toString())) {
                            currentTime.setText(String.format(timeFormat.format(0)));
                            replayTime.setText(String.format(timeFormat.format(0)));
                        }
                    }
                });
            }
        }.start();
    }

    ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            MusicService.MusicServiceBinder mb = (MusicService.MusicServiceBinder) service;
            ms = mb.getService();
            playing = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            connection = null;
        }
    };
}