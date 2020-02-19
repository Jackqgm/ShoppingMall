package com.atguigu.shoppingmall.app;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.atguigu.shoppingmall.R;
import com.atguigu.shoppingmall.base.BaseFragment;
import com.atguigu.shoppingmall.community.fragment.CommunityFragment;
import com.atguigu.shoppingmall.home.fragment.HomeFragment;
import com.atguigu.shoppingmall.shoppingcart.fragment.ShoppingcartFragment;
import com.atguigu.shoppingmall.type.fragment.TypeFragment;
import com.atguigu.shoppingmall.user.fragment.UserFragment;

import java.util.ArrayList;

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
    private ArrayList<BaseFragment> fragments;
    private int position=0;
    private Fragment tempFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //butterKnife与当前类绑定
        ButterKnife.inject(this);

        //初始化Fragment


        initFragment();

        //设置RadioGroup的监听
        initListener();

    }

    private void initListener() {
        rgMain.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i) {
                    case R.id.rb_home://首页
                        position=0;
                        break;
                    case R.id.rb_type://分类
                        position=1;
                        break;
                    case R.id.rb_comunity://发现
                        position=2;
                        break;
                    case R.id.rb_cart://购物车
                        position=3;
                        break;
                    case R.id.rb_user://用户中心
                        position=4;
                        break;
                    default:
                        position=0;
                        break;
                }
                //根据位置取不同的Fragment
                BaseFragment baseFragment = getFragment(position);

                //参数1.上次显示的Fragment; 参数2. 当前要显示的Fragment
                switchFragment(tempFragment, baseFragment);
            }
        });

        rgMain.check(R.id.rb_home);
    }

    private void initFragment() {
        fragments = new ArrayList<>();
        fragments.add(new HomeFragment());
        fragments.add(new TypeFragment());
        fragments.add(new CommunityFragment());
        fragments.add(new ShoppingcartFragment());
        fragments.add(new UserFragment());
    }

    private BaseFragment getFragment(int position){
       if (fragments!=null&&fragments.size()>0){
           BaseFragment baseFragment = fragments.get(position);
           return baseFragment;
       }
        return null;
    }

    private void switchFragment(Fragment fromFragment,BaseFragment nextFragment){
        if (tempFragment!=nextFragment){
            tempFragment=nextFragment;
            if (nextFragment!=null){
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                if (!nextFragment.isAdded()){
                    if (fromFragment!=null){
                        transaction.hide(fromFragment);
                    }
                    transaction.add(R.id.framelayout, nextFragment).commit();
                }else {
                    if (fromFragment!=null){
                        transaction.hide(fromFragment);
                    }
                    transaction.show(nextFragment).commit();
                }
            }
        }
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
