package io.intrepid.getaprint.utils;

import android.support.annotation.NonNull;

import java.util.List;

import rx.functions.Action1;
import rx.functions.Func1;
import timber.log.Timber;

public class RxUtils {

    public static <T> Func1<T, Boolean> filterNull() {
        return item -> item != null;
    }

    public static <T> Func1<List<T>, Boolean> filterEmptyList() {
        return list -> list != null && !list.isEmpty();
    }

    @NonNull
    public static Action1<Throwable> logError() {
        return throwable -> Timber.w(throwable, "observable stream encountered an error");
    }
}
