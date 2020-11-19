package com.project.quotebuff.model;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class QuoteTable implements IQuoteTable {
    Context context;
    DBHelper dbHelper;
    String[] allColumns = {KEY_ID, KEY_AUTHOR, KEY_CONTENT};

    public QuoteTable(Context context) {
        this.context = context;
        dbHelper = new DBHelper(context);
    }


    public ArrayList<Quote> getAllQuotes() {
        ArrayList<Quote> quoteList = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String selectQuery = "SELECT * FROM " + TABLE_QUOTE;
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                Quote quote = new Quote();
                quote.set_id(cursor.getString(0));
                quote.setAuthor(cursor.getString(1));
                quote.setContent(cursor.getString(2));
                // Adding contact to list
                quoteList.add(quote);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return quoteList;
    }
}
