package com.sx.wanandroid.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.scwang.smart.refresh.footer.ClassicsFooter;
import com.scwang.smart.refresh.header.ClassicsHeader;
import com.scwang.smart.refresh.layout.api.RefreshLayout;
import com.scwang.smart.refresh.layout.listener.OnRefreshLoadMoreListener;
import com.sx.wanandroid.Adapter.BoWenXiangMuRecycAdapter;
import com.sx.wanandroid.Adapter.GongZhongArticleRecycAdapter;
import com.sx.wanandroid.Adapter.GongZhongTypeRecycAdapter;
import com.sx.wanandroid.DataBean.GongZhongHaoBean;
import com.sx.wanandroid.DataBean.GongZhongSomeOneBean;
import com.sx.wanandroid.NetWork.DataManager;
import com.sx.wanandroid.R;
import com.sx.wanandroid.databinding.FragmentGongzhonghaoBinding;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

public class GongZhongHaoFragment extends Fragment implements OnRefreshLoadMoreListener {

    private FragmentGongzhonghaoBinding binding;
    private GongZhongTypeRecycAdapter gongZhongTypeRecycAdapter;
    private int pageNum = 1; //当前请求某个公众号数据列表的页数
    private int gongZhongId; //当前选择的公众号ID
    private GongZhongArticleRecycAdapter gongZhongArticleRecycAdapter;
    List<GongZhongSomeOneBean.DataBean.DatasBean> GongZhongSomeOneList;

    public static GongZhongHaoFragment newInstance() {
        return new GongZhongHaoFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_gongzhonghao, container, false);
        init();
        getGongZhongHao();
        return binding.getRoot();
    }


    public void init() {
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL);
        binding.RecycType.setLayoutManager(new LinearLayoutManager(getActivity()));
        binding.RecycType.addItemDecoration(dividerItemDecoration);
        gongZhongTypeRecycAdapter = new GongZhongTypeRecycAdapter(getActivity(),this);
        binding.RecycType.setAdapter(gongZhongTypeRecycAdapter);

        //刷新加载
        binding.refreshLayout.setRefreshHeader(new ClassicsHeader(getActivity()));
        binding.refreshLayout.setRefreshFooter(new ClassicsFooter(getActivity()));
        binding.refreshLayout.setOnRefreshLoadMoreListener(this);
        binding.RecycArticle.setLayoutManager(new LinearLayoutManager(getActivity()));
        binding.RecycArticle.addItemDecoration(dividerItemDecoration);
        gongZhongArticleRecycAdapter = new GongZhongArticleRecycAdapter(getActivity());
        binding.RecycArticle.setAdapter(gongZhongArticleRecycAdapter);

    }

    /**
     * 获取公众号列表
     */
    public void getGongZhongHao() {
        DataManager.instance().getGongZhongHao(new DataManager.RequestListener() {

            @Override
            public void succeed(Object dataBean) {
                GongZhongHaoBean gongZhongHaoBean = (GongZhongHaoBean) dataBean;
                switch (gongZhongHaoBean.getErrorCode()) {
                    case 0:
                        gongZhongTypeRecycAdapter.setData(gongZhongHaoBean.getData());
                        gongZhongId = gongZhongHaoBean.getData().get(0).getId();
                        getGongZhongSomeOne(gongZhongId, pageNum, null);
                        break;
                }
            }

            @Override
            public void failed() {

            }
        });
    }

    /**
     * 获取某个公众号数据列表
     */
    public void getGongZhongSomeOne(int id, int pageNum, final RefreshLayout refreshLayout) {
        DataManager.instance().getGongZhongSomeOne(id, pageNum, new DataManager.RequestListener() {
            @Override
            public void succeed(Object dataBean) {
                refreshLoadMoreFinish(refreshLayout);
                GongZhongSomeOneBean gongZhongSomeOneBean = (GongZhongSomeOneBean) dataBean;
                switch (gongZhongSomeOneBean.getErrorCode()) {
                    case 0:
                        if (null == GongZhongSomeOneList)
                            GongZhongSomeOneList = gongZhongSomeOneBean.getData().getDatas();
                        else
                            GongZhongSomeOneList.addAll(gongZhongSomeOneBean.getData().getDatas());
                        gongZhongArticleRecycAdapter.setData(GongZhongSomeOneList);
                        break;
                }
            }

            @Override
            public void failed() {
                refreshLoadMoreFinish(refreshLayout);
            }
        });
    }

    /**
     * 改变公众号的选择
     */
    public void selectGongZhongType(int id) {
        gongZhongId = id;
        pageNum = 1;
        GongZhongSomeOneList.clear();
        getGongZhongSomeOne(gongZhongId, pageNum, null);
    }

    @Override
    public void onRefresh(@NonNull RefreshLayout refreshLayout) {
        pageNum = 1;
        GongZhongSomeOneList.clear();
        getGongZhongSomeOne(gongZhongId, pageNum, refreshLayout);
    }

    @Override
    public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
        pageNum++;
        getGongZhongSomeOne(gongZhongId, pageNum, refreshLayout);
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
