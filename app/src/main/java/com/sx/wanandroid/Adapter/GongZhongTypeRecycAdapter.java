package com.sx.wanandroid.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.sx.wanandroid.Activity.WebviewActivity;
import com.sx.wanandroid.DataBean.BoWenXiangMuBean;
import com.sx.wanandroid.DataBean.GongZhongHaoBean;
import com.sx.wanandroid.Fragment.GongZhongHaoFragment;
import com.sx.wanandroid.R;
import com.sx.wanandroid.databinding.FragmentHotBowenXiangmuBinding;
import com.sx.wanandroid.databinding.ItemBowenXiangmuBinding;
import com.sx.wanandroid.databinding.ItemGongzhongTypeBinding;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

public class GongZhongTypeRecycAdapter extends RecyclerView.Adapter<GongZhongTypeRecycAdapter.MyViewHolder> {
    private Context context;
    private int mPosition;
    private List<GongZhongHaoBean.DataBean> data;
    private GongZhongHaoFragment fragment;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        FragmentHotBowenXiangmuBinding binding;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }

    public GongZhongTypeRecycAdapter(Context context, GongZhongHaoFragment fragment) {
        this.context = context;
        this.fragment = fragment;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemGongzhongTypeBinding binding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.item_gongzhong_type, parent, false);
        return new MyViewHolder(binding.getRoot());
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        final ItemGongzhongTypeBinding binding = DataBindingUtil.getBinding(holder.itemView);
        binding.name.setText(data.get(position).getName());
        if (mPosition == position) {
            binding.indicator.setVisibility(View.VISIBLE);
            binding.name.setTextColor(context.getResources().getColor(R.color.color_0D53DF));
        } else {
            binding.indicator.setVisibility(View.GONE);
            binding.name.setTextColor(context.getResources().getColor(R.color.color_383838));
        }

        binding.name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPosition = position;
                notifyDataSetChanged();
                fragment.selectGongZhongType(data.get(position).getId());
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
    public void setData(List<GongZhongHaoBean.DataBean> data) {
        this.data = data;
        notifyDataSetChanged();
    }


}
