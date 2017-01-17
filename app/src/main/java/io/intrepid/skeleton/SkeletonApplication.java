package io.intrepid.skeleton;

import android.app.Application;

import com.squareup.leakcanary.LeakCanary;

import io.intrepid.skeleton.base.PresenterConfiguration;
import io.intrepid.skeleton.logging.CrashlyticsReporter;
import io.intrepid.skeleton.logging.TimberConfig;
import io.intrepid.skeleton.rest.RetrofitClient;
import io.intrepid.skeleton.settings.SharePreferencesManager;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

public class SkeletonApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        LeakCanary.install(this);

        CrashlyticsReporter.init(this);

        TimberConfig.init(CrashlyticsReporter.getInstance());

        initCalligraphy();
    }

    private void initCalligraphy() {
        CalligraphyConfig config = new CalligraphyConfig.Builder()
                .setDefaultFontPath(getString(R.string.Roboto_Regular))
                .setFontAttrId(R.attr.fontPath)
                .build();
        CalligraphyConfig.initDefault(config);
    }

    public PresenterConfiguration getPresenterConfiguration() {
        return new PresenterConfiguration(
                Schedulers.io(),
                AndroidSchedulers.mainThread(),
                SharePreferencesManager.getInstance(this),
                RetrofitClient.getApi(),
                CrashlyticsReporter.getInstance()
        );
    }
}
