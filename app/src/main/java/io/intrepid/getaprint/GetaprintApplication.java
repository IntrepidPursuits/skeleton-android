package io.intrepid.getaprint;

import android.app.Application;

import com.squareup.leakcanary.LeakCanary;

import io.intrepid.getaprint.base.PresenterConfiguration;
import io.intrepid.getaprint.logging.CrashlyticsReporter;
import io.intrepid.getaprint.logging.TimberConfig;
import io.intrepid.getaprint.rest.RetrofitClient;
import io.intrepid.getaprint.settings.SharePreferencesManager;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class GetaprintApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        LeakCanary.install(this);

        CrashlyticsReporter.init(this);

        TimberConfig.init(CrashlyticsReporter.getInstance());
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
