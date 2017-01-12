package io.intrepid.getaprint.logging;

import android.content.Context;

import com.crashlytics.android.Crashlytics;
import com.crashlytics.android.core.CrashlyticsCore;

import io.fabric.sdk.android.Fabric;
import io.intrepid.getaprint.BuildConfig;

public class CrashlyticsReporter implements CrashReporter {

    private static CrashlyticsReporter instance;

    @SuppressWarnings("PointlessBooleanExpression")
    public static void init(Context context) {
        CrashlyticsCore core = new CrashlyticsCore
                .Builder()
                .disabled(!BuildConfig.REPORT_CRASH)
                .build();
        Crashlytics crashlytics = new Crashlytics
                .Builder()
                .core(core)
                .build();
        Fabric.with(context, crashlytics);
        instance = new CrashlyticsReporter();
    }

    public static CrashReporter getInstance() {
        return instance;
    }

    private CrashlyticsReporter() {
    }

    @Override
    public void logException(Throwable throwable) {
        Crashlytics.logException(throwable);
    }

    @Override
    public void log(int priority, String tag, String message) {
        Crashlytics.log(priority, tag, message);
    }
}
