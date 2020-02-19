package com.atguigu.shoppingmall.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Jackqgm on 2020/2/19.
 */

public abstract class BaseFragment extends Fragment{

    public Context mContext;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext=getActivity();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return initView();
    }

    //抽象类,由子类实现,
    public abstract View initView();

    //当Activity被创建时回调此方法
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
    }

    //当子类需要联网请求时,可以重写该方法
    public void initData() {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}
