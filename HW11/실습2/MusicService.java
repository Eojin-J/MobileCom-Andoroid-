package com.example.mobile_hw11_ex2;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;

import androidx.annotation.Nullable;

import java.security.Provider;

public class MusicService extends Service {
    //public static Object MusicServiceBinder;
    MediaPlayer mediaPlayer;
    IBinder mBinder = new MusicServiceBinder();

    class MusicServiceBinder extends Binder{
        MusicService getService() {return MusicService.this;}
    }

    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    public void onDestroy() {
        super.onDestroy();
        if (mediaPlayer!=null) mediaPlayer.stop();
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    public void play(int song){
        mediaPlayer = MediaPlayer.create(this, song);
        mediaPlayer.setLooping(true);
        mediaPlayer.start();
    }

    public void pause(){
        if (mediaPlayer!=null)
            mediaPlayer.pause();
    }

    public void stop(){
        if(mediaPlayer != null)
            mediaPlayer.stop();
    }
    public MediaPlayer getMediaPlayer() {return mediaPlayer;}

}
