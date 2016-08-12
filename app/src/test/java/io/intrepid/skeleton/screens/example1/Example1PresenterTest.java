package io.intrepid.skeleton.screens.example1;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import io.intrepid.skeleton.testutils.TestPresenterUtils;

import static org.mockito.Mockito.verify;

public class Example1PresenterTest {

    @Mock
    Example1Contract.View mockView;

    Example1Presenter presenter;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        presenter = new Example1Presenter(mockView, TestPresenterUtils.createTestConfiguration());
    }

    @Test
    public void testOnButtonClicked() throws Exception {
        presenter.onButtonClicked();
        verify(mockView).gotoExample2();
    }
}
