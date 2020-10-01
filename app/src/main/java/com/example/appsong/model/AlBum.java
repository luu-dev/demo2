package com.example.appsong.model;

public class AlBum {
    private long image;
    private String album;
    private String artist;
    private String numberOfSong;
    private int duration;

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getDuration() {
        return duration;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public long getImage() {
        return image;
    }

    public void setImage(long image) {
        this.image = image;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getNumberOfSong() {
        return numberOfSong;
    }

    public void setNumberOfSong(String numberOfSong) {
        this.numberOfSong = numberOfSong;
    }
}
