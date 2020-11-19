package com.project.quotebuff.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.project.quotebuff.model.Quotes;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "QuoteBuffDB";
    private static final String TABLE_QUOTE_SAVE = "quote_save";
    private static final String KEY_ID = "id";
    private static final String KEY_AUTHOR = "author";
    private static final String KEY_CONTENT = "content";


    public DBHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_QUOTE_SAVE_TABLE = String.format("CREATE TABLE %1s ( %2s TEXT PRIMARY KEY, %3s TEXT, %4s TEXT)", TABLE_QUOTE_SAVE, KEY_ID, KEY_AUTHOR, KEY_CONTENT);
        db.execSQL(CREATE_QUOTE_SAVE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_QUOTE_SAVE);
        onCreate(db);
    }

    public void deleteQuote(Quotes quote) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_QUOTE_SAVE, KEY_ID + "=?", new String[]{quote.get_id()});
        db.close();
    }

    public boolean getQuote(String id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_QUOTE_SAVE, new String[]{KEY_ID, KEY_AUTHOR, KEY_CONTENT},
                KEY_ID + "=?",
                new String[]{String.valueOf(id)}, null, null, null, null);
        if (cursor != null)
            return true;
        else
            return false;
    }

    public void addQuote(Quotes quote) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_ID, quote.get_id());
        values.put(KEY_AUTHOR, quote.getAuthor());
        values.put(KEY_CONTENT, quote.getContent());

        db.insert(TABLE_QUOTE_SAVE, null, values);
        db.close();
    }

    public ArrayList<Quotes> getAllQuotes() {
        ArrayList<Quotes> quotesList = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        String selectQuery = "SELECT * FROM " + TABLE_QUOTE_SAVE;
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                Quotes quote = new Quotes();
                quote.set_id(cursor.getString(0));
                quote.setAuthor(cursor.getString(1));
                quote.setContent(cursor.getString(2));
                // Adding contact to list
                quotesList.add(quote);
            } while (cursor.moveToNext());
        }
        return quotesList;
    }
}
