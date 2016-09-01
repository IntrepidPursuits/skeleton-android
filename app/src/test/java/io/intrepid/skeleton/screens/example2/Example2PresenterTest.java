package io.intrepid.skeleton.screens.example2;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.concurrent.TimeUnit;

import io.intrepid.skeleton.testutils.TestPresenterConfiguration;
import io.intrepid.skeleton.testutils.TestPresenterUtils;

import static org.mockito.Mockito.verify;

public class Example2PresenterTest {

    @Mock
    Example2Contract.View mockView;

    Example2Presenter presenter;
    private TestPresenterConfiguration testConfiguration;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        testConfiguration = TestPresenterUtils.createTestConfiguration();
        presenter = new Example2Presenter(mockView, testConfiguration);
    }

    @Test
    public void testDelayedAction() throws Exception {
        presenter.onViewCreated();

        // "Time keeps on slippin' slippin' slippin'... into the future."
        testConfiguration.getTestIoScheduler().advanceTimeBy(4, TimeUnit.SECONDS);
        testConfiguration.getTestUiScheduler().triggerActions();
        // We may eventually want to have something like testConfig.advanceTimeBy() which does both of the above?

        verify(mockView).showText("Hello World!");
    }
}
