package io.intrepid.skeleton.testutils;

import org.mockito.Mockito;

import io.intrepid.skeleton.rest.RestApi;
import io.intrepid.skeleton.settings.UserSettings;
import rx.schedulers.TestScheduler;

public class TestPresenterUtils {
    public static TestPresenterConfiguration createTestConfiguration() {
        RestApi mockApi = Mockito.mock(RestApi.class);
        return createTestConfiguration(mockApi);
    }

    public static TestPresenterConfiguration createTestConfiguration(RestApi restApi) {
        UserSettings mockSettings = Mockito.mock(UserSettings.class);
        return new TestPresenterConfiguration(
                new TestScheduler(),
                new TestScheduler(),
                mockSettings,
                restApi
        );
    }
}
