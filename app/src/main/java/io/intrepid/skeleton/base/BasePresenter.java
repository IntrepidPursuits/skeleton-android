package io.intrepid.skeleton.base;

import android.support.annotation.NonNull;

import io.intrepid.skeleton.rest.RestApi;
import io.intrepid.skeleton.settings.UserSettings;
import rx.Observable;
import rx.Scheduler;
import rx.subscriptions.CompositeSubscription;

public abstract class BasePresenter<T extends BaseContract.View> implements BaseContract.Presenter<T> {

    protected final CompositeSubscription subscriptions = new CompositeSubscription();

    protected T view;
    @NonNull
    protected final Scheduler ioScheduler;
    @NonNull
    protected final Scheduler uiScheduler;
    @NonNull
    protected final UserSettings userSettings;
    @NonNull
    protected final RestApi restApi;

    public BasePresenter(@NonNull T view, @NonNull PresenterConfiguration configuration) {
        this.view = view;
        this.ioScheduler = configuration.getIoScheduler();
        this.uiScheduler = configuration.getUiScheduler();
        this.userSettings = configuration.getUserSettings();
        this.restApi = configuration.getRestApi();
    }

    @Override
    public void onViewCreated() {

    }

    @Override
    public final void bindView(T view) {
        this.view = view;
        onViewBinded();
    }

    protected void onViewBinded() {

    }

    @Override
    public void unbindView() {
        subscriptions.clear();
        this.view = null;
        onViewUnbinded();
    }

    private void onViewUnbinded() {

    }

    /**
     * Note: The view will be null at this point. This method is for any additional cleanup that's not handled
     * by the CompositeSubscription
     */
    @Override
    public void onViewDestroyed() {

    }

    protected <R> Observable.Transformer<R, R> subscribeOnIoObserveOnUi() {
        return observable -> observable.subscribeOn(ioScheduler).observeOn(uiScheduler);
    }
}
