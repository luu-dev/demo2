package com.example.appsong.adapter;

import android.net.Uri;
import android.text.format.Formatter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.databinding.BindingAdapter;

import com.bumptech.glide.Glide;

public class AppBinding {
    @BindingAdapter("duration")
    public static void parseTime(TextView tv, int duration) {
        String str = String.format("%02d:%02d", (duration / 1000 % 3600) / 60, (duration / 1000 % 60));
        tv.setText(str);

    }
    @BindingAdapter("size")
    public static void parseSize(TextView tv,int size){
        String str= Formatter.formatFileSize(tv.getContext(),size);
        tv.setText(str);

    }

    @BindingAdapter("albumcount")
    public static void countAlbum (TextView tv, String album){

    }


    @BindingAdapter("thumb")
    public static void thumb(ImageView im, Long id){
        Uri uri=Uri.parse("content://media/external/audio/albumart/"+id);
        Glide.with(im).load(uri).into(im);

    }
}
