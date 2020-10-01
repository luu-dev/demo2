package com.example.appsong.adapter;

import android.icu.util.ULocale;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appsong.databinding.ItemsongBinding;
import com.example.appsong.model.Song;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Locale;

public class SongAdapter extends RecyclerView.Adapter<SongAdapter.SongHolder> implements Filterable {
    private ArrayList<Song> songs;
    private LayoutInflater inflater;
    private  ArrayList<Song> list;
    private SongFilter filter= new SongFilter();
    private SongItemClickListener listener;


    public void setListener(SongItemClickListener listener) {
        this.listener = listener;
    }

    public SongAdapter(LayoutInflater inflater) {
        this.inflater = inflater;
    }

    public void setSongs(ArrayList<Song> songs) {
        this.songs = songs;
        this.list=songs;
        notifyDataSetChanged();
    }

    public ArrayList<Song> getSongs() {
        return songs;
    }

    @NonNull
    @Override
    public SongHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemsongBinding binding=ItemsongBinding.inflate(inflater,parent,false);
        return new SongHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull SongHolder holder, int position) {
        holder.binding.setItem(songs.get(position));
        if (listener !=null)
            holder.binding.setListener(listener);
    }

    @Override
    public int getItemCount() {
       return songs.size();
    }

    @Override
    public Filter getFilter() {
        return filter;
    }
    public class SongFilter extends Filter{
        @Override
        protected FilterResults performFiltering(CharSequence key) {
            ArrayList<Song> result =new ArrayList<>();
            for (Song s: list){
                if (s.getTitle().toLowerCase().contains(key.toString().toLowerCase())){
                    result.add(s);
                }
            }
            FilterResults filterResults=new FilterResults();
            filterResults.count=result.size();
            filterResults.values=result;
            return filterResults;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            songs=(ArrayList<Song>) results.values;
            notifyDataSetChanged();
        }
    }

    public class SongHolder extends RecyclerView.ViewHolder{
         private ItemsongBinding binding;
         public SongHolder(@NonNull ItemsongBinding binding) {
             super(binding.getRoot());
             this.binding = binding;
         }
     }
    public interface  SongItemClickListener{
        void onSongItemClick(Song item);
//        void onSongDoubleClick(Song item);

    }



    }

