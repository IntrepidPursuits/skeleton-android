package io.intrepid.skeleton.testutils;

import org.mockito.Mockito;

import io.intrepid.skeleton.base.PresenterConfiguration;
import io.intrepid.skeleton.rest.RestApi;
import io.intrepid.skeleton.settings.UserSettings;
import rx.schedulers.Schedulers;

public class TestPresenterUtils {
    public static PresenterConfiguration createTestConfiguration() {
        RestApi mockApi = Mockito.mock(RestApi.class);
        return createTestConfiguration(mockApi);
    }

    public static PresenterConfiguration createTestConfiguration(RestApi restApi) {
        UserSettings mockSettings = Mockito.mock(UserSettings.class);
        return new PresenterConfiguration(
                Schedulers.immediate(),
                Schedulers.immediate(),
                mockSettings,
                restApi
        );
    }
}
