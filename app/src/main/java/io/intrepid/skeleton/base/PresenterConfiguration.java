package io.intrepid.skeleton.base;

import android.support.annotation.NonNull;

import io.intrepid.skeleton.logging.CrashReporter;
import io.intrepid.skeleton.rest.RestApi;
import io.intrepid.skeleton.settings.UserSettings;
import io.reactivex.Scheduler;

/**
 * Wrapper class for common dependencies that all presenters are expected to have
 */
public class PresenterConfiguration {
    @NonNull
    private final Scheduler ioScheduler;
    @NonNull
    private final Scheduler uiScheduler;
    @NonNull
    private final UserSettings userSettings;
    @NonNull
    private final RestApi restApi;
    @NonNull
    private final CrashReporter crashReporter;


    public PresenterConfiguration(@NonNull Scheduler ioScheduler,
                                  @NonNull Scheduler uiScheduler,
                                  @NonNull UserSettings userSettings,
                                  @NonNull RestApi restApi,
                                  @NonNull CrashReporter crashReporter) {
        this.ioScheduler = ioScheduler;
        this.uiScheduler = uiScheduler;
        this.userSettings = userSettings;
        this.restApi = restApi;
        this.crashReporter = crashReporter;
    }

    @NonNull
    public Scheduler getIoScheduler() {
        return ioScheduler;
    }

    @NonNull
    public Scheduler getUiScheduler() {
        return uiScheduler;
    }

    @NonNull
    public UserSettings getUserSettings() {
        return userSettings;
    }

    @NonNull
    public RestApi getRestApi() {
        return restApi;
    }

    @NonNull
    public CrashReporter getCrashReporter() {
        return crashReporter;
    }
}
