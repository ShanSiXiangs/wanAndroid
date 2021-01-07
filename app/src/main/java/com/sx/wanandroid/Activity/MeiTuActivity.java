package com.sx.wanandroid.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.scwang.smart.refresh.footer.ClassicsFooter;
import com.scwang.smart.refresh.header.ClassicsHeader;
import com.scwang.smart.refresh.layout.api.RefreshLayout;
import com.scwang.smart.refresh.layout.listener.OnRefreshLoadMoreListener;
import com.sx.wanandroid.Adapter.KaiYanRecycAdapter;
import com.sx.wanandroid.Adapter.MeiTuRecycAdapter;
import com.sx.wanandroid.DataBean.MeiTuBean;
import com.sx.wanandroid.NetWork.DataManager;
import com.sx.wanandroid.NetWork.RetrofitHelper;
import com.sx.wanandroid.R;
import com.sx.wanandroid.databinding.ActivityMeiTuBinding;

import java.util.List;

public class MeiTuActivity extends BaseActivity implements OnRefreshLoadMoreListener {

    private ActivityMeiTuBinding binding;
    private int pageNum=1;
    private MeiTuRecycAdapter meiTuRecycAdapter;
    private List<MeiTuBean.DataBean> meituDataList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_mei_tu);
        init();
        getMeiTu();
    }

    private void init() {
        binding.refreshLayout.setRefreshHeader(new ClassicsHeader(this));
        binding.refreshLayout.setRefreshFooter(new ClassicsFooter(this));
        binding.refreshLayout.setOnRefreshLoadMoreListener(this);
        binding.recycMeiTu.setLayoutManager(new GridLayoutManager(this,2));
        meiTuRecycAdapter = new MeiTuRecycAdapter(this);
        binding.recycMeiTu.setAdapter(meiTuRecycAdapter);
    }

    /**
     * 启动当前activity的方法
     *
     * @param context 调用时的页面的上下文
     */
    public static void startMeiTuActivity(Context context) {
        Intent intent = new Intent(context, MeiTuActivity.class);
        context.startActivity(intent);
    }

    /**
     * 获取美图列表及具体数据
     */
    public void getMeiTu() {

        DataManager.instance().getMeiTu(pageNum, new DataManager.RequestListener() {
            @Override
            public void succeed(Object dataBean) {
               MeiTuBean meiTuBean = (MeiTuBean) dataBean;
               switch (meiTuBean.getStatus()){
                   case 100:
                       if (null == meituDataList)
                           meituDataList = meiTuBean.getData();
                       else
                           meituDataList.addAll(meiTuBean.getData());
                       meiTuRecycAdapter.setData(meituDataList);
                       break;
               }
            }

            @Override
            public void failed() {

            }
        });
    }

    @Override
    public void onRefresh(@NonNull RefreshLayout refreshLayout) {

    }

    @Override
    public void onLoadMore(@NonNull RefreshLayout refreshLayout) {

    }


}