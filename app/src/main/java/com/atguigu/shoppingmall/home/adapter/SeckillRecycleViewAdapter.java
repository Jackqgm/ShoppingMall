package com.atguigu.shoppingmall.home.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.atguigu.shoppingmall.R;
import com.atguigu.shoppingmall.home.bean.ResultBeanData;
import com.atguigu.shoppingmall.utils.Constants;
import com.bumptech.glide.Glide;

import java.util.List;

/**
 * Created by Jackqgm on 2020/2/23.
 */

public class SeckillRecycleViewAdapter extends RecyclerView.Adapter<SeckillRecycleViewAdapter.ViewHolder> {
    private final Context mContext;
    private final List<ResultBeanData.ResultBean.SeckillInfoBean.ListBean> list;

    public SeckillRecycleViewAdapter(Context mContext, List<ResultBeanData.ResultBean.SeckillInfoBean.ListBean> list) {
        this.mContext=mContext;
        this.list=list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        return new ViewHolder(View.inflate(mContext, R.layout.item_seckill, null));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        //根据位置得到对应的数据
        ResultBeanData.ResultBean.SeckillInfoBean.ListBean listBean = list.get(position);
        //绑定数据
        Glide.with(mContext).load(Constants.BASE_URL_IMAGE + listBean.getFigure()).into(holder.iv_figure);
        holder.tv_origin_price.setText(listBean.getOrigin_price());
        holder.tv_cover_price.setText(listBean.getCover_price());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView iv_figure;
        private TextView tv_cover_price;
        private TextView tv_origin_price;

        public ViewHolder(View itemView) {
            super(itemView);
            iv_figure = itemView.findViewById(R.id.iv_figure);
            tv_cover_price = itemView.findViewById(R.id.tv_cover_price);
            tv_origin_price = itemView.findViewById(R.id.tv_origin_price);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (onSeckillRecyclerView!=null){
                        onSeckillRecyclerView.onItemClick(getLayoutPosition());
                    }
                }
            });
        }
    }

    public interface OnSeckillRecyclerView{

        public void onItemClick(int position);
    }
    private OnSeckillRecyclerView onSeckillRecyclerView;

    public void setOnSeckillRecyclerView(OnSeckillRecyclerView onSeckillRecyclerView) {
        this.onSeckillRecyclerView = onSeckillRecyclerView;
    }
}
