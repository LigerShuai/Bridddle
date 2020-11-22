package com.liger.bridddle.net;

import com.liger.bridddle.api.ApiService;
import com.liger.bridddle.api.UserService;
import com.liger.bridddle.model.Token;
import com.liger.bridddle.model.User;

import io.reactivex.rxjava3.core.Observable;

/**
 * @author Liger
 * @date 2020/11/19 14:21
 */
public class NetManager {

    public final String TAG = getClass().getName();
    private volatile static NetManager instance;
    private RetrofitManager mRetrofitManager;

    private NetManager() {
        mRetrofitManager = RetrofitManager.getInstance();
    }

    public static NetManager getInstance() {
        if (instance == null) {
            synchronized (NetManager.class) {
                if (instance == null) {
                    instance = new NetManager();
                }
            }
        }
        return instance;
    }

    public Observable<Token> getToken(String clientId, String clientSecret, String code) {
        return mRetrofitManager.create(ApiService.class).getToken(clientId, clientSecret, code);
    }

    public Observable<User> getUser(String header) {
        return mRetrofitManager.create(UserService.class).getUser(header);
    }


}
