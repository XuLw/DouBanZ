package com.zjs.example.doubanz.net;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiService {

    private static volatile ApiService sApiService;

    public static ApiService getInstance() {
        if (sApiService == null)
            synchronized (ApiService.class) {
                if (sApiService == null)
                    sApiService = new ApiService();
            }
        return sApiService;
    }

    public Api getApi(final String baseUrl) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        Api api = retrofit.create(Api.class);
        return api;
    }


}
