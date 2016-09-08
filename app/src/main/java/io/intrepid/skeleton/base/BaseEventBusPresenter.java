package io.intrepid.skeleton.base;

import android.support.annotation.NonNull;

import io.intrepid.skeleton.SkeletonApplication;

public class BaseEventBusPresenter<T extends BaseContract.View> extends BasePresenter<T> {
    public BaseEventBusPresenter(@NonNull T view, @NonNull PresenterConfiguration configuration) {
        super(view, configuration);
    }

    @Override
    protected void onViewBound() {
        SkeletonApplication.bus.register(this);
    }

    @Override
    protected void onViewUnbound() {
        SkeletonApplication.bus.unregister(this);
    }
}
