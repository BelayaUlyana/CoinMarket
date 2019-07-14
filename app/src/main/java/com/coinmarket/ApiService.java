package com.coinmarket;

import com.coinmarket.infoPOJO.InfoList;
import com.coinmarket.listPOJO.NameValuePair;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;

public interface ApiService {
    @GET("listings/latest")
    Call<NameValuePair> getAllPairs(@Header("X-CMC_PRO_API_KEY") String API_KEY);


    @GET("info?id={id}")
    Call<InfoList> getAllPairs(@Header("X-CMC_PRO_API_KEY") String API_KEY, @Query("id") Integer id);
}
