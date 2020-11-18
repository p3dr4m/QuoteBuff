package com.project.quotebuff;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;
import com.project.quotebuff.Quotes;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String jsonFileString = Utils.getJsonFromAssets(getApplicationContext(), "quotes.json");
        Log.i("data", jsonFileString);

        Gson gson = new Gson();
        Type listQuotesType = new TypeToken<List<Quotes>>() {}.getType();
        List<Quotes> quotes = gson.fromJson(jsonFileString, listQuotesType);
        for (int i = 0; i < quotes.size(); i++) {
            Log.i("data", "> Item " + i + "\n" + quotes.get(i));
        }
    }
}