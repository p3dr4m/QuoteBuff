package com.project.quotebuff.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper implements IQuoteSaveTable, IQuoteTable {
    private static final int DATABASE_VERSION = 2;
    private static final String DATABASE_NAME = "QuoteBuffDB";
    private final Context context;


    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_QUOTE_SAVE_TABLE = String.format(
                "CREATE TABLE %1s ( %2s TEXT PRIMARY KEY, %3s TEXT, %4s TEXT)",
                TABLE_QUOTE_SAVE,
                IQuoteSaveTable.KEY_ID,
                IQuoteSaveTable.KEY_AUTHOR,
                IQuoteSaveTable.KEY_CONTENT);
        String CREATE_QUOTE_TABLE = String.format(
                "CREATE TABLE %1s ( %2s TEXT PRIMARY KEY, %3s TEXT, %4s TEXT)",
                TABLE_QUOTE,
                IQuoteTable.KEY_ID,
                IQuoteTable.KEY_AUTHOR,
                IQuoteTable.KEY_CONTENT);
        db.execSQL(CREATE_QUOTE_SAVE_TABLE);
        db.execSQL(CREATE_QUOTE_TABLE);
        fillDatabaseWithData(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_QUOTE_SAVE);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_QUOTE);
        onCreate(db);
    }

    private void fillDatabaseWithData(SQLiteDatabase db) {
        String jsonFileString = Utils.getJsonFromAssets(context.getApplicationContext(), "quotes.json");
        Gson gson = new Gson();
        Type listQuotesType = new TypeToken<ArrayList<Quote>>() {
        }.getType();

        ArrayList<Quote> quoteList = gson.fromJson(jsonFileString, listQuotesType);
        assert quoteList != null;

        ContentValues values = new ContentValues();
        quoteList.forEach(quote -> {
            values.put(IQuoteTable.KEY_ID, quote.get_id());
            values.put(IQuoteTable.KEY_AUTHOR, quote.getAuthor());
            values.put(IQuoteTable.KEY_CONTENT, quote.getContent());
            db.insert(TABLE_QUOTE, null, values);
        });
    }
}
