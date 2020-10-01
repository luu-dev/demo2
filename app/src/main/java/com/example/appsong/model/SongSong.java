package com.example.appsong.model;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.widget.SeekBar;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.example.appsong.FragmentListener;
import com.example.appsong.MainActivity;
import com.example.appsong.MediaController;
import com.example.appsong.R;
import com.example.appsong.databinding.SongBinding;
import com.example.appsong.util.SystemnData;

import java.util.ArrayList;

public class SongSong  extends AppCompatActivity implements FragmentListener, SeekBar.OnSeekBarChangeListener {
    private SongBinding binding;
    private SystemnData data;
    private ArrayList<Song> songs;
    private MediaController mediaController;
    private MediaPlayer player;

    private int vitri;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= DataBindingUtil.setContentView(this, R.layout.song);
        data=new SystemnData(this);
        songs= new ArrayList<>();
        songs=data.getData();
        Intent intent=getIntent();
        String position=  intent.getStringExtra("EXTRA_VITRI");
        vitri= Integer.parseInt(position);
        binding.setSong(songs.get(vitri));
        binding.setListener(this);
        binding.seekbar.setOnSeekBarChangeListener(this);





        mediaController=new MediaController(songs,this){
            @Override
            public void create(int index) {
                super.create(index);
//                binding.setTitle(songs.get(index).getTitle());
                    binding.setSong(songs.get(index));
                    binding.seekbar.setMax(songs.get(index).getDuration());
                     UpdateTimeSong();

            }

            @Override
            public void start() {
                super.start();
                binding.setIsPlaying(true);


            }

            @Override
            public void pause() {
                super.pause();
                binding.setIsPlaying(false);
            }

        };
        mediaController.create(vitri);



    }

    @Override
    public void onNext() {
        mediaController.change(1);
    }

    @Override
    public void onPrev() {
        mediaController.change(-1);
    }

    @Override
    public void onMediaPause() {
        if (binding.getIsPlaying())
            mediaController.pause();
        else
            mediaController.start();
    }

    @Override
    public void back() {
        Intent intent=new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
        mediaController.stop();
    }

    @Override
    public void share() {
        final Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.setType("audio/mp3");
        shareIntent.putExtra(Intent.EXTRA_STREAM, Uri.parse(Environment.getExternalStorageDirectory().toString() + "/breakfast.mp3"));
        startActivity(Intent.createChooser(shareIntent, "Condividi attraverso:"));
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
        mediaController.seek(binding.seekbar.getProgress());
    }


    private void UpdateTimeSong(){
        final Handler handler=new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                binding.seekbar.setProgress(mediaController.getPosition());
                handler.postDelayed(this::run,500);
            }
        },100);
    }
}
