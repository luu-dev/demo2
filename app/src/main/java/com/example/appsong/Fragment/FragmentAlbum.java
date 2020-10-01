package com.example.appsong.Fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.appsong.MainActivity;
import com.example.appsong.adapter.AlbumAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.example.appsong.adapter.AlbumAdapter;
import com.example.appsong.R;
import com.example.appsong.databinding.FragmentalbumBinding;
import com.example.appsong.model.AlBum;
import com.example.appsong.model.AlBumSong;
import com.example.appsong.util.SystemnData;

import java.util.ArrayList;
import java.util.List;

public class FragmentAlbum extends Fragment implements AlbumAdapter.AlbumItemClickListener {

    private FragmentalbumBinding binding;
    private AlbumAdapter adapter;
    private ArrayList<AlBum> data= new ArrayList<>() ;
    private  final int b=1;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragmentalbum, container, false);
        adapter=new AlbumAdapter(getLayoutInflater());
        binding.rvAlbum.setAdapter(adapter);

        adapter.setAlbums(data);
        adapter.setListener(this);
        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }


    public void setData(List<AlBum> data) {
        this.data.clear();
        this.data.addAll(data);
        if (adapter != null) {
            adapter.setAlbums(this.data);
        }
    }


//    @Override
//    public void onAlbumClicked(AlBum item) {
//        Toast.makeText(getContext(),"dsfdsf",Toast.LENGTH_SHORT).show();
////        MainActivity ac=(MainActivity) getActivity();
//
//        Intent intent=new Intent(getContext(),AlBumSong.class);
//
////        intent.putExtra("EXTRA_ALBUM",)
//        startActivity(intent);
//        getActivity().finish();
//
//    }

    @Override
    public void onAlbumClicked(AlBum item) {
//     AlBumSong alBumSong=(AlBumSong) getActivity();
//     alBumSong.setclick=2;

                Toast.makeText(getContext(),"dsfdsf",Toast.LENGTH_SHORT).show();
    int index=data.indexOf(item);
        Intent intent=new Intent(getContext(),AlBumSong.class);
        Log.e("cc", "onAlbumClicked: "+ data.get(index).getAlbum() );
        intent.putExtra("EXTRA_ALBUM",data.get(index).getAlbum());
        intent.putExtra("EXTRA_ARTIST",data.get(index).getArtist());
        getActivity().startActivity(intent);
        getActivity().finish();
    }
}
