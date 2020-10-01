package com.example.appsong.adapter;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.appsong.databinding.ItemartistBinding;
import com.example.appsong.model.Artist;

import java.util.ArrayList;

public class ArtistAdapter extends RecyclerView.Adapter<ArtistAdapter.ArtistHolder>{

    private LayoutInflater inflater;
    private ArrayList<Artist> artists;
    private  ArtistItemClickListener listener;

    public void setListener(ArtistItemClickListener listener) {
        this.listener = listener;
    }

    public ArtistItemClickListener getListener() {
        return listener;
    }

    public void setArtists(ArrayList<Artist> artists) {
        this.artists = artists;
    }

    public ArtistAdapter(LayoutInflater inflater) {
        this.inflater = inflater;
    }

    @NonNull
    @Override
    public ArtistAdapter.ArtistHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemartistBinding binding = ItemartistBinding.inflate(inflater, parent, false);
        return new ArtistHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ArtistAdapter.ArtistHolder holder, int position) {
        holder.binding.setItem(artists.get(position));
        if (listener!=null)
            holder.binding.setListener(listener);
    }

    @Override
    public int getItemCount() {
        return artists == null ? 0 : artists.size();
    }

    public class ArtistHolder extends RecyclerView.ViewHolder{
        ItemartistBinding binding;
        public ArtistHolder(@NonNull  ItemartistBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
    public interface ArtistItemClickListener{
        void onArtistClicked(Artist item);
    }
}