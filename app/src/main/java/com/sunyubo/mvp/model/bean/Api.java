package com.sunyubo.mvp.model.bean;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * 1.类的用途
 * 2.@author:Sunyubo
 * 3.@ 2017/2/21.
 */

public class Api {
    public static ApiServer getRetrofit(){
        return  RetrofitInstance.apiServer;
    }

    static  class RetrofitInstance{
        static ApiServer apiServer=new Retrofit.Builder()
                .baseUrl("http://v.juhe.cn/joke/")
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build().create(ApiServer.class);

    }
}
