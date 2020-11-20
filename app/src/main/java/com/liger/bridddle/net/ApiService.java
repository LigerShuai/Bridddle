package com.liger.bridddle.net;

import com.liger.bridddle.model.Token;

import java.util.Map;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

/**
 * @author Liger
 * @date 2020/11/18 17:18
 */
public interface ApiService {

    @GET("oauth/authorize")
    Observable<String> getCode(@QueryMap Map<String, String> map);

    @POST("oauth/token")
    Observable<Token> getToken(@QueryMap Map<String, String> map);

    @POST("oauth/token")
    Observable<Token> getToken(@Query("client_id") String client_id,
                               @Query("client_secret") String client_secret,
                               @Query("code") String code);
}
