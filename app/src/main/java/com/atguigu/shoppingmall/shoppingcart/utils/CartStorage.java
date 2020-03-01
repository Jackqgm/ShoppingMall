package com.atguigu.shoppingmall.shoppingcart.utils;

import android.content.Context;
import android.text.TextUtils;
import android.util.SparseArray;

import com.atguigu.shoppingmall.app.MyApplication;
import com.atguigu.shoppingmall.home.bean.GoodsBean;
import com.atguigu.shoppingmall.utils.CacheUtils;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jackqgm on 2020/2/28.
 */

public class CartStorage {

    public static final String JSON_CART = "json_cart";
    private static CartStorage instance;
    private final Context mContext;
    private SparseArray<GoodsBean> sparseArray;

    public CartStorage(Context context){
        this.mContext=context;
        sparseArray=new SparseArray<>(100);
        listToSparseArray();
    }

    private void listToSparseArray() {
        List<GoodsBean> goodsBeanList=getAllData();

        for (int i = 0; i < goodsBeanList.size(); i++) {
            GoodsBean goodsBean = goodsBeanList.get(i);
            sparseArray.put(Integer.parseInt(goodsBean.getProduct_id()), goodsBean);
        }
    }

    public List<GoodsBean> getAllData() {
        List<GoodsBean> goodsBeanList=new ArrayList<>();
        //1.从本地获取
        String json= CacheUtils.getString(mContext, JSON_CART);
        //2.用Gson转换成列表
        if (!TextUtils.isEmpty(json)) {
            goodsBeanList=new Gson().fromJson(json, new TypeToken<List<GoodsBean>>(){}.getType());
        }

        return goodsBeanList;
    }

    //得到购物车实例
    public static CartStorage getInstance(){
        if (instance==null){
            instance=new CartStorage(MyApplication.getContext());
        }
        return instance;
    }

    public void addData(GoodsBean goodsBean){
        //1.添加到内存中sparseArray
        GoodsBean tempData=sparseArray.get(Integer.parseInt(goodsBean.getProduct_id()));
        if(tempData!=null){
            tempData.setNumber(goodsBean.getNumber()+1);
        }else{
            tempData=goodsBean;
            tempData.setNumber(1);
        }
        //2.同步到内存
        sparseArray.put(Integer.parseInt(tempData.getProduct_id()), tempData);

        //3.同步到本地
        saveLocal();
    }

    public void deleteData(GoodsBean goodsBean){
        sparseArray.delete(Integer.parseInt(goodsBean.getProduct_id()));
        saveLocal();
    }

    public void updateData(GoodsBean goodsBean){
        sparseArray.put(Integer.parseInt(goodsBean.getProduct_id()), goodsBean);
        saveLocal();
    }

    private void saveLocal() {
        //1.SparseArray转换成list
        List<GoodsBean> goodsBeanList=sparseArrayToList();
        //2.使用Gson把列表转换成String类型
        String json = new Gson().toJson(goodsBeanList);
        //3.把String数据保存
        CacheUtils.saveString(mContext, JSON_CART, json);
    }

    private List<GoodsBean> sparseArrayToList() {
        List<GoodsBean> goodsBeanList=new ArrayList<>();
        for (int i = 0; i < sparseArray.size(); i++) {
            GoodsBean goodsBean=sparseArray.valueAt(i);
            goodsBeanList.add(goodsBean);
        }
        return goodsBeanList;
    }


}
