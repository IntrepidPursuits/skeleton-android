package io.intrepid.skeleton.logging;

import android.util.Log;

import io.intrepid.skeleton.BuildConfig;
import timber.log.Timber;

public class TimberLogger {
    public static void init(final CrashReporter crashReporter) {
        Timber.Tree tree;
        if (BuildConfig.LOG_CONSOLE) {
            tree = new Timber.DebugTree();
        } else {
            tree = new Timber.Tree() {
                @Override
                protected void log(int priority, String tag, String message, Throwable t) {
                    if (priority >= Log.INFO) {
                        crashReporter.log(priority, tag, message);
                    }
                }
            };
        }

        Timber.plant(tree);
    }
}
