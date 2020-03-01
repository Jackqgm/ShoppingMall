package com.atguigu.shoppingmall.app;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.atguigu.shoppingmall.R;
import com.atguigu.shoppingmall.home.bean.GoodsBean;
import com.atguigu.shoppingmall.shoppingcart.utils.CartStorage;
import com.atguigu.shoppingmall.utils.Constants;
import com.bumptech.glide.Glide;

import java.io.Serializable;

public class GoodsInfoActivity extends Activity implements View.OnClickListener {
    private ImageButton ibGoodInfoBack;
    private ImageButton ibGoodInfoMore;
    private ImageView ivGoodInfoImage;
    private TextView tvGoodInfoName;
    private TextView tvGoodInfoDesc;
    private TextView tvGoodInfoPrice;
    private TextView tvGoodInfoStore;
    private TextView tvGoodInfoStyle;
    private WebView wbGoodInfoMore;
    private LinearLayout llGoodsRoot;
    private TextView tvGoodInfoCallcenter;
    private TextView tvGoodInfoCollection;
    private TextView tvGoodInfoCart;
    private Button btnGoodInfoAddcart;
    private TextView tv_more_share;
    private TextView tv_more_search;
    private TextView tv_more_home;
    private Button btn_more;
    private GoodsBean goodsbean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods_info);
        findViews();
        //接收数据
        goodsbean = (GoodsBean) getIntent().getSerializableExtra("goodsbean");
        if (goodsbean!=null){
            Toast.makeText(this, "goodsbean====="+goodsbean, Toast.LENGTH_SHORT).show();
            setDataFromView(goodsbean);
            setWebViewData(goodsbean.getProduct_id());
        }
    }

    private void setWebViewData(String product_id) {
        if (product_id!=null){
            wbGoodInfoMore.loadUrl("http://www.atguigu.com");
            //设置支持JavaScript
            WebSettings settings = wbGoodInfoMore.getSettings();
            settings.setUseWideViewPort(true);
            settings.setJavaScriptEnabled(true);
            settings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);

            wbGoodInfoMore.setWebViewClient(new WebViewClient(){
                @Override
                public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        view.loadUrl(request.getUrl().toString());
                    }
                    return true;
                }
            });
        }
    }

    private void setDataFromView(GoodsBean goodsbean) {

        Glide.with(this).load(Constants.BASE_URL_IMAGE+goodsbean.getFigure()).into(ivGoodInfoImage);
        tvGoodInfoName.setText(goodsbean.getName());
        tvGoodInfoPrice.setText("$"+goodsbean.getCover_price() );
    }

    private void findViews() {
        ibGoodInfoBack = findViewById(R.id.ib_good_info_back);
        ibGoodInfoMore = findViewById(R.id.ib_good_info_more);
        ivGoodInfoImage = findViewById(R.id.iv_good_info_image);
        tvGoodInfoName = findViewById(R.id.tv_good_info_name);
        tvGoodInfoDesc = findViewById(R.id.tv_good_info_desc);
        tvGoodInfoPrice = findViewById(R.id.tv_good_info_price);
        tvGoodInfoStore = findViewById(R.id.tv_good_info_store);
        tvGoodInfoStyle = findViewById(R.id.tv_good_info_style);
        wbGoodInfoMore = findViewById(R.id.wb_good_info_more);
        llGoodsRoot = findViewById(R.id.ll_goods_root);
        btn_more = findViewById(R.id.btn_more);
        tv_more_share = findViewById(R.id.tv_more_share);
        tv_more_search = findViewById(R.id.tv_more_search);
        tv_more_home = findViewById(R.id.tv_more_home);

        tvGoodInfoCallcenter = findViewById(R.id.tv_good_info_callcenter);
        tvGoodInfoCollection = findViewById(R.id.tv_good_info_collection);
        tvGoodInfoCart = findViewById(R.id.tv_good_info_cart);
        btnGoodInfoAddcart = findViewById(R.id.btn_good_info_addcart);

        ibGoodInfoBack.setOnClickListener(this);
        ibGoodInfoMore.setOnClickListener(this);
        btnGoodInfoAddcart.setOnClickListener(this);

        tvGoodInfoCallcenter.setOnClickListener(this);
        tvGoodInfoCollection.setOnClickListener(this);
        tvGoodInfoCart.setOnClickListener(this);

        tv_more_share.setOnClickListener(this);
        tv_more_search.setOnClickListener(this);
        tv_more_home.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == ibGoodInfoBack) {
            // Handle clicks for ibGoodInfoBack
        } else if (v == ibGoodInfoMore) {
            // Handle clicks for ibGoodInfoMore
            Toast.makeText(this, "更多", Toast.LENGTH_SHORT).show();
        } else if (v == btnGoodInfoAddcart) {
            // Handle clicks for btnGoodInfoAddcart
            CartStorage.getInstance().addData(goodsbean);
            Toast.makeText(this, "添加到购物车", Toast.LENGTH_SHORT).show();

        } else if (v == tvGoodInfoCallcenter) {
            // Handle clicks for ibGoodInfoMore
            Toast.makeText(this, "客服中心", Toast.LENGTH_SHORT).show();
        } else if (v == tvGoodInfoCollection) {
            // Handle clicks for btnGoodInfoAddcart
            Toast.makeText(this, "收藏", Toast.LENGTH_SHORT).show();
        } else if (v == tvGoodInfoCart) {
            // Handle clicks for ibGoodInfoMore
            Toast.makeText(this, "购物车", Toast.LENGTH_SHORT).show();

        }else if (v == tv_more_share) {
            // Handle clicks for ibGoodInfoMore
            Toast.makeText(this, "分享", Toast.LENGTH_SHORT).show();
        } else if (v == tv_more_search) {
            // Handle clicks for btnGoodInfoAddcart
            Toast.makeText(this, "搜索", Toast.LENGTH_SHORT).show();
        } else if (v == tv_more_home) {
            // Handle clicks for ibGoodInfoMore
            Toast.makeText(this, "主页面", Toast.LENGTH_SHORT).show();
        }
    }
}