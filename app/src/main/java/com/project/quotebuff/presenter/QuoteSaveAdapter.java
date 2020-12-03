package com.project.quotebuff.presenter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.project.quotebuff.R;
import com.project.quotebuff.model.Quote;
import com.project.quotebuff.model.QuoteSaveTable;

import java.util.ArrayList;

public class QuoteSaveAdapter extends ArrayAdapter<Quote> {
    final int layout;
    private final QuoteSaveTable quoteSaveTable;
    final ArrayList<Quote> quoteList;

    public QuoteSaveAdapter(@NonNull Context context, int layout, QuoteSaveTable quoteSaveTable) {
        super(context, 0, quoteSaveTable.getAllQuotes());
        quoteList = quoteSaveTable.getAllQuotes();
        this.quoteSaveTable = quoteSaveTable;
        this.layout = layout;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        Quote quote = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(this.layout, parent, false);
        }
        // Lookup view for data population
        TextView quote_author = convertView.findViewById(R.id.quote_author);
        TextView quote_content = convertView.findViewById(R.id.quote_content);
        // Populate the data into the template view using the data object
        quote_author.setText(quote.getAuthor());
        quote_content.setText(quote.getContent());
        Button delete_btn = convertView.findViewById(R.id.delete_quote_btn);
        delete_btn.setTag("btn" + position);
        delete_btn.setOnClickListener(v -> deleteQuote(position));

        // Return the completed view to render on screen
        return convertView;
    }

    public void deleteQuote(int position) {
        Quote quote = getItem(position);
        quoteSaveTable.deleteQuote(quote);
        clear();
        addAll(quoteSaveTable.getAllQuotes());
    }
}

