package com.example.lily.newframe.ui.home;

import android.animation.LayoutTransition;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lily.newframe.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ljq
 * on 2018/5/23.
 */

public class HomeSideAdapter extends RecyclerView.Adapter<HomeSideAdapter.SideViewHolder>{

    private Context context;
    private List<String> list;


    public HomeSideAdapter(Context context) {
        this.context = context;
        list = new ArrayList<>();
        list.add("账户");
        list.add("订阅");
        list.add("应用");
        list.add("图书");
        list.add("设置");

    }

    @Override
    public SideViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_home_side,parent,false);
        return new SideViewHolder(view);
    }

    @Override
    public void onBindViewHolder(SideViewHolder holder, int position) {
        holder.textView.setText(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class SideViewHolder extends RecyclerView.ViewHolder{
        ImageView imageView;
        TextView textView;

        public SideViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.iv_side);
            textView = itemView.findViewById(R.id.tv_side);
        }
    }
}
