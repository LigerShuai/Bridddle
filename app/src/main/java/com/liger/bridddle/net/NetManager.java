package com.liger.bridddle.net;

import com.liger.bridddle.constant.ApiConstants;
import com.liger.bridddle.model.Token;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.rxjava3.core.Observable;

/**
 * @author Liger
 * @date 2020/11/19 14:21
 */
public class NetManager {

    public final String TAG = getClass().getName();
    private ApiService mApiService;
    private volatile static NetManager instance;

    private NetManager(String baseUrl) {
        mApiService = RetrofitManager.getInstance(baseUrl).create(ApiService.class);
    }

    public static NetManager getInstance(String baseUrl) {
        if (instance == null) {
            synchronized (NetManager.class) {
                if (instance == null) {
                    instance = new NetManager(baseUrl);
                }
            }
        }
        return instance;
    }

    public static NetManager getInstance() {
        if (instance == null) {
            synchronized (NetManager.class) {
                if (instance == null) {
                    instance = new NetManager(ApiConstants.DATA_BASE_URL);
                }
            }
        }
        return instance;
    }

//    private static final class NetManagerHolder {
//        static final NetManager INSTANCE = new NetManager();
//    }
//
//    public static NetManager getInstance() {
//        return NetManagerHolder.INSTANCE;
//    }

    public Observable<String> getCode() {
        Map<String, String> map = new HashMap<>();
        map.put("client_id", ApiConstants.CLIENT_ID);
        map.put("scope", "public+upload");
        return mApiService.getCode(map);
    }

    /*public Map<String, String> setLoginParams(Map<String, String> map) {
        map.put("client_id", ApiConstants.CLIENT_ID);
        map.put("client_secret", ApiConstants.CLIENT_SECRET);
        return map;
    }

    public Observable<Token> getToken(Map<String, String> map) {
        return mApiService.getToken(map);
    }*/

    public Observable<Token> getToken(String clientId, String clientSecret, String code) {
        return mApiService.getToken(clientId, clientSecret, code);
    }


}
