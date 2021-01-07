package com.sx.wanandroid.Fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.sx.wanandroid.Activity.MeiTuActivity;
import com.sx.wanandroid.Activity.WebviewActivity;
import com.sx.wanandroid.Adapter.ShouYeViewPageAdapter;
import com.sx.wanandroid.DataBean.BannerBean;
import com.sx.wanandroid.DataBean.LoginBean;
import com.sx.wanandroid.NetWork.DataManager;
import com.sx.wanandroid.R;
import com.sx.wanandroid.databinding.FragmentShouyeBinding;
import com.wenjian.loopbanner.LoopAdapter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ShouYeFragment extends Fragment implements View.OnClickListener {

    private FragmentShouyeBinding binding;

    public static ShouYeFragment newInstance() {
        return new ShouYeFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_shouye, container, false);
        initData();
        initTabLayout();
        getBanner();
        return binding.getRoot();
    }


    /**
     * 初始化控件
     */
    private void initData() {
        binding.xiangmu.setOnClickListener(this);
        binding.tixi.setOnClickListener(this);
        binding.wenda.setOnClickListener(this);
        binding.mianshi.setOnClickListener(this);
        binding.meitu.setOnClickListener(this);
    }

    /**
     * 设置轮播图数据
     */
    private void setBannerData(List<BannerBean.DataBean> data) {
        //配置适配器
        binding.loopBanner.setAdapter(new LoopAdapter<BannerBean.DataBean>(data) {
            @Override
            protected void onBindView(ViewHolder holder, final BannerBean.DataBean data, int position) {
                Glide.with(getActivity()).load(data.getImagePath()).into((ImageView) holder.itemView);
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        WebviewActivity.startWebviewActivity(getActivity(),data.getUrl());
                    }
                });
            }
        });
    }

    /**
     * 初始化选项卡
     */
    private void initTabLayout() {
        final String[] titles = new String[]{"热门博文", "热门项目"};
        Fragment[] Fragments = new Fragment[]{new HotBoWenFragment(), new HotXiangMuFragment()};
        binding.viewPager.setAdapter(new ShouYeViewPageAdapter(getChildFragmentManager(), getLifecycle(), Fragments));
        TabLayoutMediator tabLayoutMediator = new TabLayoutMediator(binding.tabLayout, binding.viewPager, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                tab.setText(titles[position]);
            }
        });
        tabLayoutMediator.attach();
    }

    /**
     * 点击事件
     *
     * @param view
     */
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.xiangmu:

                break;
            case R.id.tixi:

                break;
            case R.id.wenda:

                break;
            case R.id.mianshi:

                break;
            case R.id.meitu:
                MeiTuActivity.startMeiTuActivity(getActivity());
                break;
        }
    }

    /**
     * 获取轮播图数据
     */
    private void getBanner() {
        DataManager.instance().getBanner(new DataManager.RequestListener() {
            @Override
            public void succeed(Object dataBean) {
                BannerBean bannerBean = (BannerBean) dataBean;
                switch (bannerBean.getErrorCode()) {
                    case 0:
                        setBannerData(bannerBean.getData());
                        break;
                }
            }

            @Override
            public void failed() {

            }

        });
    }
}
