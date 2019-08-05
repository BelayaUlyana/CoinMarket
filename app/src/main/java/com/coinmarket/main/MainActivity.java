package com.coinmarket.main;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.coinmarket.R;
import com.coinmarket.info.InfoActivity;
import com.coinmarket.listPOJO.Datum;
import com.coinmarket.listPOJO.NameValuePair;
import com.coinmarket.listPOJO.Quote;

public class MainActivity extends AppCompatActivity implements MainContract, NavigationView.OnNavigationItemSelectedListener {

    private MainAdapter adapter;
    ProgressDialog progressDialog;
    Toolbar toolbar;
    DrawerLayout drawer;

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
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(R.string.activity_main_title);

        openDrawer();

        MainPresenter presenter = new MainPresenter(this);
        presenter.getDatum();

        RecyclerView recyclerView = findViewById(R.id.customRecyclerView);
        adapter = new MainAdapter(this, new MainAdapter.ItemClickListener() {
            @Override
            public void click(int pos, Datum datum, Quote quote) {
                Intent intent = new Intent(MainActivity.this, InfoActivity.class);
                intent.putExtra("datum", datum);
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


    void openDrawer() {
        drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_toolbar, menu);
        return true;
    }


//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        if (item.getItemId() == android.R.id.action_settings) {
//            drawer.openDrawer(GravityCompat.START);
//            return true;
//        }
//        return super.onOptionsItemSelected(item);
//    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.favourites) {
            Toast.makeText(this, "favourites", Toast.LENGTH_LONG);
        } else if (id == R.id.list) {
            Toast.makeText(this, "list", Toast.LENGTH_LONG);
        } else if (id == R.id.notifications) {
            Toast.makeText(this, "notifications", Toast.LENGTH_LONG);
        } else if (id == R.id.news) {
            Toast.makeText(this, "news", Toast.LENGTH_LONG);
        } else if (id == R.id.remove_ads) {
            Toast.makeText(this, "remove_ads", Toast.LENGTH_LONG);
        } else if (id == R.id.settings) {
            Toast.makeText(this, "settings", Toast.LENGTH_LONG);
        } else if (id == R.id.about_us) {
            Toast.makeText(this, "about_us", Toast.LENGTH_LONG);
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

}
