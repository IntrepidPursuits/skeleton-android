package io.intrepid.skeleton.testutils;

import android.support.annotation.NonNull;

import io.intrepid.skeleton.base.PresenterConfiguration;
import io.intrepid.skeleton.rest.RestApi;
import io.intrepid.skeleton.settings.UserSettings;
import rx.schedulers.TestScheduler;

public class TestPresenterConfiguration extends PresenterConfiguration {
    public TestPresenterConfiguration(@NonNull TestScheduler ioScheduler,
                                      @NonNull TestScheduler uiScheduler,
                                      @NonNull UserSettings userSettings,
                                      @NonNull RestApi restApi) {
        super(ioScheduler, uiScheduler, userSettings, restApi);
    }

    public TestScheduler getTestIoScheduler() {
        return (TestScheduler) getIoScheduler();
    }

    public TestScheduler getTestUiScheduler() {
        return (TestScheduler) getUiScheduler();
    }
}
