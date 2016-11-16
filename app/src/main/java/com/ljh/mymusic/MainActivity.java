package com.ljh.mymusic;

import android.database.Cursor;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.ljh.mymusic.bean.Mp3Info;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView listView_mp3;
    private ArrayAdapter adapter;
    private List<Mp3Info> list;
    List<String> titles;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        list = getMp3Infos();
        titles=new ArrayList<>();
        Log.d("TAG1",list.get(0).getTitle());
        for (int i=0;i<list.size();i++){
            String title=list.get(i).getTitle();
            titles.add(title);
        }

        init();

    }

    private void init() {
        listView_mp3 = (ListView) findViewById(R.id.listView_mp3);
        adapter=new ArrayAdapter(this,android.R.layout.simple_list_item_1,titles);
        listView_mp3.setAdapter(adapter);
    }

    private List<Mp3Info> getMp3Infos() {
        List<Mp3Info> mp3Infos=new ArrayList<>();
        Cursor cursor=getContentResolver().query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,null,null,null,MediaStore.Audio.Media.DEFAULT_SORT_ORDER);

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
            if (isMusic != 0) {    //只把音乐添加到集合当中
                Log.d("TAG:",i+"");
                mp3Info.setId(id);
                mp3Info.setTitle(title);
                mp3Info.setArtist(artist);
                mp3Info.setDuration(duration);
                mp3Info.setSize(size);
                mp3Info.setUrl(url);
                mp3Infos.add(mp3Info);
            }
        }
        Log.d("TAG",mp3Infos.size()+"list大小");
        return mp3Infos;

    }


}
