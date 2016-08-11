package de.hhu.a07saveinstance;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.Locale;

public class SlaveActivity extends AppCompatActivity {
    private static final String TAG = "SlaveActivity";
    private static final String NUMBER_KEY = "TheNumber";
    private TextView mNumberView;
    private int mNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG, "Created.");

        setContentView(R.layout.activity_slave);
        mNumberView = (TextView) findViewById(R.id.numberView);

        // Reload the number!
        if (savedInstanceState != null) {
            mNumber = savedInstanceState.getInt(NUMBER_KEY);
            Log.i(TAG, "Restored instance state!");
        } else {
            mNumber = -1;
        }
        refresh();
    }

    private void refresh() {
        mNumberView.setText(String.format(Locale.getDefault(), "%d", mNumber));
    }

    // Close the activity on button click
    public void finishClick(View sender) {
        finish();
    }

    // + pressed
    public void plusClick(View sender) {
        mNumber++;
        refresh();
    }

    // - pressed
    public void minusClick(View sender) {
        mNumber--;
        refresh();
    }

    // Save the number!
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(NUMBER_KEY, mNumber);
    }

    //===== Activity Event Loggers ======
    @Override
    protected void onStart() {
        super.onStart();
        Log.i(TAG, "Started.");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(TAG, "Stopped.");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(TAG, "Paused.");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG, "Resumed.");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "Destroyed.");
    }
}
