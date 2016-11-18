package com.ljh.mymusic;

import android.content.Intent;
import android.database.Cursor;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.ljh.mymusic.adapter.MyAdapter;
import com.ljh.mymusic.bean.Mp3Info;
import com.ljh.mymusic.service.PlayService;
import com.ljh.mymusic.utils.AppConstant;
import com.ljh.mymusic.utils.MediaUtil;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView listView_mp3;
    private MyAdapter adapter;
    private List<Mp3Info> list;
    private int listPosition=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        list = MediaUtil.getMp3Infos(this);
        Log.d("TAG",list.toString());

        init();

    }

    private void init() {
        listView_mp3 = (ListView) findViewById(R.id.listView_mp3);
        adapter=new MyAdapter(MainActivity.this,list);
        listView_mp3.setAdapter(adapter);

        listView_mp3.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                listPosition=i;
                playMusic(listPosition);
            }
        });
    }

    private void playMusic(int listPosition) {
        if (listView_mp3!=null){
            Mp3Info mp3Info=list.get(listPosition);
            Toast.makeText(MainActivity.this, "播放音乐："+mp3Info.getTitle(), Toast.LENGTH_SHORT).show();
            Intent intent=new Intent(MainActivity.this,PlayService.class);
            intent.putExtra("url",mp3Info.getUrl());
            intent.putExtra("MSG", AppConstant.PlayerMsg.PLAY_MSG);
            startService(intent);
        }
    }


}
