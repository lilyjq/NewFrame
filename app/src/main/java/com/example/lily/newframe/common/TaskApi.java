package com.example.lily.newframe.common;


import com.example.lily.newframe.entity.UserInfo;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by ljq
 * on 2018/5/21.
 */

public interface TaskApi {


    @GET("http:10.33.66.82:8080/getUserName.html")
    Observable<Response<UserInfo>> LoginIn(@Query("name") String name, @Query("password") String password);


}
