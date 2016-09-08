package io.intrepid.skeleton.screens.demoSelection;

import android.support.annotation.NonNull;

import io.intrepid.skeleton.base.BasePresenter;
import io.intrepid.skeleton.base.PresenterConfiguration;
import io.intrepid.skeleton.models.Demo;

public class DemoSelectionPresenter extends BasePresenter<DemoSelectionContract.View> implements DemoSelectionContract.Presenter {
    public DemoSelectionPresenter(@NonNull DemoSelectionContract.View view,
                                  @NonNull PresenterConfiguration configuration) {
        super(view, configuration);
    }

    @Override
    public void demoSelected(Demo demo) {
        switch (demo) {
            case GITHUB:
                view.gotoGitHubUserRepos();
                break;
        }
    }
}
