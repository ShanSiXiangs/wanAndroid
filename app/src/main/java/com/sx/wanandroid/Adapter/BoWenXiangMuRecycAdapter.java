package com.sx.wanandroid.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sx.wanandroid.Activity.WebviewActivity;
import com.sx.wanandroid.DataBean.BoWenXiangMuBean;
import com.sx.wanandroid.R;
import com.sx.wanandroid.databinding.FragmentHotBowenXiangmuBinding;
import com.sx.wanandroid.databinding.ItemBowenXiangmuBinding;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

public class BoWenXiangMuRecycAdapter extends RecyclerView.Adapter<BoWenXiangMuRecycAdapter.MyViewHolder> {
    public Context context;
    List<BoWenXiangMuBean.DataBean.DatasBean> datas;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        FragmentHotBowenXiangmuBinding binding;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }

    public BoWenXiangMuRecycAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemBowenXiangmuBinding binding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.item_bowen_xiangmu, parent, false);
        return new MyViewHolder(binding.getRoot());
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        ItemBowenXiangmuBinding binding = DataBindingUtil.getBinding(holder.itemView);
        final BoWenXiangMuBean.DataBean.DatasBean data = datas.get(position);
        binding.type.setText(data.getSuperChapterName()+"/"+data.getChapterName());
        binding.title.setText(data.getTitle());
        binding.author.setText(data.getAuthor());
        binding.time.setText(data.getNiceDate());
        binding.getRoot().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                WebviewActivity.startWebviewActivity(context,data.getLink());
            }
        });
    }

    @Override
    public int getItemCount() {
        return datas == null || datas.size() == 0 ? 0 : datas.size();
    }

    /**
     * 设置数据并刷新
     */
    public void setData(List<BoWenXiangMuBean.DataBean.DatasBean> datas) {
        this.datas = datas;
        notifyDataSetChanged();
    }

    ;
}
