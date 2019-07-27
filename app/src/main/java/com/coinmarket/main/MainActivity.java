package com.coinmarket.main;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.coinmarket.R;
import com.coinmarket.info.InfoActivity;
import com.coinmarket.listPOJO.Datum;
import com.coinmarket.listPOJO.NameValuePair;
import com.coinmarket.listPOJO.Quote;

import static android.support.constraint.Constraints.TAG;

public class MainActivity extends AppCompatActivity implements MainContract {

    private MainAdapter adapter;
    ProgressDialog progressDialog;

    public void showInfo(NameValuePair nameValuePair) {
        adapter.addALL(nameValuePair.getData());
    }


    @Override
    public void showError(String error) {
        progressDialog.dismiss();
        Log.e("LOG ERROR ", error);
        Toast.makeText(this, "Error:" + error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void hideProgress() {
        progressDialog.dismiss();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MainPresenter presenter = new MainPresenter(this);
        presenter.getDatum();
        RecyclerView recyclerView = findViewById(R.id.customRecyclerView);
        adapter = new MainAdapter(this, new MainAdapter.ItemClickListener() {
            @Override
            public void click(int pos, Datum datum, Quote quote) {

                Intent intent = new Intent(MainActivity.this, InfoActivity.class);
                intent.putExtra("datum", datum);
                Log.d(TAG, "MainActivity quote = " + quote);
                intent.putExtra("quote", quote);
                startActivity(intent);
            }
        });


        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);


        progressDialog = new ProgressDialog(MainActivity.this);
        progressDialog.setMessage("Loading....");
        progressDialog.show();

    }

}
