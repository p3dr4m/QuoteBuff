package com.project.quotebuff;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.ArrayList;

public class QuotesListAdapter extends ArrayAdapter<Quotes> {
    public QuotesListAdapter(@NonNull Context context, ArrayList<Quotes> quotes) {
        super(context, 0, quotes);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        Quotes quotes = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_quote, parent, false);
        }
        // Lookup view for data population
        TextView quote_author = (TextView) convertView.findViewById(R.id.quote_author);
        TextView quote_content = (TextView) convertView.findViewById(R.id.quote_content);
        // Populate the data into the template view using the data object
        quote_author.setText(quotes.author);
        quote_content.setText(quotes.content);
        // Return the completed view to render on screen
        return convertView;
    }
}

