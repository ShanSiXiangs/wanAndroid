package com.sx.wanandroid.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sx.wanandroid.R;
import com.sx.wanandroid.databinding.FragmentWodeBinding;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

public class WoDeFragment extends Fragment {


    public static WoDeFragment newInstance() {
        return new WoDeFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       FragmentWodeBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_wode,container,false);
        return binding.getRoot();
    }

}
