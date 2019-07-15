package com.coinmarket.main;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.coinmarket.R;
import com.coinmarket.info.InfoActivity;
import com.coinmarket.listPOJO.NameValuePair;

public class MainActivity extends AppCompatActivity implements MainContract {

    private MainAdapter adapter;
    private MainPresenter presenter;
    ProgressDialog progressDialog;
    TextView price, textViewHR1, textViewHR24, textViewD7;

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
        presenter = new MainPresenter(this);
        presenter.getDatum();
        RecyclerView recyclerView = findViewById(R.id.customRecyclerView);
        adapter = new MainAdapter(this);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);


        progressDialog = new ProgressDialog(MainActivity.this);
        progressDialog.setMessage("Loading....");
        progressDialog.show();

    }


    public void onMyButtonClick(View view) {


        price = view.findViewById(R.id.price);
        textViewHR1 = view.findViewById(R.id.hr1);
        textViewHR24 = view.findViewById(R.id.hr24);
        textViewD7 = view.findViewById(R.id.d7);
        String percentChange = textViewHR1.getText().toString().concat("\n").concat(textViewHR24.getText().toString()).concat("\n").concat(textViewD7.getText().toString());
        Intent intent = new Intent(MainActivity.this, InfoActivity.class);

        intent.putExtra("price", price.getText().toString().concat("\n19.01.1970"));
        intent.putExtra("percentChange", percentChange);
        startActivity(intent);
    }

}
