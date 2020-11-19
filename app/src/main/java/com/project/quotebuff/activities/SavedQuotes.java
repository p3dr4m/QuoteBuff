package com.project.quotebuff.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ListView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.project.quotebuff.DBHelper;
import com.project.quotebuff.Quotes;
import com.project.quotebuff.QuotesListAdapter;
import com.project.quotebuff.R;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class SavedQuotes extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saved_quotes);

        DBHelper db = new DBHelper(this);
        ArrayList<Quotes> quotesList =  db.getAllQuotes();

        final ListView listView = findViewById(R.id.quotes_list);
        QuotesListAdapter adapter = new QuotesListAdapter(this, quotesList, R.layout.item_saved_quote);
        listView.setAdapter(adapter);


        BottomNavigationView bottomNavigationView = findViewById(R.id.nav_view);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.navigation_home:
                        Intent intent = new Intent(SavedQuotes.this, MainActivity.class);
                        startActivity(intent);
                        break;
                    case R.id.navigation_saved:
                        break;
                }
                return false;
            }
        });
    }

}