package com.example.appsong.model;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.example.appsong.Fragment.FragmentAlbum;
import com.example.appsong.Fragment.FragmentArtist;
import com.example.appsong.FragmentListener;
import com.example.appsong.MainActivity;
import com.example.appsong.R;

import com.example.appsong.adapter.AlbumSongAdapter;
import com.example.appsong.databinding.AlbumBinding;
import com.example.appsong.util.SystemnData;

import java.util.ArrayList;
import java.util.List;

public class AlBumSong extends AppCompatActivity implements FragmentListener {

 private AlbumBinding binding;
// private Song song = new Song();
 private AlbumSongAdapter adapter;
 private ArrayList<Song> data = new ArrayList<>();
 private  ArrayList<Song> s =new ArrayList<>();
 private SystemnData systemnData;



    @Override

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=DataBindingUtil.setContentView(this,R.layout.album);
        Intent intent=getIntent();


        data=new ArrayList<>();
        adapter=new AlbumSongAdapter(getLayoutInflater());

//        adapter.setAlbumSong(data);
        systemnData=new SystemnData(this);

            String album =  intent.getStringExtra("EXTRA_ALBUM");
            String artist =intent.getStringExtra("EXTRA_ARTIST");
            binding.setAlbum(album);

            binding.setArtist(artist);
            binding.setListener(this);
            s = systemnData.getData();
            int time=0;

            for (int i = 0; i < s.size(); i++) {
                if (s.get(i).getArtist().equals(artist) && s.get(i).getAlbum().equals(album)) {
                    data.add(s.get(i));
                    time+=s.get(i).getDuration();

                }

            }
            String str = String.format("%02d:%02d", (time/ 1000 % 3600) / 60, (time/ 1000 % 60));
        Log.e("str", "onCreate: "+str );
            binding.setTime(str);







        adapter.setAlbumSong(data);
        binding.lvAlbum2.setAdapter(adapter);



    }


    public void setData(List<Song> data) {
        this.data.clear();
        this.data.addAll(data);
        if (adapter != null) {
            adapter.setAlbumSong(this.data);
        }
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
