package com.ljh.mymusic.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.ljh.mymusic.R;
import com.ljh.mymusic.bean.Mp3Info;
import com.ljh.mymusic.utils.MediaUtil;

import java.util.List;

/**
 * Created by zhulaoshi on 2016/11/17.
 */
public class MyAdapter extends BaseAdapter {

    Context context;
    List<Mp3Info> list;


    public MyAdapter(Context context, List<Mp3Info> list) {
        this.context=context;
        this.list=list;
    }



    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder=null;
        if (view==null){
            viewHolder=new ViewHolder();
            view= LayoutInflater.from(context).inflate(R.layout.listview_item,viewGroup,false);
            viewHolder.tv_title = (TextView) view.findViewById(R.id.textview_title);
            viewHolder.tv_artist = (TextView) view.findViewById(R.id.textView_artist);
            viewHolder.tv_duration = (TextView) view.findViewById(R.id.textView_duration);
            viewHolder.tv_size = (TextView) view.findViewById(R.id.textView_size);
            view.setTag(viewHolder);
        }else {
            viewHolder= (ViewHolder) view.getTag();
        }

        Mp3Info mp3Info=list.get(i);
        viewHolder.tv_title.setText(mp3Info.getTitle());
        viewHolder.tv_artist.setText(mp3Info.getArtist());
        viewHolder.tv_duration.setText(MediaUtil.formatTime(mp3Info.getDuration()));
        viewHolder.tv_size.setText(mp3Info.getSize()+"");

        return view;
    }

    private class ViewHolder {
        private TextView tv_title,tv_artist,tv_duration,tv_size;
    }
}
