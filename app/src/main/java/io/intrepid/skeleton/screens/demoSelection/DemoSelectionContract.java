package io.intrepid.skeleton.screens.demoSelection;

import io.intrepid.skeleton.base.BaseContract;

public class DemoSelectionContract {
    public interface View extends BaseContract.View {
        void gotoGitHubUserRepos();
    }

    public interface Presenter extends BaseContract.Presenter<View>, DemoSelectionAdapter.OnSelectListener {
    }
}
