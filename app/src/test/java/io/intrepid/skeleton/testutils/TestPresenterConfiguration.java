package io.intrepid.skeleton.testutils;

import android.support.annotation.NonNull;

import org.mockito.Mockito;

import java.util.concurrent.TimeUnit;

import io.intrepid.skeleton.base.PresenterConfiguration;
import io.intrepid.skeleton.rest.RestApi;
import io.intrepid.skeleton.settings.UserSettings;
import rx.schedulers.TestScheduler;

@SuppressWarnings("WeakerAccess")
public class TestPresenterConfiguration extends PresenterConfiguration {
    public static TestPresenterConfiguration createTestConfiguration() {
        RestApi mockApi = Mockito.mock(RestApi.class);
        UserSettings mockSettings = Mockito.mock(UserSettings.class);
        return new TestPresenterConfiguration(
                mockSettings,
                mockApi
        );
    }

    public TestPresenterConfiguration(@NonNull UserSettings userSettings,
                                      @NonNull RestApi restApi) {
        super(new TestScheduler(), new TestScheduler(), userSettings, restApi);
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
    public void advanceRxSchedulers(long delayTime, TimeUnit unit) {
        getIoScheduler().advanceTimeBy(delayTime, unit);
        getUiScheduler().triggerActions();
    }
}
