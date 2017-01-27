package io.intrepid.skeleton.screens.example1;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import io.intrepid.skeleton.testutils.BasePresenterTest;
import io.intrepid.skeleton.testutils.TestPresenterConfiguration;

import static org.mockito.Mockito.verify;

public class Example1PresenterTest extends BasePresenterTest<Example1Presenter> {

    @Mock
    Example1Contract.View mockView;

    @Before
    public void setup() {
        presenter = new Example1Presenter(mockView, TestPresenterConfiguration.createTestConfiguration());
    }

    @Test
    public void onButtonClick() throws Exception {
        presenter.onButtonClick();
        verify(mockView).gotoExample2();
    }
}
