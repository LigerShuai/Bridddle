package com.liger.bridddle.ui;

import android.os.Bundle;
import android.util.Log;

import com.liger.bridddle.R;
import com.liger.bridddle.base.BaseActivity;
import com.liger.bridddle.model.User;
import com.liger.bridddle.net.NetManager;
import com.liger.bridddle.utils.Util;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        iniData();
    }

    private void iniData() {
        String header = Util.getHeader();
//        String header = "Bearer 63c620af92b79f41fd4fdb8142f88733e6c3d1a8ceed9a05bde3a159130e9e98";
        Log.d(TAG, "iniData: " + header);
        NetManager.getInstance()
                .getUser(header)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<User>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull User user) {
                        Log.d(TAG, "onNext: " + user.toString());
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.d(TAG, "onError: " + e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}