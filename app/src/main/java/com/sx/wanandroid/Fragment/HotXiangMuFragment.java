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

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

public class HotXiangMuFragment extends Fragment implements OnRefreshLoadMoreListener {

    private FragmentHotBowenXiangmuBinding binding;
    private int pageNum = 0; //请求文章数据的页数
    List<BoWenXiangMuBean.DataBean.DatasBean> xiangmuDataList; //文章数据列表
    private BoWenXiangMuRecycAdapter boWenXiangMuRecycAdapter;

    public static HotXiangMuFragment newInstance() {
        return new HotXiangMuFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_hot_bowen_xiangmu, container, false);
        init();
        getXiangMu(null);
        return binding.getRoot();
    }

    private void init() {
        //刷新加载
        binding.refreshLayout.setRefreshHeader(new ClassicsHeader(getActivity()));
        binding.refreshLayout.setRefreshFooter(new ClassicsFooter(getActivity()));
        binding.refreshLayout.setOnRefreshLoadMoreListener(this);
        //必须先设置LayoutManager
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL);
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        binding.recyclerView.addItemDecoration(dividerItemDecoration);
        boWenXiangMuRecycAdapter = new BoWenXiangMuRecycAdapter(getActivity());
        binding.recyclerView.setAdapter(boWenXiangMuRecycAdapter);
    }

    /**
     * 获取热门项目列表
     */
    private void getXiangMu(final RefreshLayout refreshLayout) {
        Log.e("num", "getXiangMu: "+pageNum );
        DataManager.instance().getXiangMu(pageNum, null, new DataManager.RequestListener() {
            @Override
            public void succeed(Object dataBean) {
                refreshLoadMoreFinish(refreshLayout);
                BoWenXiangMuBean boWenXiangMuBean = (BoWenXiangMuBean) dataBean;
                switch (boWenXiangMuBean.getErrorCode()) {
                    case 0:
                        if (null == xiangmuDataList)
                            xiangmuDataList = boWenXiangMuBean.getData().getDatas();
                        else
                            xiangmuDataList.addAll(boWenXiangMuBean.getData().getDatas());
                        boWenXiangMuRecycAdapter.setData(xiangmuDataList);
                        break;
                }
                Log.e("UUU", "getXiangMu: "+xiangmuDataList.size() );
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
        xiangmuDataList.clear(); //清空数据
        getXiangMu(refreshLayout);
    }

    @Override
    public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
        pageNum++;
        getXiangMu(refreshLayout);
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
