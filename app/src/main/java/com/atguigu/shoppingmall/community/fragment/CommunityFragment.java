package com.atguigu.shoppingmall.community.fragment;

import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.atguigu.shoppingmall.base.BaseFragment;

/**
 * Created by Jackqgm on 2020/2/19.
 */

public class CommunityFragment extends BaseFragment {

    private static final String TAG = "";
    private TextView textView;

    @Override
    public View initView() {
        Log.e(TAG, "发现页面的Fragment的UI被初始化了");

        textView = new TextView(mContext);
        textView.setGravity(Gravity.CENTER);
        textView.setTextSize(25);
        return textView;
    }

    @Override
    public void initData() {
        super.initData();
        textView.setText("发现页面内容");

    }
}
