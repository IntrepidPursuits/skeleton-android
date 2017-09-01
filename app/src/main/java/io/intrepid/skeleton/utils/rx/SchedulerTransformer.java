package io.intrepid.skeleton.utils.rx;

import android.support.annotation.NonNull;

import org.reactivestreams.Publisher;

import io.reactivex.Completable;
import io.reactivex.CompletableSource;
import io.reactivex.Flowable;
import io.reactivex.Maybe;
import io.reactivex.MaybeSource;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Scheduler;
import io.reactivex.Single;
import io.reactivex.SingleSource;

public class SchedulerTransformer<R> implements CombinedTransformer<R> {

    @NonNull
    private final Scheduler ioScheduler;
    @NonNull
    private final Scheduler uiScheduler;

    public SchedulerTransformer(@NonNull Scheduler ioScheduler, @NonNull Scheduler uiScheduler) {
        this.ioScheduler = ioScheduler;
        this.uiScheduler = uiScheduler;
    }

    @NonNull
    @Override
    public CompletableSource apply(@NonNull Completable upstream) {
        return upstream.subscribeOn(ioScheduler).observeOn(uiScheduler);
    }

    @NonNull
    @Override
    public Publisher<R> apply(@NonNull Flowable<R> upstream) {
        return upstream.subscribeOn(ioScheduler).observeOn(uiScheduler);
    }

    @NonNull
    @Override
    public MaybeSource<R> apply(@NonNull Maybe<R> upstream) {
        return upstream.subscribeOn(ioScheduler).observeOn(uiScheduler);
    }

    @NonNull
    @Override
    public ObservableSource<R> apply(@NonNull Observable<R> upstream) {
        return upstream.subscribeOn(ioScheduler).observeOn(uiScheduler);
    }

    @NonNull
    @Override
    public SingleSource<R> apply(@NonNull Single<R> upstream) {
        return upstream.subscribeOn(ioScheduler).observeOn(uiScheduler);
    }
}
