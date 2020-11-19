package com.project.quotebuff;

import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

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
        QuotesListAdapter adapter = new QuotesListAdapter(this, quotesList);
        listView.setAdapter(adapter);
        DBHelper db = new DBHelper(this);
        db.getReadableDatabase();

    }


}