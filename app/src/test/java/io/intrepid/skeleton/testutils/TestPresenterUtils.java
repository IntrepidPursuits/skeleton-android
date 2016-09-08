package io.intrepid.skeleton.testutils;

import org.mockito.Mockito;

import io.intrepid.skeleton.base.PresenterConfiguration;
import io.intrepid.skeleton.settings.UserSettings;
import rx.schedulers.Schedulers;

public class TestPresenterUtils {
    public static PresenterConfiguration createTestConfiguration() {
        UserSettings mockSettings = Mockito.mock(UserSettings.class);
        return new PresenterConfiguration(
                Schedulers.immediate(),
                Schedulers.immediate(),
                mockSettings
        );
    }
}
