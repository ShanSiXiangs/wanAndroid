package com.sx.wanandroid.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sx.wanandroid.Activity.WebviewActivity;
import com.sx.wanandroid.DataBean.GongZhongSomeOneBean;
import com.sx.wanandroid.R;
import com.sx.wanandroid.databinding.ItemGongzhongArticleBinding;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

public class KaiYanRecycAdapter extends RecyclerView.Adapter<KaiYanRecycAdapter.MyViewHolder> {
    private Context context;
    private List<GongZhongSomeOneBean.DataBean.DatasBean> data;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }

    public KaiYanRecycAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemGongzhongArticleBinding binding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.item_gongzhong_article, parent, false);
        return new MyViewHolder(binding.getRoot());
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        ItemGongzhongArticleBinding binding = DataBindingUtil.getBinding(holder.itemView);
        binding.title.setText(data.get(position).getTitle());
        binding.time.setText(data.get(position).getNiceDate());
        binding.getRoot().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                WebviewActivity.startWebviewActivity(context,data.get(position).getLink());
            }
        });
    }

    @Override
    public int getItemCount() {
        return data == null || data.size() == 0 ? 0 : data.size();
    }

    /**
     * 设置数据并刷新
     */
    public void setData(List<GongZhongSomeOneBean.DataBean.DatasBean> data) {
        this.data = data;
        notifyDataSetChanged();
    }


}
