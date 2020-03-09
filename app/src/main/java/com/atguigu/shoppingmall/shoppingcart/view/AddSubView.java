package com.atguigu.shoppingmall.shoppingcart.view;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.atguigu.shoppingmall.R;

/**
 * Created by Jackqgm on 2020/2/29.
 */

public class AddSubView extends LinearLayout implements View.OnClickListener {

    private final ImageView iv_add;
    private final ImageView iv_sub;
    private final TextView tv_value;
    private final Context mContext;
    private int value = 1;
    private int minValue = 1;
    private int maxValue = 5;

    public AddSubView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mContext=context;
        //把布局文件实例化,并加载到AddSubView中
        View.inflate(context, R.layout.add_sub_view, this);
        iv_add = findViewById(R.id.iv_add);
        iv_sub = findViewById(R.id.iv_sub);
        tv_value = findViewById(R.id.tv_value);

        int value = getValue();
        setValue(value);

        iv_add.setOnClickListener(this);
        iv_sub.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_add:
                addNumber();
                break;
            case R.id.iv_sub:
                subNumber();
                break;
        }
        Toast.makeText(mContext, "当前商品数量=="+value, Toast.LENGTH_SHORT).show();
    }

    private void addNumber() {
        if (value < maxValue) {
            value++;
        }
        setValue(value);
        if (onNumberChangeListener!=null){
            onNumberChangeListener.onNumberChange(value);
        }
    }

    private void subNumber() {
        if (value > minValue) {
            value--;
        }
        setValue(value);
        if (onNumberChangeListener!=null){
            onNumberChangeListener.onNumberChange(value);
        }
    }

    public int getValue() {
        String valueStr = tv_value.getText().toString().trim();
        if (!TextUtils.isEmpty(valueStr)) {
            value = Integer.parseInt(valueStr);
        }
        return value;
    }

    public void setValue(int value) {
        this.value = value;
        tv_value.setText(value + "");
    }

    public int getMinValue() {
        return minValue;
    }

    public void setMinValue(int minValue) {
        this.minValue = minValue;
    }

    public int getMaxValue() {
        return maxValue;
    }

    public void setMaxValue(int maxValue) {
        this.maxValue = maxValue;
    }

    //当数量改变时回调
    public interface OnNumberChangeListener{

        public void onNumberChange(int value);
    }

    private OnNumberChangeListener onNumberChangeListener;

    public void setOnNumberChangeListener(OnNumberChangeListener onNumberChangeListener) {
        this.onNumberChangeListener = onNumberChangeListener;
    }
}
