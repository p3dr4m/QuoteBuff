package com.project.quotebuff.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class QuoteSaveTable implements IQuoteSaveTable {
    Context context;
    DBHelper dbHelper;
    String[] allColumns = {KEY_ID, KEY_AUTHOR, KEY_CONTENT};

    public QuoteSaveTable(Context context) {
        this.context = context;
        dbHelper = new DBHelper(context);

    }

    public void deleteQuote(Quote quote) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.delete(TABLE_QUOTE_SAVE, KEY_ID + "=?", new String[]{quote.get_id()});
        db.close();
    }

    public boolean getQuote(String id) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.query(
                TABLE_QUOTE_SAVE,
                allColumns,
                KEY_ID + "=?",
                new String[]{String.valueOf(id)}, null, null, null, null);
        if (cursor != null)
            return true;
        else
            return false;
    }

    public void addQuote(Quote quote) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_ID, quote.get_id());
        values.put(KEY_AUTHOR, quote.getAuthor());
        values.put(KEY_CONTENT, quote.getContent());

        db.insert(TABLE_QUOTE_SAVE, null, values);
        db.close();
    }

    public ArrayList<Quote> getAllQuotes() {
        ArrayList<Quote> quoteList = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String selectQuery = "SELECT * FROM " + TABLE_QUOTE_SAVE;
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
