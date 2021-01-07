package com.sx.wanandroid.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.scwang.smart.refresh.footer.ClassicsFooter;
import com.scwang.smart.refresh.header.ClassicsHeader;
import com.scwang.smart.refresh.layout.api.RefreshLayout;
import com.scwang.smart.refresh.layout.listener.OnRefreshLoadMoreListener;
import com.sx.wanandroid.Adapter.GongZhongArticleRecycAdapter;
import com.sx.wanandroid.Adapter.KaiYanRecycAdapter;
import com.sx.wanandroid.R;
import com.sx.wanandroid.databinding.FragmentKaiyanBinding;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

public class KaiYanFragment extends Fragment implements OnRefreshLoadMoreListener {

    private FragmentKaiyanBinding binding;

    public static KaiYanFragment newInstance() {
        return new KaiYanFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_kaiyan, container, false);
        init();
        return binding.getRoot();
    }

    private void init(){
        binding.refreshLayout.setRefreshHeader(new ClassicsHeader(getActivity()));
        binding.refreshLayout.setRefreshFooter(new ClassicsFooter(getActivity()));
        binding.refreshLayout.setOnRefreshLoadMoreListener(this);
        binding.recycKaiYan.setLayoutManager(new LinearLayoutManager(getActivity()));
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL);
        binding.recycKaiYan.addItemDecoration(dividerItemDecoration);
        KaiYanRecycAdapter kaiYanRecycAdapter = new KaiYanRecycAdapter(getActivity());
        binding.recycKaiYan.setAdapter(kaiYanRecycAdapter);
    }


    @Override
    public void onRefresh(@NonNull RefreshLayout refreshLayout) {

    }
    @Override
    public void onLoadMore(@NonNull RefreshLayout refreshLayout) {

    }

}
