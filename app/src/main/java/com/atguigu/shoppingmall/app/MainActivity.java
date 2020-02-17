package com.atguigu.shoppingmall.app;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.atguigu.shoppingmall.R;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class MainActivity extends FragmentActivity {

    @InjectView(R.id.framelayout)
    FrameLayout framelayout;
    @InjectView(R.id.rb_home)
    RadioButton rbHome;
    @InjectView(R.id.rb_type)
    RadioButton rbType;
    @InjectView(R.id.rb_comunity)
    RadioButton rbComunity;
    @InjectView(R.id.rb_cart)
    RadioButton rbCart;
    @InjectView(R.id.rb_user)
    RadioButton rbUser;
    @InjectView(R.id.rg_main)
    RadioGroup rgMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //butterKnife与当前类绑定
        ButterKnife.inject(this);
        rgMain.check(R.id.rb_home);
    }

    @OnClick({R.id.rb_home, R.id.rb_type, R.id.rb_comunity, R.id.rb_cart, R.id.rb_user})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rb_home:
                break;
            case R.id.rb_type:
                break;
            case R.id.rb_comunity:
                break;
            case R.id.rb_cart:
                break;
            case R.id.rb_user:
                break;
        }
    }
}
