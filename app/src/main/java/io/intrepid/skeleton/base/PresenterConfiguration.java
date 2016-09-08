package io.intrepid.skeleton.base;

import android.support.annotation.NonNull;

import io.intrepid.skeleton.settings.UserSettings;
import rx.Scheduler;

public class PresenterConfiguration {
    @NonNull
    private final Scheduler ioScheduler;
    @NonNull
    private final Scheduler uiScheduler;
    @NonNull
    private final UserSettings userSettings;

    public PresenterConfiguration(@NonNull Scheduler ioScheduler,
                                  @NonNull Scheduler uiScheduler,
                                  @NonNull UserSettings userSettings) {
        this.ioScheduler = ioScheduler;
        this.uiScheduler = uiScheduler;
        this.userSettings = userSettings;
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
}
