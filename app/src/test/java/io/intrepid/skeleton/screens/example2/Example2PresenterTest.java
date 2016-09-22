package io.intrepid.skeleton.screens.example2;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.concurrent.TimeUnit;

import io.intrepid.skeleton.testutils.TestPresenterConfiguration;

import static org.mockito.Mockito.verify;

public class Example2PresenterTest {

    @Mock
    Example2Contract.View mockView;

    private Example2Presenter presenter;
    private TestPresenterConfiguration testConfiguration;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        testConfiguration = TestPresenterConfiguration.createTestConfiguration();
        presenter = new Example2Presenter(mockView, testConfiguration);
    }

    @Test
    public void testDelayedAction() throws Exception {
        presenter.onViewCreated();

        // "Time keeps on slippin' slippin' slippin'... into the future."
        testConfiguration.advanceRxSchedulers(4, TimeUnit.SECONDS);

        verify(mockView).showText("Hello World!");
    }
}
