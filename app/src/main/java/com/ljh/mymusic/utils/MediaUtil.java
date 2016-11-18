package com.ljh.mymusic.utils;

import android.content.Context;
import android.database.Cursor;
import android.provider.MediaStore;
import android.util.Log;

import com.ljh.mymusic.bean.Mp3Info;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhulaoshi on 2016/11/18.
 */
public class MediaUtil {



    public static List<Mp3Info> getMp3Infos(Context context) {
        List<Mp3Info> mp3Infos=new ArrayList<>();
        Cursor cursor=context.getContentResolver().query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,null,null,null,MediaStore.Audio.Media.DEFAULT_SORT_ORDER);
        Log.d("TAG",cursor.getCount()+"cursor大小");
        for (int i=0;i<cursor.getCount();i++){
            Mp3Info mp3Info=new Mp3Info();
            cursor.moveToNext();
            long id = cursor.getLong(cursor
                    .getColumnIndex(MediaStore.Audio.Media._ID));   //音乐id
            String title = cursor.getString((cursor
                    .getColumnIndex(MediaStore.Audio.Media.TITLE)));//音乐标题
            String artist = cursor.getString(cursor
                    .getColumnIndex(MediaStore.Audio.Media.ARTIST));//艺术家
            long duration = cursor.getLong(cursor
                    .getColumnIndex(MediaStore.Audio.Media.DURATION));//时长
            long size = cursor.getLong(cursor
                    .getColumnIndex(MediaStore.Audio.Media.SIZE));  //文件大小
            String url = cursor.getString(cursor
                    .getColumnIndex(MediaStore.Audio.Media.DATA));              //文件路径
            int isMusic = cursor.getInt(cursor
                    .getColumnIndex(MediaStore.Audio.Media.IS_MUSIC));//是否为音乐
            //if (isMusic != 0) {    //只把音乐添加到集合当中
            mp3Info.setId(id);
            mp3Info.setTitle(title);
            mp3Info.setArtist(artist);
            mp3Info.setDuration(duration);
            mp3Info.setSize(size);
            mp3Info.setUrl(url);
            mp3Infos.add(mp3Info);
            //}
        }
        Log.d("TAG",mp3Infos.size()+"list大小");
        return mp3Infos;

    }

    public static String formatTime(long time){
        String min=time/(1000*60)+"";
        String sec=time%(1000*60)+"";
        if (min.length()<2){
            min="0"+time/(1000*60)+"";
        }else{
            min=time/(1000*60)+"";
        }
        if (sec.length() == 4) {
            sec = "0" + (time % (1000 * 60)) + "";
        } else if (sec.length() == 3) {
            sec = "00" + (time % (1000 * 60)) + "";
        } else if (sec.length() == 2) {
            sec = "000" + (time % (1000 * 60)) + "";
        } else if (sec.length() == 1) {
            sec = "0000" + (time % (1000 * 60)) + "";
        }
        return min+":"+sec.trim().substring(0,2);
    }
}
