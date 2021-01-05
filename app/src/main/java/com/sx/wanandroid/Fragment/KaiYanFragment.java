package com.sx.wanandroid.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sx.wanandroid.R;
import com.sx.wanandroid.databinding.FragmentKaiyanBinding;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

public class KaiYanFragment extends Fragment {

    public static KaiYanFragment newInstance() {
        return new KaiYanFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       FragmentKaiyanBinding binding= DataBindingUtil.inflate(inflater, R.layout.fragment_kaiyan,container,false);
        return binding.getRoot();
    }




}
