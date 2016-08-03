package de.hhu.a39database;

import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {
    private ListView mCatRacesListView;
    private CatRacesAdapter mCatRacesAdapter;
    private CatRacesContract.CatRacesDbHelper mCatRacesDbHelper;
    private EditText mCommonNameEdit, mScientificNameEdit, mCutenessEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mCatRacesListView = (ListView) findViewById(R.id.cat_races_listview);
        mCommonNameEdit = (EditText) findViewById(R.id.common_name_edit);
        mScientificNameEdit = (EditText) findViewById(R.id.scientific_name_edit);
        mCutenessEdit = (EditText) findViewById(R.id.cuteness_edit);
        mCatRacesDbHelper = new CatRacesContract.CatRacesDbHelper(this);

        // Register the ListView for a ContextMenu
        registerForContextMenu(mCatRacesListView);

        // Create the adapter
        mCatRacesAdapter = new CatRacesAdapter(this, null);
        mCatRacesListView.setAdapter(mCatRacesAdapter);

        // Database access should be done asynchronously
        new AsyncTask<Void, Void, Cursor>() {
            @Override
            protected Cursor doInBackground(Void... params) {
                // Clear it for demonstration purposes
                // You probably don't want this in production code ;)
                mCatRacesDbHelper.clearDatabase();

                // Fetch all t3h c4tz (none are available, as we just cleared the database)
                Cursor cursor;
                cursor = mCatRacesDbHelper.getAllCatRaces();
                cursor.moveToFirst(); // to initialize the cursor in background thread (strict mode)
                return cursor;
            }

            @Override
            protected void onPostExecute(Cursor cursor) {
                // Update the ListView
                mCatRacesAdapter.changeCursor(cursor);
                // add some cats now
                addSomeRaces();
            }
        }.execute();
    }

    private void addSomeRaces() {
        // Create the CatRaceAdderTask
        new CatRaceAdderTask() {
            // Get the DB Helper
            @Override
            public CatRacesContract.CatRacesDbHelper getDbHelper() {
                return mCatRacesDbHelper;
            }

            // Update the ListView
            @Override
            public void onProgressUpdate(Cursor... progress) {
                mCatRacesAdapter.changeCursor(progress[0]);
            }
        }.execute();
    }

    public void addRace(View sender) {
        // Read and clear the EditTexts
        final String commonName = mCommonNameEdit.getText().toString();
        mCommonNameEdit.getText().clear();
        final String scientificName = mScientificNameEdit.getText().toString();
        mScientificNameEdit.getText().clear();
        final int cuteness = Integer.parseInt(mCutenessEdit.getText().toString());

        // Database access should be done asynchronously
        new AsyncTask<Void, Void, Cursor>() {
            @Override
            protected Cursor doInBackground(Void... params) {
                // Actually add the item
                mCatRacesDbHelper.addCatRace(commonName, scientificName, cuteness);
                return mCatRacesDbHelper.getAllCatRaces();
            }

            @Override
            protected void onPostExecute(Cursor result) {
                // Refresh the ListView
                mCatRacesAdapter.changeCursor(result);
            }
        }.execute();
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View sender, ContextMenu.ContextMenuInfo info) {
        super.onCreateContextMenu(menu, sender, info);
        getMenuInflater().inflate(R.menu.context_menu, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        final AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();

        switch (item.getItemId()) {
            case R.id.menu_delete_cat_race:
                // Database access should be done asynchonously
                new AsyncTask<Void, Void, Cursor>() {
                    @Override
                    protected Cursor doInBackground(Void... params) {
                        // Delete item
                        mCatRacesDbHelper.deleteCatRace(info.id);
                        return mCatRacesDbHelper.getAllCatRaces();
                    }

                    @Override
                    protected void onPostExecute(Cursor result) {
                        // Refresh the ListView
                        mCatRacesAdapter.changeCursor(result);
                    }
                }.execute();
                return true;

            case R.id.menu_aww_so_cute:
                new AsyncTask<Void, Void, Cursor>() {
                    @Override
                    protected Cursor doInBackground(Void... params) {
                        // This is sooo cute :3
                        mCatRacesDbHelper.incCuteness(info.id);
                        return mCatRacesDbHelper.getAllCatRaces();
                    }

                    @Override
                    protected void onPostExecute(Cursor result) {
                        // Refresh the ListView
                        mCatRacesAdapter.changeCursor(result);
                    }
                }.execute();
                return true;

            default:
                return super.onContextItemSelected(item);
        }
    }
}
