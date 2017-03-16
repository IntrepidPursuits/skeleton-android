package io.intrepid.skeleton.base;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.intrepid.skeleton.SkeletonApplication;
import timber.log.Timber;

public abstract class BaseFragment<T extends BaseContract.Presenter> extends Fragment implements BaseContract.View {

    protected T presenter;
    private Unbinder unbinder;

    @Override
    @CallSuper
    public void onAttach(Context context) {
        Timber.v("Lifecycle onAttach: %s to %s", this, context);
        super.onAttach(context);
    }

    @Override
    @CallSuper
    public void onCreate(@Nullable Bundle savedInstanceState) {
        Timber.v("Lifecycle onCreate: %s", this);
        super.onCreate(savedInstanceState);
        PresenterConfiguration configuration = getSkeletonApplication().getPresenterConfiguration();
        presenter = createPresenter(configuration);
    }

    /**
     * Override {@link #onViewCreated(Bundle)} to handle any logic that needs to occur right after inflating the view.
     * onViewCreated is called immediately after onCreateView
     */
    @Override
    public final View onCreateView(LayoutInflater inflater,
                                   @Nullable ViewGroup container,
                                   @Nullable Bundle savedInstanceState) {
        Timber.v("Lifecycle onCreateView: %s", this);
        View view = inflater.inflate(getLayoutResourceId(), container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public final void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        onViewCreated(savedInstanceState);
        presenter.onViewCreated();
    }

    /**
     * Override this method to do any additional view initialization (ex: setup RecyclerView adapter)
     */
    protected void onViewCreated(@Nullable Bundle savedInstanceState) {
    }


    protected abstract int getLayoutResourceId();

    @NonNull
    public abstract T createPresenter(PresenterConfiguration configuration);

    @Override
    @CallSuper
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        Timber.v("Lifecycle onActivityResult: %s", this);
        super.onActivityResult(requestCode, resultCode, data);
        //noinspection unchecked
        presenter.bindView(this);
    }

    @Override
    @CallSuper
    public void onStart() {
        Timber.v("Lifecycle onStart: %s", this);
        super.onStart();
        //noinspection unchecked
        presenter.bindView(this);
    }

    @Override
    @CallSuper
    public void onResume() {
        Timber.v("Lifecycle onResume: %s", this);
        super.onResume();
    }

    @Override
    @CallSuper
    public void onPause() {
        Timber.v("Lifecycle onPause: %s", this);
        super.onPause();
    }

    @Override
    @CallSuper
    public void onStop() {
        Timber.v("Lifecycle onStop: %s", this);
        super.onStop();
        presenter.unbindView();
    }

    @Override
    @CallSuper
    public void onDestroyView() {
        Timber.v("Lifecycle onDestroyView: %s", this);
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    @CallSuper
    public void onDestroy() {
        Timber.v("Lifecycle onDestroy: %s", this);
        super.onDestroy();
        presenter.onViewDestroyed();
    }

    @Override
    public void onDetach() {
        Timber.v("Lifecycle onDetach: %s from %s", this, getContext());
        super.onDetach();
    }

    protected final SkeletonApplication getSkeletonApplication() {
        return (SkeletonApplication) getActivity().getApplication();
    }
}
