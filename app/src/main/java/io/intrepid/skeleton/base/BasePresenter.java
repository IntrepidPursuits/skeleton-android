package io.intrepid.skeleton.base;

import android.support.annotation.NonNull;

import io.intrepid.skeleton.logging.CrashReporter;
import io.intrepid.skeleton.rest.RestApi;
import io.intrepid.skeleton.settings.UserSettings;
import io.intrepid.skeleton.utils.rx.SchedulerTransformer;
import io.reactivex.Scheduler;
import io.reactivex.disposables.CompositeDisposable;

public abstract class BasePresenter<T extends BaseContract.View> implements BaseContract.Presenter<T> {

    protected final CompositeDisposable disposables = new CompositeDisposable();

    protected T view;
    @NonNull
    protected final Scheduler ioScheduler;
    @NonNull
    protected final Scheduler uiScheduler;
    @NonNull
    protected final UserSettings userSettings;
    @NonNull
    protected final RestApi restApi;
    @NonNull
    protected final CrashReporter crashReporter;

    private boolean isViewBound = false;

    public BasePresenter(@NonNull T view, @NonNull PresenterConfiguration configuration) {
        this.view = view;
        this.ioScheduler = configuration.getIoScheduler();
        this.uiScheduler = configuration.getUiScheduler();
        this.userSettings = configuration.getUserSettings();
        this.restApi = configuration.getRestApi();
        this.crashReporter = configuration.getCrashReporter();
    }

    @Override
    public void onViewCreated() {

    }

    @Override
    public final void bindView(@NonNull T view) {
        this.view = view;

        if (!isViewBound) {
            onViewBound();
            isViewBound = true;
        }
    }

    protected void onViewBound() {

    }

    @Override
    public final void unbindView() {
        disposables.clear();
        this.view = null;

        if (isViewBound) {
            onViewUnbound();
            isViewBound = false;
        }
    }

    protected void onViewUnbound() {

    }

    /**
     * Note: The view will be null at this point. This method is for any additional cleanup that's not handled
     * by the CompositeDisposable
     */
    @Override
    public void onViewDestroyed() {

    }

    protected <R> SchedulerTransformer<R> subscribeOnIoObserveOnUi() {
        return new SchedulerTransformer<>(ioScheduler, uiScheduler);
    }
}
