package com.atguigu.shoppingmall.shoppingcart.fragment;

import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.atguigu.shoppingmall.base.BaseFragment;
import com.atguigu.shoppingmall.home.bean.GoodsBean;
import com.atguigu.shoppingmall.shoppingcart.utils.CartStorage;

import java.util.List;

/**
 * Created by Jackqgm on 2020/2/19.
 */

public class ShoppingcartFragment extends BaseFragment {

    private static final String TAG = "";
    private TextView textView;

    @Override
    public View initView() {
        Log.e(TAG, "购物车页面的Fragment的UI被初始化了");

        textView = new TextView(mContext);
        textView.setGravity(Gravity.CENTER);
        textView.setTextSize(25);
        return textView;
    }

    @Override
    public void initData() {
        super.initData();
        Log.e(TAG, "数据被初始化了");
        textView.setText("购物车页面内容");

        List<GoodsBean> goodsBeanList = CartStorage.getInstance().getAllData();
        for (int i = 0; i < goodsBeanList.size(); i++) {
            Log.e(TAG, goodsBeanList.get(i).toString());
        }
    }
}
