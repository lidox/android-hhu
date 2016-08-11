package de.hhu.a07saveinstance;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.*;

public class CtrlActivity extends AppCompatActivity {
    final String TAG = "CtrlActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ctrl);
    }

    // Start the slave activity
    public void startSlave(View sender) {
        startActivity(new Intent(this, SlaveActivity.class));
    }

    // Close activity when user clicks "Quit"
    public void quitClick(View sender) {
        finish();
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
