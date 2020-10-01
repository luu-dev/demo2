package com.example.appsong.Fragment;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.example.appsong.adapter.ArtistAdapter;
import com.example.appsong.adapter.SongAdapter;
import com.example.appsong.R;
import com.example.appsong.adapter.ArtistAdapter;
import com.example.appsong.databinding.FragmentartistBinding;
import com.example.appsong.databinding.FragmentsongBinding;
import com.example.appsong.model.AlBum;
import com.example.appsong.model.AlBumSong;
import com.example.appsong.model.Artist;
import com.example.appsong.model.ArtistSong;
import com.example.appsong.model.Song;
import com.example.appsong.util.SystemnData;

import java.util.ArrayList;
import java.util.List;

public class FragmentArtist extends Fragment implements ArtistAdapter.ArtistItemClickListener {
    private FragmentartistBinding binding;
    private ArtistAdapter adapter;
    private ArrayList<Artist> data = new ArrayList<>();
    private  final int a=1;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragmentartist, container, false);
        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initViews();
        adapter.setListener(this);
    }

    private void initViews() {


        adapter = new ArtistAdapter(getLayoutInflater());
//        binding.rvAlbum.setLayoutManager(new GridLayoutManager(getActivity(), 3));
        binding.rvArtist.setAdapter(adapter);
        adapter.setArtists(data);
        //adapter.setListener(this);

    }
    public void setData(List<Artist> data) {
        this.data.clear();
        this.data.addAll(data);
        if (adapter != null) {
            adapter.setArtists(this.data);
        }
    }

    private boolean click_double = false;
    //private boolean click_time = false;
    private static int times=1;

    @Override
    public void postponeEnterTransition() {
        super.postponeEnterTransition();
    }

    @Override
    public void onArtistClicked(Artist item) {

//        AlBumSong alBumSong=(AlBumSong) getActivity();
//        alBumSong.setclick=1;
        Toast.makeText(getContext(),"fdgdfggdfg",Toast.LENGTH_SHORT).show();
        int index=data.indexOf(item);
        Intent i=new Intent(getContext(), ArtistSong.class);
        i.putExtra("EXTRA_ARTIST2",data.get(index).getArtist());
        getActivity().startActivity(i);

        getActivity().finish();
    }
}
