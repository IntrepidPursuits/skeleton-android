package io.intrepid.skeleton.logging;

public interface CrashReporter {

    void log(int priority, String tag, String message);

    void logException(Throwable throwable);
}
