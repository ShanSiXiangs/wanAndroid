package com.sx.wanandroid.Activity;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import android.icu.text.Transliterator;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.sx.wanandroid.Adapter.MainViewPageAdapter;
import com.sx.wanandroid.Fragment.GongZhongHaoFragment;
import com.sx.wanandroid.Fragment.ShouYeFragment;
import com.sx.wanandroid.Fragment.GuangChangFragment;
import com.sx.wanandroid.Fragment.WoDeFragment;
import com.sx.wanandroid.Fragment.KaiYanFragment;
import com.sx.wanandroid.R;
import com.sx.wanandroid.databinding.ActivityMainBinding;

import java.util.ArrayList;

public class MainActivity extends BaseActivity {

    private ActivityMainBinding binding;
    private String[] tabTitle;
    private int[] tabIconSelect;
    private int[] tabIconUnSelect;
    private ArrayList<Fragment> fragmentList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        initData();
        initTabLayout();
    }

    private void initData() {
        tabTitle = new String[]{"首页", "公众号", "广场", "开眼", "我的"};
        tabIconSelect = new int[]{R.mipmap.shouye2, R.mipmap.gongzhong2, R.mipmap.guangchang2, R.mipmap.kaiyan2, R.mipmap.wode2};
        tabIconUnSelect = new int[]{R.mipmap.shouye1, R.mipmap.gongzhong1, R.mipmap.guangchang1, R.mipmap.kaiyan1, R.mipmap.wode1};
        fragmentList = new ArrayList<>();
        fragmentList.add(new ShouYeFragment());
        fragmentList.add(new GongZhongHaoFragment());
        fragmentList.add(new GuangChangFragment());
        fragmentList.add(new KaiYanFragment());
        fragmentList.add(new WoDeFragment());
    }

    private void initTabLayout() {
        //配置适配器
        binding.viewPager.setAdapter(new MainViewPageAdapter(getSupportFragmentManager(), getLifecycle(), fragmentList));
        //TabLayout和Viewpager2进行关联
        TabLayoutMediator tabLayoutMediator = new TabLayoutMediator(binding.tabLayout, binding.viewPager, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                tab.setText(tabTitle[position]);
                if (position == 0) {
                    tab.setIcon(tabIconSelect[position]);
                    binding.title.setText(tabTitle[position]);
                } else
                    tab.setIcon(tabIconUnSelect[position]);
            }
        });
        tabLayoutMediator.attach();
        tabLayoutSelectListener();
    }

    private void tabLayoutSelectListener() {
        binding.tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int position = tab.getPosition();
                tab.setIcon(tabIconSelect[position]);//设置选中的底部图标
                binding.title.setText(tabTitle[position]); //设置选中的标题
                if (position == 3||position ==4) //判断是否显示搜索功能 开眼和我的模块 不显示
                    binding.search.setVisibility(View.GONE);
                else
                    binding.search.setVisibility(View.VISIBLE);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                tab.setIcon(tabIconUnSelect[tab.getPosition()]);
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }

        });
    }

}