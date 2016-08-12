package io.intrepid.skeleton.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;
import io.intrepid.skeleton.SkeletonApplication;

abstract class BaseActivity extends AppCompatActivity {
    @Override
    protected final void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(getLayoutResourceId());
        ButterKnife.bind(this);

        onViewCreated(savedInstanceState);
    }

    protected abstract int getLayoutResourceId();

    protected void onViewCreated(Bundle savedInstanceState) {

    }

    protected final SkeletonApplication getSkeletonApplication() {
        return (SkeletonApplication) getApplication();
    }
}
