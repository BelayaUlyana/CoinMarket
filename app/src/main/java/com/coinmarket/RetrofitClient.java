package com.coinmarket;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    public static final String API_KEY = "91780fdc-70fd-4eb4-a766-f691b75236ba";
    private static final String BASE_URL = "https://pro-api.coinmarketcap.com/v1/cryptocurrency/";

    private static Retrofit getRetrofitInstance() {
        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static ApiService getApiService() {
        return getRetrofitInstance().create(ApiService.class);
    }
}
