package com.sx.wanandroid.Fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.scwang.smart.refresh.footer.ClassicsFooter;
import com.scwang.smart.refresh.header.ClassicsHeader;
import com.scwang.smart.refresh.layout.api.RefreshLayout;
import com.scwang.smart.refresh.layout.listener.OnRefreshLoadMoreListener;
import com.sx.wanandroid.Adapter.BoWenXiangMuRecycAdapter;
import com.sx.wanandroid.DataBean.BoWenXiangMuBean;
import com.sx.wanandroid.NetWork.DataManager;
import com.sx.wanandroid.R;
import com.sx.wanandroid.databinding.FragmentHotBowenXiangmuBinding;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;


public class HotBoWenFragment extends Fragment implements OnRefreshLoadMoreListener {
    private FragmentHotBowenXiangmuBinding binding;
    private BoWenXiangMuRecycAdapter boWenXiangMuRecycAdapter;
    private int pageNum = 0; //请求文章数据的页数
    List<BoWenXiangMuBean.DataBean.DatasBean> bowenDataList; //文章数据列表

    public static HotBoWenFragment newInstance() {
        return new HotBoWenFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_hot_bowen_xiangmu, container, false);
        init();
        getBoWen(null);
        return binding.getRoot();
    }

    private void init() {
        //刷新加载
        binding.refreshLayout.setRefreshHeader(new ClassicsHeader(getActivity()));
        binding.refreshLayout.setRefreshFooter(new ClassicsFooter(getActivity()));
        binding.refreshLayout.setOnRefreshLoadMoreListener(this);
        //recyclerView设置适配器，必须先设置LayoutManager
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL);
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        binding.recyclerView.addItemDecoration(dividerItemDecoration);
        boWenXiangMuRecycAdapter = new BoWenXiangMuRecycAdapter(getActivity());
        binding.recyclerView.setAdapter(boWenXiangMuRecycAdapter);
    }

    /**
     * 获取热门博文列表
     */
    private void getBoWen(final RefreshLayout refreshLayout) {
        DataManager.instance().getBoWen(pageNum, null, new DataManager.RequestListener() {
            @Override
            public void succeed(Object dataBean) {
                refreshLoadMoreFinish(refreshLayout);
                BoWenXiangMuBean boWenXiangMuBean = (BoWenXiangMuBean) dataBean;
                switch (boWenXiangMuBean.getErrorCode()) {
                    case 0:
                        if (null == bowenDataList)
                            bowenDataList = boWenXiangMuBean.getData().getDatas();
                        else
                            bowenDataList.addAll(boWenXiangMuBean.getData().getDatas());
                        boWenXiangMuRecycAdapter.setData(bowenDataList);
                        break;
                }
                Log.e("num", "succeed: " + bowenDataList.size());
            }

            @Override
            public void failed() {
                refreshLoadMoreFinish(refreshLayout);
            }
        });
    }

    @Override
    public void onRefresh(@NonNull RefreshLayout refreshLayout) {
        pageNum = 0; //重置请求页数
        bowenDataList.clear(); //清空数据
        getBoWen(refreshLayout);
    }

    @Override
    public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
        pageNum++;
        getBoWen(refreshLayout);
    }

    private void refreshLoadMoreFinish(RefreshLayout refreshLayout) {
        if (null != refreshLayout) {
            if (refreshLayout.isRefreshing())
                refreshLayout.finishRefresh();
            if (refreshLayout.isLoading())
                refreshLayout.finishLoadMore();
        }
    }

}
