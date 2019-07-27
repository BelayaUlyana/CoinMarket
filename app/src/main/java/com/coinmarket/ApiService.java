package com.coinmarket;

import com.coinmarket.listPOJO.NameValuePair;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;

public interface ApiService {
    @GET("listings/latest")
    Call<NameValuePair> getAllPairs(@Header("X-CMC_PRO_API_KEY") String API_KEY);
}
