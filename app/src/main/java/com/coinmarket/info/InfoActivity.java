package com.coinmarket.info;

import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.coinmarket.R;

public class InfoActivity extends AppCompatActivity {
    static final String TAG = "LOG  ";

    private ViewPager pager;
    private PagerAdapter pagerAdapter;

    TextView tvPrice, percentChange;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        tvPrice = findViewById(R.id.price);
        tvPrice.setText(getIntent().getExtras().getString("price"));
        percentChange = findViewById(R.id.hr1);
        percentChange.setText(getIntent().getExtras().getString("percentChange"));


        pager = findViewById(R.id.pager);

        pagerAdapter = new SlideFragmentPagerAdapter(getSupportFragmentManager());
        pager.setAdapter(pagerAdapter);


        pager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                Log.d(TAG, "onPageSelected, position = " + position);
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
}
