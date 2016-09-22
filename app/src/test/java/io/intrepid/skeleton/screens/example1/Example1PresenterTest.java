package io.intrepid.skeleton.screens.example1;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import io.intrepid.skeleton.testutils.TestPresenterConfiguration;

import static org.mockito.Mockito.verify;

public class Example1PresenterTest {

    @Mock
    Example1Contract.View mockView;

    private Example1Presenter presenter;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        presenter = new Example1Presenter(mockView, TestPresenterConfiguration.createTestConfiguration());
    }

    @Test
    public void testOnButtonClicked() throws Exception {
        presenter.onButtonClicked();
        verify(mockView).gotoExample2();
    }
}
