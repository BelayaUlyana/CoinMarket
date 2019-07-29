package com.coinmarket.info;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.coinmarket.R;
import com.coinmarket.info.fragments.DetailFragment;
import com.coinmarket.info.fragments.ExchangeFragment;
import com.coinmarket.info.fragments.GraphFragment;
import com.coinmarket.info.fragments.SignalFragment;
import com.coinmarket.listPOJO.Datum;
import com.coinmarket.listPOJO.Quote;

import java.text.ParseException;

public class InfoActivity extends AppCompatActivity implements SignalFragment.SignalListener {
    static final String TAG = "LOG  ";

    private SignalFragment signalFragment;
    private Datum datum;
    private Quote quote;
    TextView tvPrice, percentChangeHR1, percentChangeHR24, percentChangeD7, tvDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        datum = getIntent().getParcelableExtra("datum");
        quote = getIntent().getParcelableExtra("quote");

        Log.d(TAG, "datum = " + datum);
        Log.d(TAG, "usd = " + quote.getUSD().getPrice());

        try {
            initViewPager();
            Log.d(TAG, "getDateAdded = " + datum.getDateAdded());
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }

    private void initViewPager() throws ParseException {

        DetailFragment detailFragment = DetailFragment.newInstance();
        ExchangeFragment exchangeFragment = ExchangeFragment.newInstance();
        GraphFragment graphFragment = GraphFragment.newInstance();
        signalFragment = SignalFragment.newInstance();

        tvPrice = findViewById(R.id.price);
        tvDate = findViewById(R.id.date);
        percentChangeHR1 = findViewById(R.id.hr1);
        percentChangeHR24 = findViewById(R.id.hr24);
        percentChangeD7 = findViewById(R.id.d7);

//        Log.d(TAG, "getPrice = " + quote.getUSD().getPriceStr());


        tvPrice.setText(quote.getUSD().getPriceStr());
        tvDate.setText(datum.getDateAdded());
        percentChangeHR1.setText(quote.getUSD().getStrPercentChange1h().append((" 1h")));
        percentChangeHR24.setText(quote.getUSD().getStrPercentChange24h().append(" 1d"));
        percentChangeD7.setText(quote.getUSD().getStrPercentChange7d().append(" 7d"));

        SlideFragmentPagerAdapter pagerAdapter = new SlideFragmentPagerAdapter(getSupportFragmentManager());
        pagerAdapter.addFragment(detailFragment, "Detail");
        pagerAdapter.addFragment(exchangeFragment, "Exchange");
        pagerAdapter.addFragment(graphFragment, "Graph");
        pagerAdapter.addFragment(signalFragment, "Signal");

        ViewPager pager = findViewById(R.id.pager);
        pager.setAdapter(pagerAdapter);
        pager.setOffscreenPageLimit(2);

        TabLayout tabLayout = findViewById(R.id.tabLayout);
        tabLayout.setupWithViewPager(pager);

        pager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                Log.d(TAG, "onPageSelected, position = " + position);
                if (position == 3) {
                    signalFragment.changeText("new text");
                }
            }

            @Override
            public void onPageScrolled(int position, float positionOffset,
                                       int positionOffsetPixels) {
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });

    }


    @Override
    public void add() {
        Log.d(TAG, " кнопка работает");
    }
}
