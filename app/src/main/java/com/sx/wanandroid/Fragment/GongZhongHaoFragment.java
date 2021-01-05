package com.sx.wanandroid.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sx.wanandroid.R;
import com.sx.wanandroid.databinding.FragmentGongzhonghaoBinding;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

public class GongZhongHaoFragment extends Fragment {

    public static GongZhongHaoFragment newInstance() {
        return new GongZhongHaoFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        FragmentGongzhonghaoBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_gongzhonghao,container,false);
        return binding.getRoot();
    }

    /**
     * 初始化控件
     */
    public void initView() {

    }

}
