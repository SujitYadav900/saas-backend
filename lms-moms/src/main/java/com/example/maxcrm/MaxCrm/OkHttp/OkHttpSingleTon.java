package com.example.maxcrm.MaxCrm.OkHttp;

import okhttp3.ConnectionPool;
import okhttp3.OkHttpClient;

import java.util.concurrent.TimeUnit;

public class OkHttpSingleTon {

    public static final OkHttpClient client = new OkHttpClient.Builder()
            .readTimeout(120, TimeUnit.SECONDS)
            .writeTimeout(120, TimeUnit.SECONDS)
            .connectTimeout(120,TimeUnit.SECONDS)
            .connectionPool(new ConnectionPool(10,120,TimeUnit.SECONDS))

            .build();

}
