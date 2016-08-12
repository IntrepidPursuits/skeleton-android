package io.intrepid.skeleton.screens.example1;

import android.support.annotation.NonNull;

import io.intrepid.skeleton.base.BasePresenterImpl;
import io.intrepid.skeleton.base.PresenterConfiguration;

public class Example1Presenter extends BasePresenterImpl<Example1Contract.View> implements Example1Contract.Presenter {

    public Example1Presenter(@NonNull Example1Contract.View view,
                             @NonNull PresenterConfiguration configuration) {
        super(view, configuration);
    }

    @Override
    public void onButtonClicked() {
        view.gotoExample2();
    }
}
