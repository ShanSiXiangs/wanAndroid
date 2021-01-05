package com.sx.wanandroid.Adapter;

import java.util.ArrayList;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class MainViewPageAdapter extends FragmentStateAdapter {
    ArrayList<Fragment> FragmentList;

    public MainViewPageAdapter(@NonNull FragmentManager fm, Lifecycle Lifecycle, ArrayList<Fragment> FragmentList) {
        super(fm, Lifecycle);
        this.FragmentList = FragmentList;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return FragmentList.get(position);
    }

    @Override
    public int getItemCount() {
        return FragmentList.size();
    }
}
