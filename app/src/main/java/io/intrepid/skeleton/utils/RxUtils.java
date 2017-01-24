package io.intrepid.skeleton.utils;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import timber.log.Timber;

public class RxUtils {

    @NonNull
    public static Consumer<Throwable> logError() {
        return throwable -> Timber.w(throwable, "observable stream encountered an error");
    }

    public static void unsubscribeDisposable(@Nullable Disposable disposable) {
        if (disposable != null) {
            disposable.dispose();
        }
    }
}
