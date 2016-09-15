package io.intrepid.skeleton.screens.demoSelection;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import io.intrepid.skeleton.models.Demo;
import io.intrepid.skeleton.testutils.TestPresenterUtils;

import static org.mockito.Mockito.verify;

public class DemoSelectionPresenterTest {
    @Mock
    DemoSelectionContract.View mockView;

    DemoSelectionPresenter presenter;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        presenter = new DemoSelectionPresenter(mockView, TestPresenterUtils.createTestConfiguration());
    }

    @Test
    public void testOnDemoSelected_gitHubDemo() throws Exception {
        presenter.demoSelected(Demo.GITHUB);
        verify(mockView).gotoGitHubUserRepos();
    }
}
