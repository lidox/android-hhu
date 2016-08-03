package de.hhu.strictmode;

import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    SharedPreferences mSharedPreferences = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(MainActivity.class.getSimpleName(), "onCreate");
        setContentView(R.layout.activity_main);

        //doInBackground();
        doInMainThread();
    }

    private void doInBackground() {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... params) {
                mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);
                mSharedPreferences.contains("dummy"); // dummy call to load shared preferences xml
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                onSharedPreferencesAvailable();
            }
        }.execute();
    }

    private void doInMainThread() {
        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);
        onSharedPreferencesAvailable();
    }

    private void onSharedPreferencesAvailable() {
        Log.d(MainActivity.class.getSimpleName(), "onSharedPreferencesAvailable");

        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putString("key", "value");
        editor.apply();

        String whatIsMyValue = mSharedPreferences.getString("key", "no value");
        TextView textView = (TextView) findViewById(R.id.theTextView);
        if (textView != null) {
            textView.setText(whatIsMyValue);
        }
    }
}
