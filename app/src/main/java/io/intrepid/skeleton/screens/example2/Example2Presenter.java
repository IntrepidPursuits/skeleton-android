package io.intrepid.skeleton.screens.example2;

import android.support.annotation.NonNull;

import java.util.concurrent.TimeUnit;

import io.intrepid.skeleton.base.BasePresenter;
import io.intrepid.skeleton.base.PresenterConfiguration;
import io.intrepid.skeleton.utils.RxUtils;
import rx.Observable;
import rx.Subscription;

class Example2Presenter extends BasePresenter<Example2Contract.View> implements Example2Contract.Presenter {

    Example2Presenter(@NonNull Example2Contract.View view,
                      @NonNull PresenterConfiguration configuration) {
        super(view, configuration);
    }

    @Override
    public void onViewCreated() {
        Subscription subscription = Observable
                .fromCallable(() -> {
                    // Adding a delay here to demonstrate that Espresso test can automatically wait for this to finish
                    return "Hello World!";
                })
                .delay(3, TimeUnit.SECONDS, ioScheduler)
                .compose(subscribeOnIoObserveOnUi())
                .subscribe(text -> view.showText(text), RxUtils.logError());
        subscriptions.add(subscription);
    }
}
