package io.intrepid.skeleton.base;

public interface BaseContract {

    interface View {
    }

    interface Presenter<T extends View> {

        void bindView(T view);

        void unbindView();

        void onViewCreated();

        void onViewDestroyed();
    }
}
