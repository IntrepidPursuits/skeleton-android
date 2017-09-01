package io.intrepid.skeleton.utils.rx;

import org.junit.Test;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Maybe;
import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.observers.TestObserver;
import io.reactivex.schedulers.TestScheduler;
import io.reactivex.subscribers.TestSubscriber;

public class SchedulerTransformerTest {

    private final TestScheduler ioScheduler = new TestScheduler();
    private final TestScheduler uiScheduler = new TestScheduler();
    private final SchedulerTransformer<Integer> transformer = new SchedulerTransformer<>(ioScheduler, uiScheduler);

    @Test
    public void observableApply() {
        TestObserver<Integer> testObserver = Observable.just(5).compose(transformer).test();
        testObserver.assertNoValues();
        ioScheduler.triggerActions();
        testObserver.assertNoValues();
        uiScheduler.triggerActions();
        testObserver.assertValue(5);
        testObserver.assertComplete();
    }

    @Test
    public void flowableApply() {
        TestSubscriber<Integer> testObserver = Flowable.just(5).compose(transformer).test();
        testObserver.assertNoValues();
        ioScheduler.triggerActions();
        testObserver.assertNoValues();
        uiScheduler.triggerActions();
        testObserver.assertValue(5);
        testObserver.assertComplete();
    }

    @Test
    public void singleApply() {
        TestObserver<Integer> testObserver = Single.just(5).compose(transformer).test();
        testObserver.assertNoValues();
        ioScheduler.triggerActions();
        testObserver.assertNoValues();
        uiScheduler.triggerActions();
        testObserver.assertValue(5);
        testObserver.assertComplete();
    }

    @Test
    public void completableApply() {
        TestObserver<Void> testObserver = Completable.complete().compose(transformer).test();
        testObserver.assertNotComplete();
        ioScheduler.triggerActions();
        testObserver.assertNotComplete();
        uiScheduler.triggerActions();
        testObserver.assertComplete();
    }

    @Test
    public void maybeApply() {
        TestObserver<Integer> testObserver = Maybe.just(5).compose(transformer).test();
        testObserver.assertNoValues();
        ioScheduler.triggerActions();
        testObserver.assertNoValues();
        uiScheduler.triggerActions();
        testObserver.assertValue(5);
        testObserver.assertComplete();
    }
}
