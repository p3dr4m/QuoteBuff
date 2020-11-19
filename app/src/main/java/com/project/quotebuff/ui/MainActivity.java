package com.project.quotebuff.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.project.quotebuff.R;
import com.project.quotebuff.model.Quote;
import com.project.quotebuff.model.QuoteSaveTable;
import com.project.quotebuff.model.QuoteTable;
import com.project.quotebuff.presenter.QuoteAdapter;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ArrayList<Quote> quoteList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        QuoteAdapter adapter = new QuoteAdapter(
                this,
                R.layout.item_quote,
                new QuoteSaveTable(getApplicationContext()),
                new QuoteTable(getApplicationContext()));
        final ListView listView = findViewById(R.id.quotes_list);
        listView.setAdapter(adapter);





        BottomNavigationView bottomNavigationView = findViewById(R.id.nav_view);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.navigation_saved:
                        Intent intent = new Intent(MainActivity.this, SavedQuotes.class);
                        startActivity(intent);
                        break;
                    case R.id.navigation_home:
                        break;
                }
                return false;
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

    }

}