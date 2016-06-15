package de.hhu.a39database;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

public class CatRacesAdapter extends CursorAdapter {
    // Constructor...
    public CatRacesAdapter(Context context, Cursor cursor) {
        super(context, cursor, 0);
    }

    // Create an item view by inflating the layout
    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.cat_race_item, parent, false);
    }

    // This is called for every item
    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        // Find the views
        TextView commonNameLabel = (TextView) view.findViewById(R.id.common_name_label);
        TextView scientificNameLabel = (TextView) view.findViewById(R.id.scientific_name_label);

        // Find the columns
        int cutenessColumn = cursor.getColumnIndexOrThrow(CatRacesContract.CatRace.COLUMN_NAME_CUTENESS);
        int commonNameColumn = cursor.getColumnIndexOrThrow(CatRacesContract.CatRace.COLUMN_NAME_COMMON_NAME);
        int scientificNameColumn = cursor.getColumnIndexOrThrow(CatRacesContract.CatRace.COLUMN_NAME_SCIENTIFIC_NAME);

        // Lookup the values
        int cuteness = cursor.getInt(cutenessColumn);
        String commonName = cursor.getString(commonNameColumn);
        String scientificName = cursor.getString(scientificNameColumn);

        // Bind the values to the views
        commonNameLabel.setText(context.getString(R.string.common_name_format, commonName, cuteness));
        scientificNameLabel.setText(scientificName);
    }
}
