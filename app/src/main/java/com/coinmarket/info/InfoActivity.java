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

public class InfoActivity extends AppCompatActivity implements SignalFragment.SignalListener {
    static final String TAG = "LOG  ";

    private ViewPager pager;
    private SlideFragmentPagerAdapter pagerAdapter;
    private DetailFragment detailFragment;
    private ExchangeFragment exchangeFragment;
    private GraphFragment graphFragment;
    private SignalFragment signalFragment;
    private TabLayout tabLayout;
    private Datum datum;
    private Quote quote;
    TextView tvPrice, percentChange;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        datum = getIntent().getParcelableExtra("datum");
        quote = getIntent().getParcelableExtra("quote");
        Log.d(TAG, "datum = " + datum);
        Log.d(TAG, "quote = " + datum.getQuote());
        Log.d(TAG, "usd = " + datum.getQuote().getUSD());

        initUI();
        initViewPager();

    }


    private void initUI() {

//        USD usd = datum.getQuote().getUSD();
//        String percentChange = usd.getPercentChange1h().toString()
//                .concat("\n").concat(usd.getPercentChange24h().toString())
//                .concat("\n").concat(usd.getPercentChange7d().toString());


    }

    private void initViewPager() {

        detailFragment = DetailFragment.newInstance();
        exchangeFragment = ExchangeFragment.newInstance();
        graphFragment = GraphFragment.newInstance();
        signalFragment = SignalFragment.newInstance();

        tvPrice = findViewById(R.id.price);
        tvPrice.setText(getIntent().getExtras().getString("price"));
        percentChange = findViewById(R.id.hr1);
        percentChange.setText(getIntent().getExtras().getString("percentChange"));


        pagerAdapter = new SlideFragmentPagerAdapter(getSupportFragmentManager());
        pagerAdapter.addFragment(detailFragment, "Detail");
        pagerAdapter.addFragment(exchangeFragment, "Exchange");
        pagerAdapter.addFragment(graphFragment, "Graph");
        pagerAdapter.addFragment(signalFragment, "Signal");

        pager = (ViewPager) findViewById(R.id.pager);
        pager.setAdapter(pagerAdapter);
        pager.setOffscreenPageLimit(2);

        tabLayout = findViewById(R.id.tabLayout);
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
