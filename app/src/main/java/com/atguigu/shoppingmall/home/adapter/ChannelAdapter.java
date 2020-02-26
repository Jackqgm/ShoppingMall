package com.atguigu.shoppingmall.home.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.atguigu.shoppingmall.R;
import com.atguigu.shoppingmall.home.bean.ResultBeanData;
import com.atguigu.shoppingmall.utils.Constants;
import com.bumptech.glide.Glide;
import java.util.List;

/**
 * Created by Jackqgm on 2020/2/22.
 */

public class ChannelAdapter extends BaseAdapter {
    private Context mContext;
    private List<ResultBeanData.ResultBean.ChannelInfoBean> datas;

    public ChannelAdapter(Context mContext, List<ResultBeanData.ResultBean.ChannelInfoBean> channel_info) {
        this.mContext = mContext;
        this.datas = channel_info;
    }

    @Override
    public int getCount() {
        return datas.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView=View.inflate(mContext, R.layout.item_channel, null);
            viewHolder = new ViewHolder();
            viewHolder.iv_icon = convertView.findViewById(R.id.iv_channel);
            viewHolder.tv_title = convertView.findViewById(R.id.tv_channel);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        //根据位置得到对应的数据
        ResultBeanData.ResultBean.ChannelInfoBean channelInfoBean = datas.get(i);
        Glide.with(mContext).load(Constants.BASE_URL_IMAGE + channelInfoBean.getImage()).into(viewHolder.iv_icon);
        viewHolder.tv_title.setText(channelInfoBean.getChannel_name());
        return convertView;
    }

    static class ViewHolder {
        ImageView iv_icon;
        TextView tv_title;
    }

}
