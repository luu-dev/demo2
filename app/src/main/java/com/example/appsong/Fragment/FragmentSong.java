package com.example.appsong.Fragment;

import android.Manifest;
import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;
import android.widget.SeekBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.example.appsong.FragmentListener;
import com.example.appsong.MediaController;
import com.example.appsong.adapter.SongAdapter;
import com.example.appsong.MainActivity;
import com.example.appsong.R;
import com.example.appsong.databinding.FragmentsongBinding;
import com.example.appsong.model.Song;
import com.example.appsong.model.SongSong;
import com.example.appsong.util.SystemnData;

import java.util.ArrayList;

public class FragmentSong extends Fragment implements SearchView.OnQueryTextListener, MenuItem.OnActionExpandListener, SongAdapter.SongItemClickListener, FragmentListener, SeekBar.OnSeekBarChangeListener {
    private SongAdapter adapter;
    private ArrayList<Song> songs;
    private FragmentsongBinding binding;
    private SystemnData data;
    private SearchView search;
    private Dialog dialog;
    private  MediaController mediaController;

    public SongAdapter getAdapter() {
        return adapter;
    }

    public MediaController getMediaController() {
        return mediaController;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding= DataBindingUtil.inflate(inflater,R.layout.fragmentsong,container,false);
        if (checkPermission()) {

            init();
        } else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions(PERMISSIONS, 0);
            }
        }

        return  binding.getRoot();
    }

    private void init() {
        data=new SystemnData(this.getActivity());
        adapter=new SongAdapter(getLayoutInflater());
        songs= new ArrayList<>();
        songs=data.getData();
        adapter.setSongs(songs);
        binding.lvSong.setAdapter(adapter);
        adapter.setListener(this);
        binding.setListener(this);
       mediaController=new MediaController(songs,getContext()){
            @Override
            public void create(int index) {
                super.create(index);
                binding.setTitle(songs.get(index).getTitle());
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

       binding.sbTime.setOnSeekBarChangeListener(this);
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);



    }

    private String[] PERMISSIONS = {
            Manifest.permission.READ_EXTERNAL_STORAGE
    };
    private boolean checkPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            for (String p : PERMISSIONS) {
                if (getActivity().checkSelfPermission(p) == PackageManager.PERMISSION_DENIED) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (checkPermission()) {
           init();
        }
    }


    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        getActivity().getMenuInflater().inflate(R.menu.searchmenu,menu);
        MenuItem menu1= getActivity().findViewById(R.id.menu_search);
        search= (SearchView)menu1.getActionView();
        search.setOnQueryTextListener(this);

        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
//        MainActivity ac=(MainActivity) getActivity();
//        ac.getFragmentSong().adapter.getFilter();
//       adapter.getFilter().filter(newText);
       return false;

    }

    @Override
    public boolean onMenuItemActionExpand(MenuItem item) {
        return false;
    }

    @Override
    public boolean onMenuItemActionCollapse(MenuItem item) {
        return false;
    }
    private int i=0;

    @Override
    public void onSongItemClick(Song item) {
        binding.bottom.setVisibility(View.VISIBLE);
        int index = songs.indexOf(item);
        i++;
        Handler handler = new Handler();
        Runnable r = new Runnable() {


            @Override
            public void run() {
                i = 0;
            }
        };
        if (i==1){


            mediaController.create(index);
            binding.sbTime.setMax(songs.get(index).getDuration());
            UpdateTimeSong();

            handler.postDelayed(r,250);
        }
        else if (i==2 ){

            mediaController.stop();
            Toast.makeText(getContext(),"Asdsaf",Toast.LENGTH_SHORT).show();

            Intent intent = new Intent(getContext(), SongSong.class);
//            intent.putExtra("EXTRA_IMGG",songs.get(index).getId());
//            intent.putExtra("EXTRA_TITLEE",songs.get(index).getTitle());
//            intent.putExtra("EXTRA_ARTISTT",songs.get(index).getArtist());
            intent.putExtra("EXTRA_VITRI",index+"");
            getActivity().startActivity(intent);
            getActivity().finish();






        }


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

    }

    @Override
    public void share() {

    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
//        binding.sbTime.setProgress(mediaController.getPosition());


    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {
    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
        mediaController.seek(binding.sbTime.getProgress());
    }

    private void UpdateTimeSong(){
            final Handler handler=new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {

                    binding.sbTime.setProgress(mediaController.getPosition());
                    handler.postDelayed(this::run,500);
                }
            },100);
    }
}

