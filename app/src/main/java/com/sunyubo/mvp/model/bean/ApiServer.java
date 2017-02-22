package com.sunyubo.mvp.model.bean;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * 1.类的用途
 * 2.@author:Sunyubo
 * 3.@ 2017/2/21.
 */

public interface ApiServer {
    @GET("randJoke.php?key=26e9ed96a3ea69201844871a9ef4acec")
    Observable<HttpResult<List<Joker>>> getJokerData(@Query("type") String type);
}
