package de.hhu.strictmode;

import android.app.Application;
import android.os.Build;
import android.os.Handler;
import android.os.StrictMode;
import android.util.Log;

/**
 * @author Andre Ippisch
 */
public class StrictModeApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(StrictModeApplication.class.getSimpleName(), "onCreate");

        if (BuildConfig.DEBUG) {
            // only if we are in debug mode, never use strict mode in published application
            enableStrictMode();
        }
    }

    private void enableStrictMode() {
        if (Build.VERSION.SDK_INT >= 9) {
            // strict mode is available from API 9
            strictModeConfigurations();
        }

        if (Build.VERSION.SDK_INT >= 16) {
            // from API 16 on the strict mode must be enabled like this when used in Application class
            new Handler().postAtFrontOfQueue(new Runnable() {
                @Override
                public void run() {
                    strictModeConfigurations();
                }
            });
        }
    }

    private void strictModeConfigurations() {
        Log.w(StrictModeApplication.class.getSimpleName(), "strictModeConfigurations");
        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
                .detectAll()
                .penaltyLog()
                .penaltyDialog()
                .build()
        );
        StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder()
                .detectAll()
                .penaltyLog()
                .build()
        );
    }
}
