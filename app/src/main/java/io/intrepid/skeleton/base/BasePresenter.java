package io.intrepid.skeleton.base;

public interface BasePresenter<T extends BaseView> {

    void bindView(T view);

    void unbindView();

    void onViewCreated();

    void onViewDestroyed();
}
