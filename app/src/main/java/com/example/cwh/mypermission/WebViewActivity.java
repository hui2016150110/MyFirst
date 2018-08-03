package com.example.cwh.mypermission;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

public class WebViewActivity extends AppCompatActivity {

    //实现WebView的变量声明
    private String url = "http://2014.qq.com";
    private WebView webView;
    private ProgressDialog progressDialog_web = null;
    private ProgressBar progressBar_web;
    private EditText editText_web;
    private Button button_goweb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.webview_item);
        createWebView();
    }
    private void createWebView(){
//        Uri uri = Uri.parse(url);
//        Intent intent = new Intent(Intent.ACTION_VIEW,uri);
//        startActivity(intent);

        webView = (WebView) findViewById(R.id.webView);
        progressBar_web =(ProgressBar) findViewById(R.id.progress_web);
        editText_web = (EditText) findViewById(R.id.edittext_web);
        button_goweb = findViewById(R.id.button_goweb);
        //加载本地资源
//        webView.loadUrl("file:///android_asset/baidu.html");
        //加载外部资源
        webView.loadUrl("https://m.baidu.com/?from=1012852s");

        //覆盖webview默认通过第三方或者系统的浏览器打开网页的行为，使得网页在webview中打开
        webView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view,String url){
                view.loadUrl(url);
                //返回值是true网页在webView中打开,否则就是在第三方浏览器中打开
                return true;
            }
            //webViewClient帮助webview去处理一些页面控制和请求通知
        });


        //启用支持javaScript,电脑端的网址在手机端不太兼容
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        //webView页面优先使用缓存
        webSettings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);


        webView.setWebChromeClient(new WebChromeClient(){
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                //newProgress是1到100的整数
                //显示加载速度
                progressBar_web.setProgress(newProgress);
                editText_web.setText(webView.getTitle());
            }
        });

        editText_web.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editText_web.setText(webView.getUrl());
            }
        });

        button_goweb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                webView.loadUrl(editText_web.getText().toString());
            }
        });

    }

    //改写物理键---返回逻辑
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if(keyCode==KeyEvent.KEYCODE_BACK){
            if(webView.canGoBack()){
                webView.goBack();
                return true;
            }
            else {
                System.exit(0);
            }
        }
        return super.onKeyDown(keyCode, event);
    }
}
