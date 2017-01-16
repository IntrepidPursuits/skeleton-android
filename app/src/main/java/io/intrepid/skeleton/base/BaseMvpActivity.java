package io.intrepid.skeleton.base;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * Base class for activities that will have some business logic instead of just hosting a fragment.
 * If the activity is only going to act as a container for a fragment, use {@link BaseFragmentActivity}
 * instead
 */
public abstract class BaseMvpActivity<T extends BaseContract.Presenter> extends BaseActivity implements BaseContract.View {

    protected T presenter;

    @NonNull
    public abstract T createPresenter(PresenterConfiguration configuration);

    protected abstract int getLayoutResourceId();

    @Override
    /**
     * Override {@link #onViewCreated(Bundle)} to handle any logic that needs to occur right after inflating the view.
     * onViewCreated is called immediately after onCreateView
     */
    protected final void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        PresenterConfiguration configuration = getSkeletonApplication().getPresenterConfiguration();
        presenter = createPresenter(configuration);
        onViewCreated(savedInstanceState);
        presenter.onViewCreated();
    }

    /**
     * Override this method to do any additional view initialization (ex: setup RecycleView adapter)
     */
    protected void onViewCreated(Bundle savedInstanceState) {

    }

    @Override
    @CallSuper
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        presenter.bindView(this);
    }

    @Override
    @CallSuper
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        presenter.bindView(this);
    }

    @Override
    @CallSuper
    public void onStart() {
        super.onStart();
        presenter.bindView(this);
    }

    @Override
    @CallSuper
    public void onStop() {
        super.onStop();
        presenter.unbindView();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.onViewDestroyed();
    }
}
