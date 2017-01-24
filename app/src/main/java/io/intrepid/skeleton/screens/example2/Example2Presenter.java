package io.intrepid.skeleton.screens.example2;

import android.support.annotation.NonNull;

import io.intrepid.skeleton.base.BasePresenter;
import io.intrepid.skeleton.base.PresenterConfiguration;
import io.intrepid.skeleton.utils.RxUtils;
import io.reactivex.disposables.Disposable;

class Example2Presenter extends BasePresenter<Example2Contract.View> implements Example2Contract.Presenter {

    Example2Presenter(@NonNull Example2Contract.View view,
                      @NonNull PresenterConfiguration configuration) {
        super(view, configuration);
    }

    @Override
    public void onViewCreated() {
        Disposable disposable = restApi.getMyIp()
                .compose(subscribeOnIoObserveOnUi())
                .subscribe(ipModel -> {
                    String ip = ipModel.ip;
                    view.showCurrentIpAddress(ip);
                    userSettings.setLastIp(ip);
                }, RxUtils.logError());
        disposables.add(disposable);

        String lastIp = userSettings.getLastIp();
        if (lastIp == null) {
            view.hidePreviousIpAddress();
        } else {
            view.showPreviousIpAddress(lastIp);
        }
    }
}
