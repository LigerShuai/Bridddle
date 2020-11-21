package com.liger.bridddle.net;

import com.liger.bridddle.constant.ApiConstants;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author Liger
 * @date 2020/10/31 12:32
 */
public class RetrofitManager {

    public final String TAG = getClass().getName();
    private Retrofit mRetrofit;
    private volatile static RetrofitManager instance;

    private RetrofitManager(String baseUrl) {
        init(baseUrl);
    }

    public static RetrofitManager getInstance(String baseUrl) {
        if (instance == null) {
            synchronized (RetrofitManager.class) {
                if (instance == null) {
                    instance = new RetrofitManager(baseUrl);
                }
            }
        }
        return instance;
    }

    public <T> T create(Class<T> service) {
        return mRetrofit.create(service);
    }

    public void init(String baseUrl) {
        // 初始化 OkHttpClient
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
//                .addInterceptor(new HttpLoggingInterceptor())
//                .addInterceptor(new LogInterceptor())
                .build();

        // 初始化 Retrofit
        mRetrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())        //添加 Gson 适配器
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create()) //添加 RxJava 适配器
                .client(okHttpClient)
                .build();
    }

}
