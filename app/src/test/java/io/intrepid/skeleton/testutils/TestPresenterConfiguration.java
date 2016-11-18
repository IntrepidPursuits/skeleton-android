package io.intrepid.skeleton.testutils;

import android.support.annotation.NonNull;

import org.mockito.Mockito;

import io.intrepid.skeleton.base.PresenterConfiguration;
import io.intrepid.skeleton.logging.CrashReporter;
import io.intrepid.skeleton.rest.RestApi;
import io.intrepid.skeleton.settings.UserSettings;
import rx.schedulers.TestScheduler;

@SuppressWarnings("WeakerAccess")
public class TestPresenterConfiguration extends PresenterConfiguration {
    public static TestPresenterConfiguration createTestConfiguration() {
        RestApi mockApi = Mockito.mock(RestApi.class);
        UserSettings mockSettings = Mockito.mock(UserSettings.class);
        CrashReporter crashReporter = Mockito.mock(CrashReporter.class);
        return new TestPresenterConfiguration(
                mockSettings,
                mockApi,
                crashReporter
        );
    }

    public TestPresenterConfiguration(@NonNull UserSettings userSettings,
                                      @NonNull RestApi restApi,
                                      @NonNull CrashReporter crashReporter) {
        super(new TestScheduler(), new TestScheduler(), userSettings, restApi, crashReporter);
    }

    @NonNull
    @Override
    public TestScheduler getIoScheduler() {
        return (TestScheduler) super.getIoScheduler();
    }

    @NonNull
    @Override
    public TestScheduler getUiScheduler() {
        return (TestScheduler) super.getUiScheduler();
    }

    /**
     * Helper method for triggering pending Rx events
     */
    public void triggerRxSchedulers() {
        getIoScheduler().triggerActions();
        getUiScheduler().triggerActions();
    }
}
