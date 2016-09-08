package io.intrepid.skeleton;

import android.os.AsyncTask;

import io.intrepid.skeleton.base.PresenterConfiguration;
import io.intrepid.skeleton.settings.SharePreferencesManager;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class InstrumentationTestApplication extends SkeletonApplication {
    @Override
    public PresenterConfiguration getPresenterConfiguration() {
        return new PresenterConfiguration(
                // using AsyncTask executor since Espresso automatically waits for it to clear before proceeding
                Schedulers.from(AsyncTask.THREAD_POOL_EXECUTOR),
                AndroidSchedulers.mainThread(),
                // these can be replaced with mock version
                SharePreferencesManager.getInstance(this)
        );
    }
}
