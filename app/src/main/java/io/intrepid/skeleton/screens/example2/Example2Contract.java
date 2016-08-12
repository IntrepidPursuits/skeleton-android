package io.intrepid.skeleton.screens.example2;

import io.intrepid.skeleton.base.BasePresenter;
import io.intrepid.skeleton.base.BaseView;

public class Example2Contract {
    public interface View extends BaseView {

        void showText(String text);
    }

    public interface Presenter extends BasePresenter<View> {

    }
}
