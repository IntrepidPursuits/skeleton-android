package io.intrepid.skeleton.base;

import android.support.annotation.NonNull;

import io.intrepid.skeleton.rest.RestApi;
import io.intrepid.skeleton.settings.UserSettings;
import rx.Scheduler;

public class PresenterConfiguration {
    @NonNull
    private final Scheduler ioScheduler;
    @NonNull
    private final Scheduler uiScheduler;
    @NonNull
    private final UserSettings userSettings;
    @NonNull
    private final RestApi restApi;

    public PresenterConfiguration(@NonNull Scheduler ioScheduler,
                                  @NonNull Scheduler uiScheduler,
                                  @NonNull UserSettings userSettings,
                                  @NonNull RestApi restApi) {
        this.ioScheduler = ioScheduler;
        this.uiScheduler = uiScheduler;
        this.userSettings = userSettings;
        this.restApi = restApi;
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
}
