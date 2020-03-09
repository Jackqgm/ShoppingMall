package com.atguigu.shoppingmall.shoppingcart.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.atguigu.shoppingmall.R;
import com.atguigu.shoppingmall.home.bean.GoodsBean;
import com.atguigu.shoppingmall.shoppingcart.utils.CartStorage;
import com.atguigu.shoppingmall.shoppingcart.view.AddSubView;
import com.atguigu.shoppingmall.utils.Constants;
import com.bumptech.glide.Glide;

import java.util.List;

/**
 * Created by Jackqgm on 2020/3/8.
 */

public class ShoppingCartAdapter extends RecyclerView.Adapter<ShoppingCartAdapter.ViewHolder> {
    private final Context mContext;
    private final List<GoodsBean> datas;
    private final TextView tvShopcartTotal;
    private final CheckBox checkboxAll;
    private final CheckBox cbAll;


    public ShoppingCartAdapter(Context mContext, List<GoodsBean> goodsBeanList, TextView tvShopcartTotal, CheckBox checkboxAll, CheckBox cbAll) {
        this.mContext = mContext;
        this.datas = goodsBeanList;
        this.tvShopcartTotal = tvShopcartTotal;
        this.checkboxAll = checkboxAll;
        this.cbAll = cbAll;

        showTotalPrice();
        setListener();
        checkAll();
    }

    public void showTotalPrice() {
        tvShopcartTotal.setText("合计:" + getTotalPrice());
    }

    public double getTotalPrice() {
        double totalPrice = 0.0;
        if (datas != null && datas.size() > 0) {
            for (int i = 0; i < datas.size(); i++) {
                GoodsBean goodsBean = datas.get(i);
                if (goodsBean.isSelected()) {
                    totalPrice = totalPrice + Double.valueOf(goodsBean.getNumber()) * Double.valueOf(goodsBean.getCover_price());
                }
            }
        }

        return totalPrice;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = View.inflate(mContext, R.layout.item_shop_cart, null);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        //根据位置得到对应的bean对象
        final GoodsBean goodsBean = datas.get(position);
        //设置数据
        holder.cb_gov.setChecked(goodsBean.isSelected());
        Glide.with(mContext).load(Constants.BASE_URL_IMAGE + goodsBean.getFigure()).into(holder.iv_gov);
        holder.tv_desc_gov.setText(goodsBean.getName());
        holder.tv_price_gov.setText("￥" + goodsBean.getCover_price());
        holder.addSubView.setValue(goodsBean.getNumber());
        holder.addSubView.setMinValue(1);
        holder.addSubView.setMaxValue(10);
        holder.addSubView.setOnNumberChangeListener(new AddSubView.OnNumberChangeListener() {
            @Override
            public void onNumberChange(int value) {
                goodsBean.setNumber(value);
                CartStorage.getInstance().updateData(goodsBean);
                setListener();
                checkAll();
                showTotalPrice();
                notifyItemChanged(position);
            }
        });
    }

    private void setListener() {
        setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                //1.根据位置找到对应的bean对象
                GoodsBean goodsBean = datas.get(position);
                //2.设置取反状态
                goodsBean.setSelected(!goodsBean.isSelected());
                //3.刷新状态
                notifyItemChanged(position);
                //4.校验是否全选
                checkAll();
                //5.重新计算总价钱
                showTotalPrice();
            }
        });
        checkboxAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean isCheck = checkboxAll.isChecked();//得到状态
                checkAll_none(isCheck);
                showTotalPrice();  //计算总价钱
            }
        });
        cbAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean isCheck = cbAll.isChecked();//得到状态
                checkAll_none(isCheck);
            }
        });
    }

    //设置全选和非全选
    public void checkAll_none(boolean isCheck) {
        if (datas != null && datas.size() > 0) {
            for (int i = 0; i < datas.size(); i++) {
                GoodsBean goodsBean = datas.get(i);
                goodsBean.setSelected(isCheck);
                notifyItemChanged(i);
            }
        }
    }

    public void checkAll() {
        if (datas != null && datas.size() > 0) {
            int number = 0;
            for (int i = 0; i < datas.size(); i++) {
                GoodsBean goodsBean = datas.get(i);
                if (!goodsBean.isSelected()) {
                    checkboxAll.setChecked(false);//非全选
                    cbAll.setChecked(false);
                } else {
                    number++;//选中
                }
            }
            if (number == datas.size()) {
                checkboxAll.setChecked(true);//全选
                cbAll.setChecked(true);
            } else {
                checkboxAll.setChecked(false);//非全选
                cbAll.setChecked(false);
            }
        }
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    //删除选中的
    public void deleteData() {
        if (datas != null && datas.size() > 0) {
            for (int i = 0; i < datas.size(); i++) {
                GoodsBean goodsBean = datas.get(i);
                if (goodsBean.isSelected()) {
                    datas.remove(goodsBean);
                    CartStorage.getInstance().deleteData(goodsBean);
                    notifyItemRemoved(i);
                    i--;
                }
            }
        }
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private CheckBox cb_gov;
        private ImageView iv_gov;
        private TextView tv_desc_gov;
        private TextView tv_price_gov;
        private AddSubView addSubView;

        public ViewHolder(View itemView) {
            super(itemView);
            cb_gov = itemView.findViewById(R.id.cb_gov);
            iv_gov = itemView.findViewById(R.id.iv_gov);
            tv_desc_gov = itemView.findViewById(R.id.tv_desc_gov);
            tv_price_gov = itemView.findViewById(R.id.tv_price_gov);
            addSubView = itemView.findViewById(R.id.addSubView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (onItemClickListener != null) {
                        onItemClickListener.onItemClick(getLayoutPosition());
                    }
                }
            });
        }
    }

    //点击Item的监听者
    public interface OnItemClickListener {
        public void onItemClick(int position);
    }

    public OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }
}
