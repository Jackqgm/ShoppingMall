package com.atguigu.shoppingmall.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.renderscript.Sampler;

import com.bumptech.glide.disklrucache.DiskLruCache;

/**
 * Created by Jackqgm on 2020/2/28.
 */

public class CacheUtils {
    public static String getString(Context mContext, String key) {
        SharedPreferences sp = mContext.getSharedPreferences("atguigu", Context.MODE_PRIVATE);
        return sp.getString(key,"");
    }

    public static void saveString(Context mContext, String key, String value) {
        SharedPreferences sp = mContext.getSharedPreferences("atguigu", Context.MODE_PRIVATE);
        sp.edit().putString(key, value).commit();
    }
}
