package com.coinmarket.main;

import com.coinmarket.POJO.NameValuePair;

public interface MainContract {
    void showValuePair(NameValuePair nameValuePair);
    void showError(String error);
    void hideProgress();
}
