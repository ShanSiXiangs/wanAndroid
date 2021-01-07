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
import com.sx.wanandroid.Adapter.GuangChangRecycAdapter;
import com.sx.wanandroid.DataBean.GuangChangBean;
import com.sx.wanandroid.NetWork.DataManager;
import com.sx.wanandroid.R;
import com.sx.wanandroid.databinding.FragmentGuangchangBinding;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

public class GuangChangFragment extends Fragment implements OnRefreshLoadMoreListener {

    private FragmentGuangchangBinding binding;
    private GuangChangRecycAdapter guangChangRecycAdapter;
    private int pageNum = 0;
    private List<GuangChangBean.DataBean.DatasBean> guangChangList;

    public static GuangChangFragment newInstance() {
        return new GuangChangFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_guangchang, container, false);
        init();
        getGuangChang(null);
        return binding.getRoot();
    }

    public void init() {
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL);
        binding.refreshLayout.setRefreshHeader(new ClassicsHeader(getActivity()));
        binding.refreshLayout.setRefreshFooter(new ClassicsFooter(getActivity()));
        binding.refreshLayout.setOnRefreshLoadMoreListener(this);
        binding.recycGuangChang.setLayoutManager(new LinearLayoutManager(getActivity()));
        binding.recycGuangChang.addItemDecoration(dividerItemDecoration);
        guangChangRecycAdapter = new GuangChangRecycAdapter(getActivity());
        binding.recycGuangChang.setAdapter(guangChangRecycAdapter);
    }

    /**
     * 获取广场列表
     */
    public void getGuangChang(final RefreshLayout refreshLayout) {
        DataManager.instance().getGuangChang(pageNum, new DataManager.RequestListener() {
            @Override
            public void succeed(Object dataBean) {
                refreshLoadMoreFinish(refreshLayout);
                GuangChangBean guangChangBean = (GuangChangBean) dataBean;
                switch (guangChangBean.getErrorCode()) {
                    case 0:
                        if (null == guangChangList)
                            guangChangList = guangChangBean.getData().getDatas();
                        else
                            guangChangList.addAll(guangChangBean.getData().getDatas());
                        guangChangRecycAdapter.setData(guangChangList);
                        break;
                }
            }

            @Override
            public void failed() {
                refreshLoadMoreFinish(refreshLayout);
            }
        });
    }


    @Override
    public void onRefresh(@NonNull RefreshLayout refreshLayout) {
        pageNum = 0;
        guangChangList.clear();
        getGuangChang(refreshLayout);
    }

    @Override
    public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
        pageNum++;
        getGuangChang(refreshLayout);
    }

    public void refreshLoadMoreFinish(RefreshLayout refreshLayout) {
        if (null != refreshLayout) {
            if (refreshLayout.isRefreshing())
                refreshLayout.finishRefresh();
            if (refreshLayout.isLoading())
                refreshLayout.finishLoadMore();
        }
    }
}
