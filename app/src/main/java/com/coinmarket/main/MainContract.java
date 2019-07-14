package com.coinmarket.main;


import com.coinmarket.listPOJO.NameValuePair;

public interface MainContract {
    void showInfo(NameValuePair nameValuePair);
    void showError(String error);
    void hideProgress();
}
