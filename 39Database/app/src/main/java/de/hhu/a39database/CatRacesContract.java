package de.hhu.a39database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

public class CatRacesContract {
    // Make this private so no one can instantiate this by accident
    private CatRacesContract() {
    }

    // Our column name definitions
    public static abstract class CatRace implements BaseColumns {
        public static final String TABLE_NAME = "cat_race";
        public static final String COLUMN_NAME_COMMON_NAME = "common_name";
        public static final String COLUMN_NAME_SCIENTIFIC_NAME = "scientific_name";
        public static final String COLUMN_NAME_CUTENESS = "cuteness";
    }

    // Useful SQL query parts
    private static final String TEXT_TYPE = " TEXT";
    private static final String INTEGER_TYPE = " INTEGER";
    private static final String COMMA_SEP = ",";
    // Useful SQL queries
    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + CatRace.TABLE_NAME + " (" +
                    CatRace._ID + INTEGER_TYPE + " PRIMARY KEY" + COMMA_SEP +
                    CatRace.COLUMN_NAME_COMMON_NAME + TEXT_TYPE + COMMA_SEP +
                    CatRace.COLUMN_NAME_SCIENTIFIC_NAME + TEXT_TYPE + COMMA_SEP +
                    CatRace.COLUMN_NAME_CUTENESS + INTEGER_TYPE + " )";
    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + CatRace.TABLE_NAME;

    // A helper class for accessing the database
    public static class CatRacesDbHelper extends SQLiteOpenHelper {
        // The expected database version. Everytime you change the schema,
        // you should increment this number and implement onUpgrade.
        public static final int DATABASE_VERSION = 1;
        // Just the file name
        public static final String DATABASE_NAME = "catraces.db";

        public CatRacesDbHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            // Create all the tables (in this case we only have one)
            db.execSQL(SQL_CREATE_ENTRIES);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            // There are no older versions than 1 yet.
            // Whenever you design a newer version, make sure to add some migration here.
        }

        // Fetch all cat races
        public Cursor getAllCatRaces() {
            SQLiteDatabase db = getReadableDatabase();

            // The columns to return
            String[] projection = {
                    CatRace._ID,
                    CatRace.COLUMN_NAME_COMMON_NAME,
                    CatRace.COLUMN_NAME_SCIENTIFIC_NAME,
                    CatRace.COLUMN_NAME_CUTENESS
            };

            // The sort order (ascending by scientific name)
            String sortOrder = CatRace.COLUMN_NAME_CUTENESS + " ASC";

            // Do the query!
            return db.query(CatRace.TABLE_NAME, // table name
                    projection,                 // columns to return
                    null,                       // columns for WHERE
                    null,                       // values for WHERE
                    null,                       // groups
                    null,                       // filters
                    sortOrder);                 // sort order
        }

        // Insert a cat race into the database
        public void addCatRace(String commonName, String scientificName, int cuteness) {
            SQLiteDatabase db = getWritableDatabase();

            // Fill the ContentValues
            ContentValues values = new ContentValues();
            values.put(CatRace.COLUMN_NAME_COMMON_NAME, commonName);
            values.put(CatRace.COLUMN_NAME_SCIENTIFIC_NAME, scientificName);
            values.put(CatRace.COLUMN_NAME_CUTENESS, cuteness);

            // And ... action!
            db.insert(CatRace.TABLE_NAME, null, values);
        }

        // Delete a cat race
        public void deleteCatRace(long id) {
            SQLiteDatabase db = getWritableDatabase();
            db.delete(CatRace.TABLE_NAME, CatRace._ID + "=?", new String[]{Long.toString(id)});
        }

        // Increase cuteness
        public void incCuteness(long id) {
            SQLiteDatabase db = getWritableDatabase();

            // Build SQL Query
            String sql = "UPDATE " + CatRace.TABLE_NAME + " SET "
                    + CatRace.COLUMN_NAME_CUTENESS + " = "
                    + CatRace.COLUMN_NAME_CUTENESS + " + 1 "
                    + " WHERE " + CatRace._ID + " = ?";
            // Execute query
            db.execSQL(sql, new Object[]{id});

            // There is also db.update(), but unfortunately this doesn't support
            // constructs like this one ("cuteness = cuteness + 1")
        }

        // Delete all t3h c4tz!
        public void clearDatabase() {
            SQLiteDatabase db = getWritableDatabase();
            db.execSQL(SQL_DELETE_ENTRIES);
            db.execSQL(SQL_CREATE_ENTRIES);
        }
    }
}
