package com.coinmarket.main;

import android.util.Log;

import com.coinmarket.RetrofitClient;
import com.coinmarket.POJO.NameValuePair;

import retrofit2.Callback;
import retrofit2.Response;

class MainPresenter {

    private MainContract mainContract;

    MainPresenter(MainContract mainContract) {
        this.mainContract = mainContract;
    }

    void getDatum() {
        retrofit2.Call<NameValuePair> call = RetrofitClient.getApiService().getAllPairs(RetrofitClient.API_KEY);
        call.enqueue(new Callback<NameValuePair>() {
            @Override
            public void onResponse(retrofit2.Call<NameValuePair> call, Response<NameValuePair> response) {
                Log.e("LOG ", response.toString());
                mainContract.showValuePair(response.body());
                mainContract.hideProgress();
            }

            @Override
            public void onFailure(retrofit2.Call<NameValuePair> call, Throwable t) {
                t.printStackTrace();
                mainContract.showError(t.getMessage());
                mainContract.hideProgress();
            }
        });
    }

    void getUSD(){

    }

}
