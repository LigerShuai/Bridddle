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

    private RetrofitManager() {
        init();
    }

    private static final class NetManagerHolder {
        static final RetrofitManager INSTANCE = new RetrofitManager();
    }

    public static RetrofitManager getInstance() {
        return NetManagerHolder.INSTANCE;
    }

    public <T> T create(Class<T> service) {
        return mRetrofit.create(service);
    }

    public void init() {
        // 初始化 OkHttpClient
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
//                .addInterceptor(new HttpLoggingInterceptor())
//                .addInterceptor(new LogInterceptor())
                .build();

        // 初始化 Retrofit
        mRetrofit = new Retrofit.Builder()
                .baseUrl(ApiConstants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())        //添加 Gson 适配器
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create()) //添加 RxJava 适配器
                .client(okHttpClient)
                .build();
    }

}
