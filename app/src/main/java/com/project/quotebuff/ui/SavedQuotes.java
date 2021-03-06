package com.project.quotebuff.ui;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.project.quotebuff.R;
import com.project.quotebuff.model.QuoteSaveTable;
import com.project.quotebuff.presenter.QuoteSaveAdapter;

public class SavedQuotes extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saved_quotes);
        final ListView listView = findViewById(R.id.quotes_list);
        QuoteSaveAdapter adapter = new QuoteSaveAdapter(
                this,
                R.layout.item_saved_quote,
                new QuoteSaveTable(getApplicationContext()));
        listView.setAdapter(adapter);

        BottomNavigationView bottomNavigationView = findViewById(R.id.nav_view);
        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            if (item.getItemId() == R.id.navigation_saved) {
                return false;
            } else if (item.getItemId() == R.id.navigation_home) {
                Intent intent = new Intent(SavedQuotes.this, MainActivity.class);
                startActivity(intent);
            }
            return false;
        });
    }

}