package io.intrepid.skeleton.screens.example1;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import io.intrepid.skeleton.testutils.PresenterTestBase;

import static org.mockito.Mockito.verify;

public class Example1PresenterTest extends PresenterTestBase<Example1Presenter> {

    @Mock
    Example1Contract.View mockView;

    @Before
    public void setup() {
        presenter = new Example1Presenter(mockView, testConfiguration);
    }

    @Test
    public void onButtonClick() {
        presenter.onButtonClick();
        verify(mockView).gotoExample2();
    }
}
