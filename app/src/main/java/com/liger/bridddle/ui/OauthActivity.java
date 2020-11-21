package com.liger.bridddle.ui;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.blankj.utilcode.util.SPStaticUtils;
import com.liger.bridddle.R;
import com.liger.bridddle.constant.ApiConstants;
import com.liger.bridddle.model.Token;
import com.liger.bridddle.net.NetManager;
import com.liger.bridddle.ui.activities.MainActivity;

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
            String url = request.getUrl().toString();
            if (url.contains("code")) {
                //重定向网页，参数中包含了code
//                view.stopLoading();
                String code = url.substring(url.lastIndexOf("=") + 1);
                getToken(code);
//                skipMain(url);
                return true;
            }
            view.loadUrl(url);
            Log.d(TAG, "load url: " + url);
            return false;
        }
    }

    private void skipMain(String url) {
        String substring = url.substring(0, url.indexOf("?"));
        Uri uri = Uri.parse(substring);
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);
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
                        if (token.getToken().isEmpty()) return;
                        SPStaticUtils.put("code", token.getToken());
                        startActivity(new Intent(OauthActivity.this, MainActivity.class));
                        Log.d(TAG, "onNext: " + token.toString());
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