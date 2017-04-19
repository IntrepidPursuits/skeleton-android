package io.intrepid.skeleton.screens.example2;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import io.intrepid.skeleton.models.IpModel;
import io.intrepid.skeleton.testutils.BasePresenterTest;
import io.reactivex.Observable;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class Example2PresenterTest extends BasePresenterTest<Example2Presenter> {

    @Mock
    Example2Contract.View mockView;

    @Before
    public void setUp() {
        presenter = new Example2Presenter(mockView, testConfiguration);
    }

    @Test
    @SuppressWarnings("PMD.AvoidUsingHardCodedIP")
    public void onViewCreated() {
        final String mockIp = "127.0.0.1";
        final String mockPreviousIp = "127.0.0.2";

        IpModel mockIpModel = new IpModel();
        mockIpModel.ip = mockIp;
        when(mockRestApi.getMyIp()).thenReturn(Observable.just(mockIpModel));
        when(mockUserSettings.getLastIp()).thenReturn(mockPreviousIp);

        presenter.onViewCreated();
        verify(mockView).showPreviousIpAddress(mockPreviousIp);
        testConfiguration.triggerRxSchedulers();
        verify(mockView).showCurrentIpAddress(mockIp);
        verify(mockUserSettings).setLastIp(mockIp);
    }

    @Test
    public void onViewCreated_NoPreviousIp() {
        when(mockRestApi.getMyIp()).thenReturn(Observable.empty());
        when(mockUserSettings.getLastIp()).thenReturn(null);

        presenter.onViewCreated();
        verify(mockView).hidePreviousIpAddress();
    }
}
