package com.example.appsong.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appsong.databinding.ItemalbumBinding;
import com.example.appsong.model.AlBum;

import java.util.ArrayList;

public class AlbumAdapter extends RecyclerView.Adapter<AlbumAdapter.AlbumHolder>{

    private LayoutInflater inflater;
    private ArrayList<AlBum> albums;
    private AlbumItemClickListener listener;

    public void setListener(AlbumItemClickListener listener) {
        this.listener = listener;
    }

    public AlbumItemClickListener getListener() {
        return listener;
    }

    public AlbumAdapter(LayoutInflater inflater) {
        this.inflater = inflater;
    }

    public void setAlbums(ArrayList<AlBum> albums) {
        this.albums = albums;
    }

    @NonNull
    @Override
    public AlbumHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemalbumBinding binding = ItemalbumBinding.inflate(inflater, parent, false);
        return new AlbumHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull AlbumHolder holder, int position) {
        holder.binding.setItem(albums.get(position));
        if (listener!=null)
            holder.binding.setListener(listener);
    }

    @Override
    public int getItemCount() {
        return albums.size();
    }

    public class AlbumHolder extends RecyclerView.ViewHolder {
        private ItemalbumBinding binding;
        public AlbumHolder(@NonNull ItemalbumBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

    public interface AlbumItemClickListener{
        void onAlbumClicked(AlBum item);
    }
}
