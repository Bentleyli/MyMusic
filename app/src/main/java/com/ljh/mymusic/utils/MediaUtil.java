package com.ljh.mymusic.utils;

import android.util.Log;

/**
 * Created by zhulaoshi on 2016/11/18.
 */
public class MediaUtil {

    public static String formatTime(long time){
        String min=time/(1000*60)+"";
        String sec=time%(1000*60)+"";
        Log.d("TAG","毫秒数："+time+"分："+min+"秒："+sec);
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
