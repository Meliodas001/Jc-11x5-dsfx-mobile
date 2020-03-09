package com.jingcai.jc_11x5.ui.activity;

import android.graphics.Bitmap;
import android.net.http.SslError;
import android.os.Bundle;
import android.view.View;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;

import com.jingcai.jc_11x5.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class HelpActivity extends BaseActivity {

    @Bind(R.id.iv_header_left)
    ImageView ivHeaderLeft;
    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.load_webview)
    WebView loadWebview;

    private String url = "http://39.108.153.232/download/help.html";
//    private String url = "https://www.baidu.com";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);
        ButterKnife.bind(this);
        initView();
    }

    @Override
    protected void initView() {
        tvTitle.setText("操作说明");
        ivHeaderLeft.setVisibility(View.VISIBLE);
        //支持js打开
        loadWebview.getSettings().setJavaScriptEnabled(true);



        loadWebview.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
            }

            //加载https时候，需要加入 下面代码
            @Override
            public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
                handler.proceed();//接受所有证书
            }
        });
        loadWebview.setWebChromeClient(new WebChromeClient());
        loadWebview.loadUrl(url);

    }


    @OnClick(R.id.iv_header_left)
    public void onViewClicked() {
        finish();
    }
}
