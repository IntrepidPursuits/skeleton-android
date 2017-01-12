package io.intrepid.getaprint.screens.example1;

import android.support.annotation.NonNull;

import io.intrepid.getaprint.base.BasePresenter;
import io.intrepid.getaprint.base.PresenterConfiguration;

class Example1Presenter extends BasePresenter<Example1Contract.View> implements Example1Contract.Presenter {

    Example1Presenter(@NonNull Example1Contract.View view,
                      @NonNull PresenterConfiguration configuration) {
        super(view, configuration);
    }

    @Override
    public void onButtonClicked() {
        view.gotoExample2();
    }
}
