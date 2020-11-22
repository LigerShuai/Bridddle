package com.liger.bridddle.net;

import java.lang.reflect.Field;

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
    private volatile static RetrofitManager instance;

    private RetrofitManager() {
    }

    public static RetrofitManager getInstance() {
        if (instance == null) {
            synchronized (RetrofitManager.class) {
                if (instance == null) {
                    instance = new RetrofitManager();
                }
            }
        }
        return instance;
    }

    private OkHttpClient getOkHttpClient() {
        return new OkHttpClient.Builder()
//                .addInterceptor(new HttpLoggingInterceptor())
//                .addInterceptor(new LogInterceptor())
                .build();
    }

    private Retrofit getRetrofit(String baseUrl) {
        return new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())        //添加 Gson 适配器
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create()) //添加 RxJava 适配器
                .client(getOkHttpClient())
                .build();
    }

    public <T> T create(Class<T> serviceClass) {
        String baseUrl = "";
        try {
            Field field = serviceClass.getField("BASE_URL");
            try {
                baseUrl = (String) field.get(field);

                baseUrl = (String) field.get(field);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }

        return getRetrofit(baseUrl).create(serviceClass);
    }

}
