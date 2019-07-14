package com.coinmarket.main;

import android.util.Log;

import com.coinmarket.RetrofitClient;
import com.coinmarket.listPOJO.NameValuePair;

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
                mainContract.showInfo(response.body());
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

//    void getImage(String id){
//        retrofit2.Call<InfoList> call = RetrofitClient.getApiService().getAllPairs(RetrofitClient.API_KEY, id);
//        call.enqueue(new Callback<InfoList>() {
//            @Override
//            public void onResponse(retrofit2.Call<InfoList> call, Response<InfoList> response) {
//                Log.e("LOG ", response.toString());
//                mainContract.showInfoList(response.body());
//                mainContract.hideProgress();
//            }
//
//            @Override
//            public void onFailure(retrofit2.Call<InfoList> call, Throwable t) {
//                t.printStackTrace();
//                mainContract.showError(t.getMessage());
//                mainContract.hideProgress();
//            }
//        });
//    }

}
