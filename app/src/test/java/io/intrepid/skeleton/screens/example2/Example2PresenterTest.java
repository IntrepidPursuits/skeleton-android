package io.intrepid.skeleton.screens.example2;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import io.intrepid.skeleton.models.IpModel;
import io.intrepid.skeleton.rest.RestApi;
import io.intrepid.skeleton.settings.UserSettings;
import io.intrepid.skeleton.testutils.BasePresenterTest;
import io.intrepid.skeleton.testutils.TestPresenterConfiguration;
import rx.Observable;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class Example2PresenterTest extends BasePresenterTest<Example2Presenter> {

    @Mock
    Example2Contract.View mockView;

    private TestPresenterConfiguration testConfiguration;
    private RestApi mockApi;
    private UserSettings mockSettings;

    @Before
    public void setUp() {
        testConfiguration = TestPresenterConfiguration.createTestConfiguration();
        mockApi = testConfiguration.getRestApi();
        mockSettings = testConfiguration.getUserSettings();
        presenter = new Example2Presenter(mockView, testConfiguration);
    }

    @Test
    public void testOnViewCreated() throws Exception {
        final String mockIp = "127.0.0.1";
        final String mockPreviousIp = "127.0.0.2";

        IpModel mockIpModel = new IpModel();
        mockIpModel.ip = mockIp;
        when(mockApi.getMyIp()).thenReturn(Observable.just(mockIpModel));
        when(mockSettings.getLastIp()).thenReturn(mockPreviousIp);

        presenter.onViewCreated();
        verify(mockView).showPreviousIpAddress(mockPreviousIp);
        testConfiguration.triggerRxSchedulers();
        verify(mockView).showCurrentIpAddress(mockIp);
        verify(mockSettings).setLastIp(mockIp);
    }

    @Test
    public void testOnViewCreated_NoPreviousIp() throws Exception {
        when(mockApi.getMyIp()).thenReturn(Observable.empty());
        when(mockSettings.getLastIp()).thenReturn(null);

        presenter.onViewCreated();
        verify(mockView).hidePreviousIpAddress();
    }
}
