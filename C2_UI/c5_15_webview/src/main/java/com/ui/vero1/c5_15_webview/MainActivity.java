package com.ui.vero1.c5_15_webview;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MainActivity extends AppCompatActivity {
    private WebView webView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        Uri uri=Uri.parse("http://www.baidu.com");
//        Intent i=new Intent(Intent.ACTION_VIEW,uri);
//        startActivity(i);
        webView=(WebView)findViewById(R.id.webview);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setDomStorageEnabled(true);
        webView.loadUrl("www.baidu.com");


//        webView.setWebViewClient(new WebViewClient() {
//            @Override
//            public boolean shouldOverrideUrlLoading(WebView view, String url) {
//                //在WebView中打开
//                if (Uri.parse(url).getHost().equals("www.baidu.com")){
//                    return false;
//                //在系统浏览器中打开
//                }else {
//                    Intent i=new Intent(Intent.ACTION_VIEW,Uri.parse(url));
//                    startActivity(i);
//                    return true;
//                }
//            }
//        });
    }

    //按back键回到前一个页面，而不是退出Activity
//    @Override
//    public boolean onKeyDown(int keyCode, KeyEvent event) {
//        if (keyCode==KeyEvent.KEYCODE_BACK&&webView.canGoBack()){
//            webView.goBack();
//            return true;
//        }
//        return false;
//    }
}
