package com.sx.wanandroid.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.sx.wanandroid.Activity.WebviewActivity;
import com.sx.wanandroid.DataBean.GongZhongSomeOneBean;
import com.sx.wanandroid.DataBean.MeiTuBean;
import com.sx.wanandroid.R;
import com.sx.wanandroid.databinding.ItemGongzhongArticleBinding;
import com.sx.wanandroid.databinding.ItemMeituBinding;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

public class MeiTuRecycAdapter extends RecyclerView.Adapter<MeiTuRecycAdapter.MyViewHolder> {
    private Context context;
    private List<MeiTuBean.DataBean> data;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }

    public MeiTuRecycAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemMeituBinding binding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.item_meitu, parent, false);
        return new MyViewHolder(binding.getRoot());
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        ItemMeituBinding binding = DataBindingUtil.getBinding(holder.itemView);
        Glide.with(context).load(data.get(position).getUrl()).into(binding.pic);
    }

    @Override
    public int getItemCount() {
        return data == null || data.size() == 0 ? 0 : data.size();
    }

    /**
     * 设置数据并刷新
     */
    public void setData(List<MeiTuBean.DataBean> data) {
        this.data = data;
        notifyDataSetChanged();
    }

}
