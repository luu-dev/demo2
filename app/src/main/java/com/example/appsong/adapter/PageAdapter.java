package com.example.appsong.Adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.PagerAdapter;

public class PageAdapter extends FragmentPagerAdapter {
    private  Fragment[] fragments;
    public PageAdapter(@NonNull FragmentManager fm, Fragment...fragments) {
        super(fm,BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        this.fragments=fragments;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return fragments[position];
    }

    @Override
    public int getCount() {
        return fragments.length;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position){

            case 0:
                return "BAI HAT";
            case 1:
                return "ALBUM";
            case 2:
                return "ARTIST";
        }

        return super.getPageTitle(position);
    }
}
