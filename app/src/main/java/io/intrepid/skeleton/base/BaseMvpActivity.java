package io.intrepid.skeleton.base;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.NonNull;

public abstract class BaseMvpActivity<T extends BaseContract.Presenter> extends BaseActivity implements BaseContract.View {

    protected T presenter;

    @NonNull
    public abstract T createPresenter(PresenterConfiguration configuration);

    protected abstract int getLayoutResourceId();

    @CallSuper
    protected void onViewCreated(Bundle savedInstanceState) {
        PresenterConfiguration configuration = getSkeletonApplication().getPresenterConfiguration();
        presenter = createPresenter(configuration);
        presenter.onViewCreated();
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
