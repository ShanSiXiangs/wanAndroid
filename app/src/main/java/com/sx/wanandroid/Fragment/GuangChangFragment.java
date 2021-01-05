package com.sx.wanandroid.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sx.wanandroid.R;
import com.sx.wanandroid.databinding.FragmentGuangchangBinding;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

public class GuangChangFragment extends Fragment {


    public static GuangChangFragment newInstance() {
        return new GuangChangFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       FragmentGuangchangBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_guangchang,container,false);
        return binding.getRoot();
    }


}
