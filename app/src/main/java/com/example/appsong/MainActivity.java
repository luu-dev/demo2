package com.example.appsong;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.Manifest;
import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.SearchView;
import android.widget.TableLayout;

//import com.example.appsong.Adapter.ArtistAdapter;

import com.example.appsong.Adapter.PageAdapter;

import com.example.appsong.Fragment.FragmentAlbum;
import com.example.appsong.Fragment.FragmentArtist;
import com.example.appsong.Fragment.FragmentSong;
import com.example.appsong.databinding.ActivityMainBinding;
import com.example.appsong.model.AlBumSong;
import com.example.appsong.util.SystemnData;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener, androidx.appcompat.widget.SearchView.OnQueryTextListener {
    private ActivityMainBinding binding;
    private PagerAdapter adapter;
    private ViewPager pager;
    private FragmentSong fragmentSong = new FragmentSong();
    private FragmentAlbum fragmentAlbum = new FragmentAlbum();
   private FragmentArtist fragmentArtist=new FragmentArtist();
    private TabLayout tab;
    private SearchView search;
    private SystemnData data;
//    private AlBumSong alBumSong;
//
//    public AlBumSong getAlBumSong() {
//        return alBumSong;
//    }

    private  static  int SPLASH_TIME_OUT =4000;

    public FragmentAlbum getFragmentAlbum() {
        return fragmentAlbum;
    }

    public FragmentArtist getFragmentArtist() {
        return fragmentArtist;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                Intent homeintent=new Intent(MainActivity.this,HomeActivity.class);
//                startActivity(homeintent);
//                finish();
//            }
//        },SPLASH_TIME_OUT);
        if (checkPermission())

        init();
        else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions(PERMISSIONS, 0);
            }
        }
    }

    public FragmentSong getFragmentSong() {
        return fragmentSong;
    }

    private void init() {

        pager = findViewById(R.id.pager);
        tab = findViewById(R.id.tab);
        tab.setupWithViewPager(pager);
//        binding.tab.setupWithViewPager(pager);
//        binding.pager.setAdapter(adapter);
        binding.pager.addOnPageChangeListener(this);
        adapter = new PageAdapter(getSupportFragmentManager(), fragmentSong, fragmentAlbum, fragmentArtist);


        pager.setAdapter(adapter);
        data = new SystemnData(this);
        fragmentAlbum.setData(data.getAlbum());
        fragmentArtist.setData(data.getArtist());

        binding.search.setOnQueryTextListener(this);



    }


    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }






    private String[] PERMISSIONS = {
            Manifest.permission.READ_EXTERNAL_STORAGE
    };

    private boolean checkPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            for (String p : PERMISSIONS) {
                if (checkSelfPermission(p) == PackageManager.PERMISSION_DENIED) {
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
    public boolean onQueryTextSubmit(String query) {
        return true;
    }

    @Override
    public boolean onQueryTextChange(String newText) {

        getFragmentSong().getAdapter().getFilter().filter(newText);
        return true;
    }
}