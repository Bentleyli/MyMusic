package com.ljh.mymusic.service;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.ljh.mymusic.utils.AppConstant;

import java.io.IOException;

/**
 * Created by zhulaoshi on 2016/11/18.
 */
public class PlayService extends Service {

    private MediaPlayer mediaPlayer =  new MediaPlayer();       //媒体播放器对象
    private String path;                        //音乐文件路径
    private boolean isPause;                    //暂停状态

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (mediaPlayer.isPlaying()){
            stop();
        }
        path=intent.getStringExtra("url");
        int msg=intent.getIntExtra("MSG",0);
        if(msg == AppConstant.PlayerMsg.PLAY_MSG) {
            play(0);
        } else if(msg == AppConstant.PlayerMsg.PAUSE_MSG) {
            pause();
        } else if(msg == AppConstant.PlayerMsg.STOP_MSG) {
            stop();
        }
        return super.onStartCommand(intent, flags, startId);
    }

    private void pause() {
        if (mediaPlayer!=null&&mediaPlayer.isPlaying()){
            mediaPlayer.pause();
            isPause=true;
        }
    }

    private void play(int i) {
        mediaPlayer.reset();  //把各项参数恢复到初始状态
        try {
            mediaPlayer.reset();  //把各项参数恢复到初始状态
            mediaPlayer.setDataSource(path);
            mediaPlayer.prepare();
            mediaPlayer.setOnPreparedListener(new PreparedListener(i));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void stop() {
        if (mediaPlayer!=null){
            mediaPlayer.stop();
            try {
                mediaPlayer.prepare();   // 在调用stop后如果需要再次通过start进行播放,需要之前调用prepare函数
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mediaPlayer!=null){
            mediaPlayer.stop();
            mediaPlayer.release();
        }
    }

    private class PreparedListener implements MediaPlayer.OnPreparedListener {

        private int position;
        public PreparedListener(int i) {
            this.position=i;
        }

        @Override
        public void onPrepared(MediaPlayer mediaPlayer) {
            mediaPlayer.start();  //开始播放
            if (position>0){
                mediaPlayer.seekTo(position);  //如果音乐不是从头播放
            }
        }
    }
}
