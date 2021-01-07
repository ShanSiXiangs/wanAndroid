package com.sx.wanandroid.Adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sx.wanandroid.Activity.WebviewActivity;
import com.sx.wanandroid.DataBean.GongZhongSomeOneBean;
import com.sx.wanandroid.DataBean.GuangChangBean;
import com.sx.wanandroid.R;
import com.sx.wanandroid.databinding.ItemGongzhongArticleBinding;
import com.sx.wanandroid.databinding.ItemGuangchangBinding;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

public class GuangChangRecycAdapter extends RecyclerView.Adapter<GuangChangRecycAdapter.MyViewHolder> {
    private Context context;
    private List<GuangChangBean.DataBean.DatasBean> data;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }

    public GuangChangRecycAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemGuangchangBinding binding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.item_guangchang, parent, false);
        return new MyViewHolder(binding.getRoot());
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        ItemGuangchangBinding binding = DataBindingUtil.getBinding(holder.itemView);
        binding.title.setText(data.get(position).getTitle());
        binding.author.setText("分享人：" + data.get(position).getShareUser());
        binding.time.setText(data.get(position).getNiceDate());
        binding.thenew.setVisibility(position < 15 ? View.VISIBLE : View.GONE);

        binding.getRoot().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                WebviewActivity.startWebviewActivity(context, data.get(position).getLink());
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
    public void setData(List<GuangChangBean.DataBean.DatasBean> data) {
        this.data = data;
        notifyDataSetChanged();
    }


}
