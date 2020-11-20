package com.liger.bridddle.ui;

import android.os.Bundle;
import android.util.Log;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.liger.bridddle.R;
import com.liger.bridddle.constant.ApiConstants;
import com.liger.bridddle.model.Token;
import com.liger.bridddle.net.NetManager;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

/**
 * @author Liger
 * @date 2020/11/19 15:49
 */
public class OauthActivity extends AppCompatActivity {

    private final String TAG = getClass().getName();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_oauth);
        initView();
    }

    private void initView() {
        WebView myWebView = findViewById(R.id.webview);
        WebSettings webSettings = myWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        myWebView.loadUrl(ApiConstants.OAUTH_URL);
        myWebView.setWebViewClient(new MyWebViewClient());
    }

    private class MyWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
            view.canGoBack();
            return super.shouldOverrideUrlLoading(view, request);
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            Log.d(TAG, "onPageFinished: " + url);
            getCode(url);
            super.onPageFinished(view, url);
        }
    }

    private void getCode(String url) {
        String code = "";
        if (url.contains("code")) {
            code = url.substring(url.lastIndexOf("=") + 1);
            Log.d(TAG, "getCode: " + code);
            getToken(code);
        }

    }

    private void getToken(String code) {
        if (code.isEmpty()) return;
        NetManager.getInstance()
                .getToken(ApiConstants.CLIENT_ID, ApiConstants.CLIENT_SECRET, code)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Token>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull Token token) {
                        Log.d(TAG, "onNext: " + token);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.d(TAG, "onError: " + e.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        Log.d(TAG, "onComplete: ");
                    }
                });
    }

}