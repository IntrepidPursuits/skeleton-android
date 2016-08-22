package io.intrepid.skeleton.logging;

import android.support.annotation.NonNull;
import android.util.Log;

import io.intrepid.skeleton.BuildConfig;
import timber.log.Timber;

public class TimberConfig {
    @SuppressWarnings({ "ConstantConditions", "UnusedAssignment" })
    public static void init(@NonNull final CrashReporter crashReporter) {
        Timber.Tree tree = null;
        if (BuildConfig.LOG_CONSOLE) {
            if (BuildConfig.REPORT_CRASH) {
                tree = new Timber.DebugTree() {
                    @Override
                    protected void log(int priority, String tag, String message, Throwable t) {
                        super.log(priority, tag, message, t);
                        if (priority >= Log.INFO) {
                            crashReporter.log(priority, tag, message);
                        }
                    }
                };
            } else {
                tree = new Timber.DebugTree();
            }
        } else if (BuildConfig.REPORT_CRASH) {
            tree = new Timber.Tree() {
                @Override
                protected void log(int priority, String tag, String message, Throwable t) {
                    if (priority >= Log.INFO) {
                        crashReporter.log(priority, tag, message);
                    }
                }
            };
        }
        if (tree != null) {
            Timber.plant(tree);
        }
    }
}
