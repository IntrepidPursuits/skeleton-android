package io.intrepid.skeleton.base;

import android.support.annotation.NonNull;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import io.intrepid.skeleton.testutils.PresenterTestBase;
import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class BasePresenterTest extends PresenterTestBase<BasePresenter<BaseContract.View>> {
    @Mock
    private BaseContract.View view;

    private Disposable disposable;

    @Before
    public void setUp() {
        presenter = spy(new TestPresenter(view, testConfiguration));
    }

    @Test
    public void bind_onlyOnce() throws Exception {
        presenter.bindView(view);
        presenter.bindView(view);
        assertEquals(view, presenter.view);
        verify(presenter, times(1)).onViewBound();
    }

    @Test
    public void unbind_onlyOnce() throws Exception {
        presenter.bindView(view);
        presenter.unbindView();
        presenter.unbindView();
        verify(presenter, times(1)).onViewUnbound();
    }

    @Test
    public void unbind_notBeforeBind() throws Exception {
        presenter.unbindView();
        verify(presenter, never()).onViewUnbound();
    }

    @Test
    public void unbind_clearsDisposables() {
        presenter.bindView(view);
        assertFalse(disposable.isDisposed());

        presenter.unbindView();
        assertTrue(disposable.isDisposed());
    }

    private class TestPresenter extends BasePresenter<BaseContract.View> {

        TestPresenter(@NonNull BaseContract.View view,
                      @NonNull PresenterConfiguration configuration) {
            super(view, configuration);
        }

        @Override
        protected void onViewBound() {
            disposable = Observable.never().subscribe();
            disposables.add(disposable);
        }
    }

}
