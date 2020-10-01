package com.example.appsong.model;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.example.appsong.FragmentListener;
import com.example.appsong.MainActivity;
import com.example.appsong.R;
import com.example.appsong.adapter.AlbumSongAdapter;
import com.example.appsong.databinding.AlbumBinding;
import com.example.appsong.util.SystemnData;

import java.util.ArrayList;

public class ArtistSong extends AppCompatActivity implements FragmentListener {
    private AlbumBinding binding;
    private AlbumSongAdapter adapter;
    private ArrayList<Song> data = new ArrayList<>();
    private  ArrayList<Song> s =new ArrayList<>();
    private SystemnData systemnData;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding= DataBindingUtil.setContentView(this, R.layout.album);
        Intent intent=getIntent();

        data=new ArrayList<>();
        adapter=new AlbumSongAdapter(getLayoutInflater());
        systemnData=new SystemnData(this);


        String artist =intent.getStringExtra("EXTRA_ARTIST2");
        int time=0;

        binding.setArtist(artist);
        s = systemnData.getData();
        binding.setListener(this);

        for (int i = 0; i < s.size(); i++) {
            if (s.get(i).getArtist().equals(artist) ) {
                data.add(s.get(i));
                time+=s.get(i).getDuration();

            }

        }
        String str = String.format("%02d:%02d", (time/ 1000 % 3600) / 60, (time/ 1000 % 60));
        binding.setTime(str);

        adapter.setAlbumSong(data);
        binding.lvAlbum2.setAdapter(adapter);
    }

    @Override
    public void onNext() {

    }

    @Override
    public void onPrev() {

    }

    @Override
    public void onMediaPause() {

    }

    @Override
    public void back() {
        Intent intent=new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void share() {

    }
}

