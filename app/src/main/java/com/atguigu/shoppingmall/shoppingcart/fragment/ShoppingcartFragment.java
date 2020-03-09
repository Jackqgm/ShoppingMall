package com.atguigu.shoppingmall.shoppingcart.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.atguigu.shoppingmall.R;
import com.atguigu.shoppingmall.base.BaseFragment;
import com.atguigu.shoppingmall.home.bean.GoodsBean;
import com.atguigu.shoppingmall.shoppingcart.adapter.ShoppingCartAdapter;
import com.atguigu.shoppingmall.shoppingcart.utils.CartStorage;

import java.util.List;

/**
 * Created by Jackqgm on 2020/2/19.
 */

public class ShoppingcartFragment extends BaseFragment implements View.OnClickListener {

    private static final String TAG = ShoppingcartFragment.class.getSimpleName();
    private TextView tvShopcartEdit;
    private RecyclerView recyclerview;
    private LinearLayout llCheckAll;
    private CheckBox checkboxAll;
    private TextView tvShopcartTotal;
    private Button btnCheckOut;
    private LinearLayout llDelete;
    private CheckBox cbAll;
    private Button btnDelete;
    private Button btnCollection;
    private ImageView ivEmpty;
    private TextView tvEmptyCartTobuy;
    private LinearLayout ll_empty_shopcart;
    private ShoppingCartAdapter adapter;

    private static final int ACTION_EDIT = 1;//编辑状态
    private static final int ACTION_COMPLETE = 2;//完成状态

    @Override
    public View initView() {
        Log.e(TAG, "购物车页面的Fragment的UI被初始化了");
        View view = View.inflate(getContext(), R.layout.fragment_shopping_cart, null);
        tvShopcartEdit = view.findViewById(R.id.tv_shopcart_edit);
        recyclerview = view.findViewById(R.id.recyclerview);
        llCheckAll = view.findViewById(R.id.ll_check_all);
        checkboxAll = view.findViewById(R.id.checkbox_all);
        tvShopcartTotal = view.findViewById(R.id.tv_shopcart_total);
        btnCheckOut = view.findViewById(R.id.btn_check_out);
        llDelete = view.findViewById(R.id.ll_delete);
        cbAll = view.findViewById(R.id.cb_all);
        btnDelete = view.findViewById(R.id.btn_delete);
        btnCollection = view.findViewById(R.id.btn_collection);

        ll_empty_shopcart = view.findViewById(R.id.ll_empty_shopcart);
        ivEmpty = view.findViewById(R.id.iv_empty);
        tvEmptyCartTobuy = view.findViewById(R.id.tv_empty_cart_tobuy);

        btnCheckOut.setOnClickListener(this);
        btnDelete.setOnClickListener(this);
        btnCollection.setOnClickListener(this);

        initListener();
        return view;
    }

    private void initListener() {
        //1.设置默认的状态
        tvShopcartEdit.setTag(ACTION_EDIT);
        tvShopcartEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int action= (int) view.getTag();
                if (action==ACTION_EDIT){
                    //切换成完成状态
                    showDelete();
                }else{
                    //切换成编辑状态
                    hideDelete();
                }
            }
        });
    }

    private void showDelete() {
        //1.设置状态和文本为 完成
        tvShopcartEdit.setTag(ACTION_COMPLETE);
        tvShopcartEdit.setText("完成");
        //2.变成非勾选
        if (adapter!=null){
            adapter.checkAll_none(false);
            adapter.checkAll();
        }
        //3.删除视图显示
        llDelete.setVisibility(View.VISIBLE);
        //4.结算视图隐藏
        llCheckAll.setVisibility(View.GONE);
    }

    private void hideDelete() {
        //1.设置状态和文本为 编辑
        tvShopcartEdit.setTag(ACTION_EDIT);
        tvShopcartEdit.setText("编辑");
        //2.变成非勾选
        if (adapter!=null){
            adapter.checkAll_none(true);
            adapter.checkAll();
            adapter.showTotalPrice();
        }
        //3.删除视图隐藏
        llDelete.setVisibility(View.GONE);
        //4.结算视图显示
        llCheckAll.setVisibility(View.VISIBLE);
    }

    @Override
    public void onClick(View v) {
        if (v == btnCheckOut) {
            // Handle clicks for btnCheckOut
        } else if (v == btnDelete) {
            // Handle clicks for btnDelete
            adapter.deleteData();//删除选中的
            //校验状态
            adapter.checkAll();
            //设置数据为0
            if (adapter.getItemCount()==0){
                emptyShoppingCart();
            }

        } else if (v == btnCollection) {
            // Handle clicks for btnCollection
        }
    }

    @Override
    public void initData() {
        super.initData();
        Log.e(TAG, "数据被初始化了");
    }

    @Override
    public void onResume() {
        super.onResume();
        showData();
    }

    private void showData() {
        List<GoodsBean> goodsBeanList = CartStorage.getInstance().getAllData();
        if (goodsBeanList != null && goodsBeanList.size() > 0) {
            tvShopcartEdit.setVisibility(View.VISIBLE);
            ll_empty_shopcart.setVisibility(View.GONE);
            llCheckAll.setVisibility(View.VISIBLE);

            adapter = new ShoppingCartAdapter(mContext, goodsBeanList, tvShopcartTotal, checkboxAll, cbAll);
            recyclerview.setAdapter(adapter);

            recyclerview.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));

        } else {
            emptyShoppingCart();
        }
    }

    private void emptyShoppingCart() {
        ll_empty_shopcart.setVisibility(View.VISIBLE);
        tvShopcartEdit.setVisibility(View.GONE);
        llDelete.setVisibility(View.GONE);
    }
}
