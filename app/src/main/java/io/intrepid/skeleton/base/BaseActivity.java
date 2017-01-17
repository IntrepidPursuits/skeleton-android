package io.intrepid.skeleton.base;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;
import io.intrepid.skeleton.SkeletonApplication;
import timber.log.Timber;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

abstract class BaseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        Timber.v("Lifecycle onCreate: " + this);
        super.onCreate(savedInstanceState);

        setContentView(getLayoutResourceId());
        ButterKnife.bind(this);
    }

    @Override
    @CallSuper
    protected void onNewIntent(Intent intent) {
        Timber.v("Lifecycle onNewIntent: " + this);
        super.onNewIntent(intent);
    }

    @Override
    @CallSuper
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Timber.v("Lifecycle onActivityResult: " + this);
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    @CallSuper
    protected void onStart() {
        Timber.v("Lifecycle onStart: " + this);
        super.onStart();
    }

    @Override
    @CallSuper
    protected void onResume() {
        Timber.v("Lifecycle onResume: " + this);
        super.onResume();
    }

    @Override
    @CallSuper
    protected void onPause() {
        Timber.v("Lifecycle onPause: " + this);
        super.onPause();
    }

    @Override
    @CallSuper
    protected void onStop() {
        Timber.v("Lifecycle onStop: " + this);
        super.onStop();
    }

    @Override
    @CallSuper
    protected void onDestroy() {
        Timber.v("Lifecycle onDestroy: " + this);
        super.onDestroy();
    }

    @Override
    @CallSuper
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    protected abstract int getLayoutResourceId();

    protected final SkeletonApplication getSkeletonApplication() {
        return (SkeletonApplication) getApplication();
    }
}
