package io.intrepid.skeleton.utils;

import org.junit.Test;

import io.reactivex.disposables.Disposable;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class RxUtilsTest {

    @Test
    public void unsubscribeDisposable() {
        Disposable mockDisposable = mock(Disposable.class);
        RxUtils.unsubscribeDisposable(mockDisposable);
        verify(mockDisposable).dispose();

        // just ensures that there's not exception
        RxUtils.unsubscribeDisposable(null);
    }
}
