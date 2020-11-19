package com.project.quotebuff.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.project.quotebuff.DBHelper;
import com.project.quotebuff.Quotes;
import com.project.quotebuff.QuotesListAdapter;
import com.project.quotebuff.R;
import com.project.quotebuff.Utils;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ArrayList<Quotes> quotesList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String jsonFileString = Utils.getJsonFromAssets(getApplicationContext(), "quotes.json");
        Log.i("data", jsonFileString);

        Gson gson = new Gson();
        Type listQuotesType = new TypeToken<ArrayList<Quotes>>() {
        }.getType();
        ArrayList<Quotes> quotesList = gson.fromJson(jsonFileString, listQuotesType);
        for (int i = 0; i < quotesList.size(); i++) {
            Log.i("data", "> Item " + i + "\n" + quotesList.get(i));
        }
        final ListView listView = findViewById(R.id.quotes_list);
        QuotesListAdapter adapter = new QuotesListAdapter(this, quotesList, R.layout.item_quote);
        listView.setAdapter(adapter);
        DBHelper db = new DBHelper(this);
        db.getReadableDatabase();
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


}