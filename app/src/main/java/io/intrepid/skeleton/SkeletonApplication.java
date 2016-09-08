package io.intrepid.skeleton;

import android.app.Application;
import android.widget.Toast;

import com.squareup.leakcanary.LeakCanary;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import io.intrepid.skeleton.base.PresenterConfiguration;
import io.intrepid.skeleton.events.OnThrowableEvent;
import io.intrepid.skeleton.logging.CrashlyticsReporter;
import io.intrepid.skeleton.logging.TimberConfig;
import io.intrepid.skeleton.settings.SharePreferencesManager;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class SkeletonApplication extends Application {
    public static EventBus bus;

    @Override
    public void onCreate() {
        super.onCreate();

        LeakCanary.install(this);

        CrashlyticsReporter.init(this);

        TimberConfig.init(CrashlyticsReporter.getInstance());

        bus = new EventBus();
        bus.register(this);
    }

    public PresenterConfiguration getPresenterConfiguration() {
        return new PresenterConfiguration(
                Schedulers.io(),
                AndroidSchedulers.mainThread(),
                SharePreferencesManager.getInstance(this)
        );
    }

    @Subscribe
    @SuppressWarnings("unused")
    public void onEvent(OnThrowableEvent onThrowableEvent) {
        Toast.makeText(this, onThrowableEvent.getThrowable().getMessage(), Toast.LENGTH_SHORT).show();
    }
}
