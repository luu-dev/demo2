package com.example.appsong.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appsong.databinding.ItemalbumBinding;
import com.example.appsong.databinding.ItemalbumsongBinding;
import com.example.appsong.model.AlBum;
import com.example.appsong.model.Song;

import java.util.ArrayList;




    public class AlbumSongAdapter extends RecyclerView.Adapter<AlbumSongAdapter.AlbumSongHolder>{

        private LayoutInflater inflater;
        private ArrayList<Song>songs;


        public AlbumSongAdapter(LayoutInflater inflater) {
            this.inflater = inflater;
        }

        public void setAlbumSong(ArrayList<Song> songs) {
            this.songs = songs;
        }


        @NonNull
        @Override
        public AlbumSongHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            ItemalbumsongBinding binding=ItemalbumsongBinding.inflate(inflater,parent,false);
            return  new AlbumSongHolder(binding);
        }

        @Override
        public void onBindViewHolder(@NonNull AlbumSongHolder holder, int position) {
            holder.binding.setSong(songs.get(position));
        }

        @Override
        public int getItemCount() {
            return songs.size();
        }

        public class AlbumSongHolder extends RecyclerView.ViewHolder {
            private ItemalbumsongBinding binding;
            public AlbumSongHolder(@NonNull ItemalbumsongBinding binding) {
                super(binding.getRoot());
                this.binding = binding;
            }
        }


    }