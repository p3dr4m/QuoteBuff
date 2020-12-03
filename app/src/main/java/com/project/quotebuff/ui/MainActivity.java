package com.project.quotebuff.ui;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.project.quotebuff.R;
import com.project.quotebuff.model.QuoteSaveTable;
import com.project.quotebuff.model.QuoteTable;
import com.project.quotebuff.presenter.QuoteAdapter;

public class MainActivity extends AppCompatActivity {

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
        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            if (item.getItemId() == R.id.navigation_saved) {
                Intent intent = new Intent(MainActivity.this, SavedQuotes.class);
                startActivity(intent);
            } else if (item.getItemId() == R.id.navigation_home) {
                return false;
            }
            return false;
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

    }

}