package de.hhu.a09applicationtest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.RelativeLayout;

public class MainActivity extends AppCompatActivity {
    private RelativeLayout mTopmostLayout;
    private int mGravityIndex = 0;
    private static final int[] GRAVITIES = new int[]{
            Gravity.CENTER,
            Gravity.TOP | Gravity.START,
            Gravity.TOP | Gravity.END,
            Gravity.BOTTOM | Gravity.CENTER
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTopmostLayout = (RelativeLayout) findViewById(R.id.topmost_layout);
    }

    public void jumpingButtonClick(View sender) {
        mGravityIndex = (mGravityIndex + 1) % GRAVITIES.length;
        mTopmostLayout.setGravity(GRAVITIES[mGravityIndex]);
    }
}
