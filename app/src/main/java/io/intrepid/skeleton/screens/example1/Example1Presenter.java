package io.intrepid.skeleton.screens.example1;

import android.support.annotation.NonNull;

import io.intrepid.skeleton.base.BasePresenter;
import io.intrepid.skeleton.base.PresenterConfiguration;

class Example1Presenter extends BasePresenter<Example1Contract.View> implements Example1Contract.Presenter {

    Example1Presenter(@NonNull Example1Contract.View view,
                      @NonNull PresenterConfiguration configuration) {
        super(view, configuration);
    }

    @Override
    public void onButtonClick() {
        view.gotoExample2();
    }

    public void lintWarningTest(int number) {
        int testLint = 0;
        switch (number) {
            case 1:
                testLint = testLint + 1;
            case 2:
                testLint = testLint + 1;
            default:
                testLint = testLint + 1;
        }
    }
}
