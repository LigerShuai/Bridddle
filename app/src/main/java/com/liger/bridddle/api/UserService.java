package com.liger.bridddle.api;

import com.liger.bridddle.constant.ApiConstants;
import com.liger.bridddle.model.User;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.GET;
import retrofit2.http.Header;

/**
 * @author Liger
 * @date 2020/11/21 18:52
 */
public interface UserService {

    String BASE_URL = ApiConstants.DATA_BASE_URL;

    @GET("user")
    Observable<User> getUser(@Header("Authorization") String header);

}
