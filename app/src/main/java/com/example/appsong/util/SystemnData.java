package com.example.appsong.util;

import android.content.ContentResolver;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.provider.MediaStore;

import com.example.appsong.model.AlBum;
import com.example.appsong.model.Artist;
import com.example.appsong.model.Song;

import java.util.ArrayList;

public class SystemnData {
    private ContentResolver resolver;

    public SystemnData(Context context) {
        resolver = context.getContentResolver();
    }

    public ArrayList<Song> getData() {


        Cursor cursor = resolver.query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, null, null, null, null);
        int indexId = cursor.getColumnIndex(MediaStore.Audio.AudioColumns.ALBUM_ID);
        int indexData = cursor.getColumnIndex(MediaStore.Audio.AudioColumns.DATA);
        int indexSize = cursor.getColumnIndex(MediaStore.Audio.AudioColumns.SIZE);
        int indexTitle = cursor.getColumnIndex(MediaStore.Audio.AudioColumns.TITLE);
       int indexDuration = cursor.getColumnIndex(MediaStore.Audio.AudioColumns.DURATION);
        int indexArtist = cursor.getColumnIndex(MediaStore.Audio.AudioColumns.ARTIST);
       int indexAlbum = cursor.getColumnIndex(MediaStore.Audio.AudioColumns.ALBUM);
        ArrayList<Song> arr = new ArrayList<>();
        cursor.moveToFirst();
        while (cursor.isAfterLast() == false) {

            String data = cursor.getString(indexData);
            long id = cursor.getLong(indexId);
           int size = cursor.getInt(indexSize);
            int duration = cursor.getInt(indexDuration);
            String title = cursor.getString(indexTitle);
            String artist = cursor.getString(indexArtist);
            String album = cursor.getString(indexAlbum);
            Song song = new Song();
           song.setAlbum(album);
            song.setId(id);
            song.setArtist(artist);
           song.setData(data);
            song.setDuration(duration);
            song.setSize(size);
            song.setTitle(title);
            arr.add(song);
            cursor.moveToNext();
        }
        cursor.close();
        return arr;
    }

    public ArrayList<AlBum> getAlbum(){
        ArrayList<AlBum> arr = new ArrayList<>();
        Cursor cursor = resolver.query(
                MediaStore.Audio.Albums.EXTERNAL_CONTENT_URI,
                null, null, null, null
        );
        cursor.moveToFirst();
//        int time =cursor.getColumnIndex(MediaStore.Audio.AudioColumns.DURATION);
        int indexAlbum = cursor.getColumnIndex(MediaStore.Audio.Albums.ALBUM);
        int indexArtist = cursor.getColumnIndex(MediaStore.Audio.Albums.ARTIST);
        int indexNumberOfSong = cursor.getColumnIndex(MediaStore.Audio.Albums.NUMBER_OF_SONGS);
        int indexImage = cursor.getColumnIndex(MediaStore.Audio.Albums.ALBUM_ID);

        while (cursor.isAfterLast() == false) {
            AlBum album = new AlBum();

            //album.setImage(cursor.getLong(indexImage));
            album.setAlbum(cursor.getString(indexAlbum));
            album.setArtist(cursor.getString(indexArtist));
            album.setNumberOfSong(cursor.getString(indexNumberOfSong));

            arr.add(album);
            cursor.moveToNext();
        }
        return arr;
    }


    public ArrayList<Artist> getArtist() {
        ArrayList<Artist> arr = new ArrayList<>();
        Cursor cursor = resolver.query(
                MediaStore.Audio.Artists.EXTERNAL_CONTENT_URI,
                null, null, null, null
        );
        cursor.moveToFirst();

        int indexAlbum = cursor.getColumnIndex(MediaStore.Audio.Artists.ARTIST);
        int indexNumberOfAlbums = cursor.getColumnIndex(MediaStore.Audio.Artists.NUMBER_OF_ALBUMS);
        int indexNumberOfTracks = cursor.getColumnIndex(MediaStore.Audio.Artists.NUMBER_OF_TRACKS);

        while (cursor.isAfterLast() == false) {
            Artist artist = new Artist();

            artist.setArtist(cursor.getString(indexAlbum));
            artist.setNumberOfAlbum(cursor.getString(indexNumberOfAlbums));
            artist.setNumberOfSong(cursor.getString(indexNumberOfTracks));

            arr.add(artist);
            cursor.moveToNext();
        }
        return arr;
    }

}